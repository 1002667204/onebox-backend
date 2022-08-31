package com.turing.onebox.common.utils;

import com.turing.onebox.common.constant.OneboxConstant;
import com.turing.onebox.common.exception.NoTypeException;
import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.ImportUser;
import com.turing.onebox.home.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

/**
 * @author HuangYuhan
 * @create 2022-08-24-14:23
 * 文件以及文件夹的工具类
 * 不要试图把它变成static的
 * 因为路径是通过@ConfigurtationPropertites注解获取的
 * 是反射注入进去的·····静态的话就注入不了路径
 * 如果你通过其他方式获取路径，可以考虑改成static的方法
 */
@Component
public class FileInfoUtils {


    @Autowired
    private ClassInfoService classInfoService;


    private String ROOT_FILE_PATH=OneboxConstant.ROOT_FILE_PATH;

    /**
     *
     * 上传文件：封装fileInfo信息
     * @return
     * @Author Huang
     */
    public  FileInfo encapsulateFileInfo(ImportUser user) throws NoTypeException {
//        创建FileInfo文件类
        FileInfo fileInfo = new FileInfo();
//        封装id
        Integer uuid = UUIDUtils.getUUID();
        fileInfo.setId(uuid);
//        获取name（无后缀）
        String originalFilename = user.getFile().getOriginalFilename();
        String name = originalFilename.substring(0, originalFilename.indexOf("."));
//        组合名字+唯一id
        String uniqueName=name+"-"+uuid.toString();
//        截取后缀(带点）
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
//        根据后缀查找类型（未找到就会抛出类型异常）
        String suffixType = FileInfoUtils.getClassNameByExt(suffixName, classInfoService);
//        封装type
        fileInfo.setType(suffixType);
//        封装ext
        fileInfo.setExt(suffixName);
//        封装name
        fileInfo.setName(name);
//        封装dir
        fileInfo.setDir(user.getId());
//        计算文件大小
        long sizeOfLong = user.getFile().getSize();
        int size = Math.toIntExact(sizeOfLong)/1024+1;
//        封装size   kb
        fileInfo.setSize(size);
//        组合路径:路径+唯一名字（名字+id）+后缀
        String realPath=ROOT_FILE_PATH+uniqueName+suffixName;
//        封装realPath
        fileInfo.setRealPath(realPath);
//        封装createTime
        String createTime=DateUtils.formateDate(new Date());
        fileInfo.setCreateTime(createTime);
//        封装inRecycled
        fileInfo.setInRecycled(OneboxConstant.NOT_IN_RECYCLED);
//        封装star
        fileInfo.setStar(OneboxConstant.IS_NOT_STARRED);
        return fileInfo;
    }

    /**
     * 根据realPath和newName生成新的realPath：
     * 就是修改名字的时候，也修改真实文件的名字
     * @return newRealPath
     */
    public static String generateNewRealPath(String realPath, String oldName,String newName) {
//      用newName替换oldName
        String newRealPath = realPath.replaceAll(oldName, newName);
        return newRealPath;
    }



    /*根据后缀返回类型*/
    public static String getClassNameByExt(String ext, ClassInfoService classInfoService) throws NoTypeException {
//        删去后缀的那个点
        //String ext = originName.substring(1, originName.length());

        try {
            String className = classInfoService.queryClassNameByExt(ext);
            if (className != null) {
                return className;
            }else {
                throw new NoTypeException("找不到后缀对应的类型");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoTypeException("找不到后缀对应的类型");
        }

    }















}
