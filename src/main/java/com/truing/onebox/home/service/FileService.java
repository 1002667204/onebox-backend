package com.truing.onebox.home.service;

import com.truing.onebox.home.model.dto.FileItem;
import com.truing.onebox.home.model.dto.Folder;
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


    public boolean newFolder(Folder folder) {
        return false;
    }



    public boolean deleteFile(Integer id) {
        return false;
    }



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




    public void uploadFile(String path, InputStream inputStream) {
        return;
    }



    public void downloadToStream(String pathAndName) {
    }


    public boolean starredFile(Integer id){
        return false;
    }
}
