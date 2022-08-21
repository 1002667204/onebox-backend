package com.turing.onebox.home.service;

import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.home.mapper.FileInfoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * 获取文件列表
     * @param name 文件夹名
     * @return 
     */
    public List<FileItem> fileList(String name) {
        return fileInfoMapper.fileList(name);
    }

    /**
     * 获取星标文件列表
     * @return
     */
    public List<FileItem> starredList() {
        //调用mapper方法
        return fileInfoMapper.starredList();
    }

    /**
     * 新建文件夹
     * @param folder
     * @return
     */
    public boolean newFolder(Folder folder) {
        return fileInfoMapper.newFolder(folder);
    }


    /**
     * 删除文件(将文件移入回收站)
     * @param id
     * @return
     */
    public boolean deleteFile(Integer id) {
        return fileInfoMapper.deleteFile(id);
    }

    /**
     * 删除文件(将文件从本地删除)
     */


    /**
     * 删除文件夹()
     * @param id
     * @return
     */
    public boolean deleteFolder(Integer id) {
        return fileInfoMapper.deleteFolder(id);
    }


    /**
     * 文件重命名
     * 如果文件名没变，不做任何操作
     * @param id
     * @param
     * @return
     */
    public boolean renameFile(Integer id, String newName) {
        //先判断 文件名 是否改变
        if (newName.equals(fileInfoMapper.getFileNameById(id))) {
            return false;
        }

        return fileInfoMapper.renameFile(id, newName);
    }

    /**
     * 重命名文件夹
     * @param newName
     * @return
     */
    public boolean renameFolder(Integer id, String newName) {
        //先判断 文件名 是否改变
        if (newName.equals(fileInfoMapper.getFolderNameById(id))) {
            return false;
        }
        return fileInfoMapper.renameFolder(id, newName);
    }


    /**
     * 设置星标文件
     * @param id
     * @return
     */
    public boolean starredFile(Integer id){
        return fileInfoMapper.starredFile(id);
    }

}
