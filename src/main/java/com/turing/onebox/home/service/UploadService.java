package com.turing.onebox.home.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @ClassName UploadService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 16:20
 * @Version 1.0
 */
@Service
public class UploadService {

    public void uploadFile(String path, InputStream inputStream) {
        

        // 将虚拟路径转化为真实路径
//        String baseFilePath = "/root" + "";
//        String uploadPath = StringUtils.removeDuplicateSlashes(baseFilePath + StringUtils.DELIMITER_STR + path);

        // 上传文件
//        File uploadToFileObj = new File(uploadPath);
//        BufferedOutputStream outputStream = FileUtil.getOutputStream(uploadToFileObj);
//        IoUtil.copy(inputStream, outputStream);

    }

}
