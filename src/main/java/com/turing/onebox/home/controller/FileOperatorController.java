package com.turing.onebox.home.controller;

import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.utils.UUIDUtils;
import com.turing.onebox.home.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class FileOperatorController {

    @Resource
    private FileService fileService;

    /**
     * 创建文件夹
     *
     * @return AjaxJson<FileItem>
     */
    @PostMapping("/mkdir")
    public AjaxJson<?> mkdir(String name, Integer dir, Integer secret, String password) {

        //先生成Folder对象
        Folder folder = new Folder();

        int uuid = UUIDUtils.getUUID();
        folder.setId(uuid);
        folder.setName(name);
        folder.setDir(dir);
        folder.setSecret(secret);
        folder.setInRecycled(0);
        folder.setPassword(password);
        folder.setStar(0);

        if (fileService.newFolder(folder)) {
            return AjaxJson.getSuccessData(new FileItem(folder));
        } else {
            return AjaxJson.getError("文件夹创建失败");
        }

    }

    /**
     * 删除文件
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/file")
    public AjaxJson<?> deleteFile(Integer id) {
        if (fileService.deleteFile(id)) {
            return AjaxJson.getSuccess("删除成功");
        } else {
            return AjaxJson.getError("删除失败，请检查文件是否存在");
        }
    }

    /**
     * 删除文件夹
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/folder")
    public AjaxJson<?> deleteFolder(Integer id) {
        if (fileService.deleteFolder(id)){
            return AjaxJson.getSuccess();
        } else {
            return AjaxJson.getError("删除失败");
        }
    }

    /**
     * 重命名文件
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/rename/file")
    public AjaxJson<?> rename(Integer id, String newName) {
        //先判断newName是否为空
        if(newName == null || newName.equals("")){
            return AjaxJson.getError("重命名失败，文件名不能为空");
        }

        if (fileService.renameFile(id, newName)){
            return AjaxJson.getSuccess();
        } else {
            return AjaxJson.getError("重命名失败");
        }

    }


    /**
     * 重命名文件夹
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/rename/folder")
    public AjaxJson<?> renameFolder(Integer id, String newName) {
        //先判断newName是否为空
        if(newName == null || newName.equals("")){
            return AjaxJson.getError("NullPointNewName");
        }
        return AjaxJson.getSuccessData(fileService.renameFolder(id, newName));
    }



    /**
     * 设置文件星标状态 AjaxJson.getSuccess()
     */
    @PostMapping("/starred")
    public AjaxJson<?> starredFile(Integer id, Integer starred){
        return AjaxJson.getSuccessData(fileService.starredFile(id));
    }

}
