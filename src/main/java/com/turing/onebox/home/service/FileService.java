package com.turing.onebox.home.service;

import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.model.dto.Folder;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;


@Service
public class FileService {

    /**
     * 获取文件列表
     * @param name 文件夹名
     * @return 
     */
    public List<FileItem> fileList(String name) {
        return null;
    }

    /**
     * 新建文件夹
     * @param folder
     * @return
     */
    public boolean newFolder(Folder folder) {
        return false;
    }


    /**
     * 删除文件
     * @param id
     * @return
     */
    public boolean deleteFile(Integer id) {
        return false;
    }


    /**
     * 删除文件夹
     * @param id
     * @return
     */
    public boolean deleteFolder(Integer id) {
        return false;
    }


    /**
     * 文件重命名
     * 如果文件名没变，不做任何操作
     * @param id
     * @param
     * @return
     */
    public boolean renameFile(Integer id, String newName) {
        return false;
    }


    /**
     * 重命名文件夹
     * @param newName
     * @return
     */
    public boolean renameFolder(Integer id, String newName) {
        return false;
    }


    /**
     * 上传文件
     * @param path
     * @param inputStream
     */
    public void uploadFile(String path, InputStream inputStream) {
        return;
    }


    /**
     * 下载文件
     * @param pathAndName
     */
    public void downloadToStream(String pathAndName) {
    }

    /**
     * 设置星标文件
     * @param id
     * @return
     */
    public boolean starredFile(Integer id){
        return false;
    }
}
