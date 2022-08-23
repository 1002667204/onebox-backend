package com.turing.onebox.home.controller;


import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.home.service.FileService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
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
@CrossOrigin
public class FileController {

    @Resource
    private FileService fileService = new FileService();

    /**
     * 获取文件列表
     * 获取dir为参数的所有文件和文件夹
     *
     * @Return AjaxJson.getSuccessData(fileItemList)
     */
    @PostMapping("/files")
    public AjaxJson<?> list(Integer id) {
        //没有name的话报错
        if (id != null) {
           List<FileItem> fileItems = fileService.queryFileItemByDir(id);
            return AjaxJson.getSuccessData(fileItems);
        } else {
            return AjaxJson.getError("未找到文件");
        }

    }

    /**
     * 根据类型获取文件
     *
     */
    @PostMapping("/type")
    public AjaxJson<?> listFileByType(String type){
        List<FileItem> fileItems = fileService.queryFileByType(type);
        return AjaxJson.getSuccessData(fileItems);
    }


    /**
     * 获取星标文件列表
     *
     * @Return AjaxJson.getSuccessData(fileItemList)
     */
    @PostMapping("/starredFiles")
    public AjaxJson<?> listStarred() {

        List<FileItem> fileItemList = fileService.starredList();

        //如果没有星标文件就返回信息
        if(fileItemList != null){
            return AjaxJson.getSuccessData(fileItemList);
        }else {
            return AjaxJson.getError("NullStarredFile");
        }
    }

}
