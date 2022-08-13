package com.truing.onebox.home.controller;

import com.truing.onebox.common.utils.AjaxJson;
import com.truing.onebox.home.model.dto.FileItem;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 16:17
 * @Version 1.0
 */
@RequestMapping("/api/storage")
@RestController
public class FileController {

    /**
     * 获取文件列表
     */
    @PostMapping("/files")
    public AjaxJson<List<FileItem>> list(String name){
        return null;
    }



}
