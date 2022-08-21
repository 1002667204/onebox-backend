package com.turing.onebox.home.controller;

import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.home.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private FileService fileService = new FileService();

    /**
     * 获取文件列表
     *
     * @Return AjaxJson.getSuccessData(fileItemList)
     */
    @PostMapping("/files")
    public AjaxJson<?> list(Integer id) {
        //没有name的话报错
        if (id != null) {
            List<FileItem> fileItemList = fileService.transIntoFileItem(fileService.fileList(id));
            return AjaxJson.getSuccessData(fileItemList);
        } else {
            return AjaxJson.getError("NullPointFolderName");
        }

    }


    /**
     * 获取星标文件列表
     *
     * @Return AjaxJson.getSuccessData(fileItemList)
     */
    @PostMapping("/starredfiles")
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
