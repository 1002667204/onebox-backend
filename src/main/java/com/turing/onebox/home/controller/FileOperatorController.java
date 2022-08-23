package com.turing.onebox.home.controller;

import com.turing.onebox.common.constant.OneboxConstant;
import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.common.utils.UUIDUtils;
import com.turing.onebox.home.mapper.FolderMapper;
import com.turing.onebox.home.service.FileService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin
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
        folder.setId(UUIDUtils.getUUID());
        folder.setName(name);
        folder.setDir(dir);
        folder.setSecret(secret);
        folder.setInRecycled(OneboxConstant.NOT_IN_RECYCLED);
        folder.setPassword(password);
        folder.setStar(OneboxConstant.IS_NOT_STARRED);
        folder.setCreateTime(DateUtils.formateDateTime(new Date()));

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
        FileInfo fileInfo = fileService.queryFileById(id);
        if (Objects.equals(fileInfo.getInRecycled(), OneboxConstant.NOT_IN_RECYCLED)) {
            if (fileService.deleteFile(id)) {
                return AjaxJson.getSuccess("删除成功");
            }
        } else {
            if (fileService.removeFile(id)) {
                return AjaxJson.getSuccess("删除成功");
            }
        }
        return AjaxJson.getError("删除失败");
    }
    /**
     * 删除文件夹
     * @param
     * @return AjaxJson.getSuccess()
     */
    @PostMapping("/delete/folder")
    public AjaxJson<?> deleteFolder(Integer id) {
        Folder folder = fileService.queryFolderById(id);
        if (Objects.equals(folder.getId(), OneboxConstant.NOT_IN_RECYCLED)) {
            if (fileService.deleteFolder(id)) {
                return AjaxJson.getSuccess("删除成功");
            }
        } else {
            if (fileService.removeFolder(id)) {
                return AjaxJson.getSuccess("删除成功");
            }
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
        public AjaxJson<?> starredFile (Integer id, Integer starred, Integer type){
            if (type == 1) {
                fileService.starredFolder(id, starred);
                if (starred == 0) return AjaxJson.getSuccess("取消星标成功");
                if (starred == 1) return AjaxJson.getSuccess("设置星标成功");

            } else {
                fileService.starredFile(id, starred);
                if (starred == 0) return AjaxJson.getSuccess("取消星标成功");
                if (starred == 1) return AjaxJson.getSuccess("设置星标成功");
            }
            return AjaxJson.getError("设置星标状态失败");
        }

    }
