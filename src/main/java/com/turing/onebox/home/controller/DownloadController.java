package com.turing.onebox.home.controller;

import cn.hutool.core.io.FileUtil;
import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.home.service.DownloadService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName DownloadController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 15:47
 * @Version 1.0
 */
@Controller
public class DownloadController {

    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private DownloadService downloadService;


    /**
     * 下载文件
     * @param id
     * @return
     */
    @GetMapping("/download")
    @ResponseBody
    public AjaxJson<?> downAttachment(HttpServletResponse response, Integer id) throws IOException {
        FileInfo fileInfo = downloadService.queryFileById(id);
        File file = new File(fileInfo.getRealPath());
        System.out.println(file);
        if (!file.exists()) {
            return AjaxJson.getError();
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileInfo.getName()+fileInfo.getExt());
        byte[] readBytes = FileUtil.readBytes(file);
        OutputStream os = response.getOutputStream();
        os.write(readBytes);
        return AjaxJson.getSuccess();

    }

}
