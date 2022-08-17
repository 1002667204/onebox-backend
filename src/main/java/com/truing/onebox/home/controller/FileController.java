package com.truing.onebox.home.controller;

import com.truing.onebox.common.model.dto.FileInfo;
import com.truing.onebox.common.utils.AjaxJson;
import com.truing.onebox.common.model.result.FileItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
     * @Return AjaxJson.getSuccessData(fileItemList)
     */
    @PostMapping("/files")
    public AjaxJson<List<FileItem>> list(){
        List<FileItem> fileItemList = new ArrayList<>();
        return AjaxJson.getSuccessData(fileItemList);
    }


    /**
     * 获取星标文件列表
     * @Return AjaxJson.getSuccessData(fileItemList)
     */
    @PostMapping("/starredfiles")
    public AjaxJson<List<FileItem>> listStarred(){
        List<FileItem> fileItemList = new ArrayList<>();
        return AjaxJson.getSuccessData(fileItemList);
    }

}
