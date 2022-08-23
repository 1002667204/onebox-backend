package com.turing.onebox.home.controller;

import com.turing.onebox.home.service.DownloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DownloadController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 15:47
 * @Version 1.0
 */
@Controller
@CrossOrigin
public class DownloadController {

    @Resource
    private HttpServletRequest httpServletRequest;


    /**
     * 下载文件
     * @param id
     * @return
     */
    @GetMapping("/download/**")
//    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<org.springframework.core.io.Resource> downAttachment(Integer id) {
//    public ResponseEntity<org.springframework.core.io.Resource> downAttachment() {
        // 获取下载文件路径
//        String path = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
//        String bestMatchPattern = (String) httpServletRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
//        AntPathMatcher apm = new AntPathMatcher();
//        String filePath = apm.extractPathWithinPattern(bestMatchPattern, path);

        // 进行下载.
        DownloadService downloadService = new DownloadService();

        return downloadService.downloadToStream(id);
//        return downloadService.downloadToStream(0);
    }

}
