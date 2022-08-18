package com.turing.onebox.home.service;

import com.turing.onebox.common.utils.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName DownloadService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 15:36
 * @Version 1.0
 */
@Service
public class DownloadService {

    /**
     * 以流的形式下载文件
     * @param id 文件id
     * @return
     */
    public ResponseEntity<Resource> downloadToStream(Integer id) {

        // 获取要下载的文件（真实绝对路径）
//        File file = new File(StringUtils.removeDuplicateSlashes(StringUtils.DELIMITER_STR));
        File file = new File("/Users/colin13/Desktop/banner.txt");

        // 判断文件是否存在
        if (!file.exists()) {
            ByteArrayResource byteArrayResource = new ByteArrayResource("文件不存在或异常".getBytes(StandardCharsets.UTF_8));
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(byteArrayResource);
        }

        // 返回下载信息
        HttpHeaders headers = new HttpHeaders();
        String fileName = file.getName();

        // 设置以附件的形式下载文件
        headers.setContentDispositionFormData("attachment", StringUtils.encodeAllIgnoreSlashes(fileName));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // 设置响应类型
                .body(new FileSystemResource(file));
    }

}
