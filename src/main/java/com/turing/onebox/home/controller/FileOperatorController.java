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
    public AjaxJson<FileItem> mkdir(String name, Integer dir, Integer secret, String password) {

        //先生成Folder对象
        Folder folder = new Folder();

        int uuid = UUIDUtils.getUUID();//UUID这里用的强转，不知道行不行
        folder.setId(uuid);
        folder.setName(name);
        folder.setDir(dir);
        folder.setSecret(secret);//若是0则password为null，若是1则有password
        folder.setInRecycled(0);//默认不在回收站
        folder.setPassword(password);
        folder.setStar(0);//默认不标星

        //调用fileService方法
        fileService.newFolder(folder);

        //fileItem结果
        FileItem fileItem = new FileItem();

        fileItem.setId(uuid);
        fileItem.setName(name);
        fileItem.setExt(null);//文件夹的扩展名不知道能不能这么写
        fileItem.setSize(null);//这里不知道咋写
        fileItem.setType("folder");
        fileItem.setStar(0);

        return AjaxJson.getSuccessData(fileItem);
    }

    /**
     * 删除文件
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/file")
    public AjaxJson<?> deleteFile(Integer id) {
        //这里应该不用判断文件是否存在吧，如果不存在id没法传进来
        return AjaxJson.getSuccessData(fileService.deleteFile(id));
    }

    /**
     * 删除文件夹
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/folder")
    public AjaxJson<?> deleteFolder(Integer id) {
        return AjaxJson.getSuccessData(fileService.deleteFolder(id));
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
            return AjaxJson.getError("NullPointNewName");
        }
        return AjaxJson.getSuccessData(fileService.renameFile(id, newName));
    }


    /**
     * 重命名文件夹
     * 重命名文件夹后是不是要修改该文件夹下所有文件的dir
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
