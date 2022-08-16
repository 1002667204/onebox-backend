package com.truing.onebox.home.controller;

import com.truing.onebox.common.model.result.FileItem;
import com.truing.onebox.common.utils.AjaxJson;
import com.truing.onebox.common.model.dto.Folder;
import com.truing.onebox.home.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileOperatorController {

    @Resource
    private FileService fileService;

    /**
     * 创建文件夹
     * @return AjaxJson<FileItem>
     */
    @PostMapping("/mkdir")
    public AjaxJson<FileItem> mkdir(String name, Integer dir, Integer secret, String password){
        FileItem fileItem = new FileItem();
        fileItem.setId(0);
        fileItem.setName("newFolder");
        return AjaxJson.getSuccessData(fileItem);
    }

    /**
     * 删除文件
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/file")
    public AjaxJson<?> deleteFile(Integer id) {
        return null;
    }

    /**
     * 删除文件夹
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/folder")
    public AjaxJson<?> deleteFolder(Integer id) {
        return null;
    }

    /**
     * 重命名文件
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/rename/file")
    public AjaxJson<?> rename(Integer id, String newName) {
        return null;
    }


    /**
     * 重命名文件夹
     * 重命名文件夹后是不是要修改该文件夹下所有文件的dir
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/rename/folder")
    public AjaxJson<?> renameFolder(Integer id, String newName) {
        return null;
    }



    /**
     * 星标文件 AjaxJson.getSuccess()
     */
    @PostMapping("/starred")
    public AjaxJson<?> starredFile(Integer id){return null;}
}
