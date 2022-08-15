package com.truing.onebox.home.controller;

import com.truing.onebox.common.utils.AjaxJson;
import com.truing.onebox.common.model.dto.Folder;
import com.truing.onebox.home.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

@RestController
@RequestMapping("/")
public class FileOperatorController {

    @Resource
    private FileService fileService;

    /**
     * 创建文件夹
     * @return
     */
    @PostMapping("mkdir")
    public AjaxJson<?> mkdir(Folder folder){

        return null;
    }

    /**
     * 删除文件
     * @param
     * @return
     */
    @PostMapping("/delete/file")
    public AjaxJson<?> deleteFile(Integer id) {
        return null;
    }

    /**
     * 删除文件夹
     * @param
     * @return
     */
    @PostMapping("/delete/folder")
    public AjaxJson<?> deleteFolder(Integer id) {
        return null;
    }

    /**
     * 重命名文件
     * @param
     * @return
     */
    @PostMapping("/rename/file")
    public AjaxJson<?> rename(Integer id, String name) {
        return null;
    }


    /**
     * 重命名文件夹
     * 重命名文件夹后是不是要修改该文件夹下所有文件的dir
     * @param
     * @return
     */
    @PostMapping("/rename/folder")
    public AjaxJson<?> renameFolder(Integer id, String name) {
        return null;
    }



    /**
     * 星标文件
     */
    @PostMapping("/starred")
    public AjaxJson<?> starredFile(Integer id){return null;}
}
