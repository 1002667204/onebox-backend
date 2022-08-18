package com.turing.onebox.home.controller;

import com.turing.onebox.common.utils.AjaxJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 16:19
 * @Version 1.0
 */
@Controller
public class UploadController {

    @Resource
    private HttpServletRequest httpServletRequest;


    /**
     *
     * @param file
     * @param dir 上传的虚拟路径
     * @return
     * @throws IOException
     */
    @PostMapping("/file/upload/**")
    @ResponseBody
    public AjaxJson<?> upload(@RequestParam MultipartFile file, Integer dir) throws IOException {
        // 判断上传的文件是否为空
        if (file == null || file.isEmpty()) {
            return AjaxJson.getError("文件为空，无法上传.");
        }

        // 获取虚拟路径对应的真实路径
//        String path = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//        String bestMatchPattern = (String) httpServletRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
//        AntPathMatcher apm = new AntPathMatcher();
//        String filePath = apm.extractPathWithinPattern(bestMatchPattern, path);
        String filePath = "";

        // 处理文件重名问题

        // 进行上传
//        UploadService uploadService = new UploadService();
//        uploadService.uploadFile(filePath, file.getInputStream());
        File file1 = new File(filePath + file.getOriginalFilename());
        file.transferTo(file1);

        // 更新文件表

        // 更新日志表

        return AjaxJson.getSuccess();
    }

}
