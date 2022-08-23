package com.turing.onebox.home.controller;

import com.turing.onebox.common.constant.OneboxConstant;
import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.ImportUser;
import com.turing.onebox.common.model.dto.LogInfo;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.common.utils.FileConfig;
import com.turing.onebox.common.utils.UUIDUtils;
import com.turing.onebox.home.service.ClassInfoService;
import com.turing.onebox.home.service.FileService;
import com.turing.onebox.home.service.LogService;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 16:19
 * @Version 1.0
 */
@Controller
@CrossOrigin
public class UploadController {
    /*
     * 文件上传路径
     * 在配置里修改
     *
     * */
    @Value("${huang.file.upload.path}")
    public   String FILE_UPLOAD_PATH;

/*
    @Autowired
    private FileConfig fileConfig;
*/

    @Resource
    private HttpServletRequest httpServletRequest;
    @Autowired
    private FileService fileService;

    @Autowired
    private LogService logService;

    @Autowired
    private ClassInfoService classInfoService;

    /**
     * @return
     * @throws IOException
     * @Author HuangYuhan
     * @Description name需要prefix，type需要判断，ext需要
     * suffix,size需要从kb变成mb，realpath是path+originalName
     * 需要考虑日志更新
     * 需要考虑文件重命名:
     * (查询在相同文件夹下的是否有同名的文件：dir，name）
     * 1.文件大小用kb还是mb
     * 2.类型判断
     * （根据后缀，查询其所属类型）
     */
    @PostMapping("/file/upload/**")
    @ResponseBody
    public AjaxJson<?> upload(ImportUser importUser, BindingResult bindingResult) throws IOException {

        MultipartFile file = importUser.getFile();
        System.out.println(file);
        Integer dir = importUser.getId();
        // 判断上传的文件是否为空
        if (file == null || file.isEmpty()) {
            return AjaxJson.getError("文件为空，无法上传.");
        }

//        更新文件列表
//        创建FileInfo类存储文件信息
        FileInfo fileInfo = new FileInfo();
        String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
      //  设置每个文件的唯一文件名
        Integer onlyId=UUIDUtils.getUUID();
        String fileOnlyName=fileName+onlyId;
        fileInfo.setName(fileName);
//        检查文件是否重命名，如果名字已经存在，则直接不允许存储
        boolean flag = fileService.queryFileByFileNameAndDir(fileName, dir);
        if (!flag) {
            return AjaxJson.getError("文件名已经存在于该文件夹，上传失败");
        }

        fileInfo.setId(onlyId);
        fileInfo.setDir(dir);
        String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        fileInfo.setExt(fileSuffix);
//        设置类型
        String className = getClassNameByExt(fileSuffix, classInfoService);
        if (className.equals("false")) {
            return AjaxJson.getError("该文件后缀找不到所属类型");
        } else {
            fileInfo.setType(className);
        }
//        ---------------
        //System.out.println(file.getSize());
        int fileSize = Math.toIntExact(file.getSize() / 1024);
        //System.out.println(fileSize);
        //System.out.println(fileSize/1024);
        fileInfo.setSize(fileSize);
        fileInfo.setCreateTime(DateUtils.formateDateTime(new Date()));
//        -------------------
//        真实路径就是：路径+文件名+唯一id+后缀
        fileInfo.setRealPath(FILE_UPLOAD_PATH + fileOnlyName+fileSuffix);
        fileInfo.setInRecycled(OneboxConstant.NOT_IN_RECYCLED);
        fileInfo.setStar(OneboxConstant.IS_NOT_STARRED);
//        通过service层存储File信息

//        更新日志信息 1.创建日志信息 2.通过logservice存储
        LogInfo logInfo = new LogInfo();
        logInfo.setId(UUIDUtils.getUUID());
        logInfo.setFileName(fileName);
        logInfo.setMethod(1);
        logInfo.setModifyTime(DateUtils.formateDate(new Date()));

        try {
            boolean fileFlag = fileService.uploadFile(fileInfo);
            boolean logFlag = logService.addLogInfo(logInfo);
            if (fileFlag && logFlag) {
//                影响行数为1表示插入文件在数据库成功
                //将文件上传至指定路径
//               获取文件的
//      获取文件全路径:路径+文件名+唯一id+后缀
                Path path = Paths.get(FILE_UPLOAD_PATH +fileOnlyName+fileSuffix);
                System.out.println(path.toString());
                byte[] bytes;
                try {
//        获取文件流
                    bytes = file.getBytes();
//        将文件写入指定路径
                    Files.write(path, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                    return AjaxJson.getError("写入文件至指定路径失败");
                }
                //System.out.println("至此文件上传结束");
                return AjaxJson.getSuccess("成功将文件信息保存至数据库且更新日志成功");
            } else {
                return AjaxJson.getError("文件信息保存数据库失败或者日志更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxJson.getError("文件信息保存数据库出现异常");
        }

    }




    /*根据后缀返回类型*/
    public String getClassNameByExt(String ext, ClassInfoService classInfoService) {
//        删去后缀的那个点
        //String ext = originName.substring(1, originName.length());

        try {
            String className = classInfoService.queryClassNameByExt(ext);
            if (className != null) {
                return className;
            }else {
                return "false";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }

    }

}
