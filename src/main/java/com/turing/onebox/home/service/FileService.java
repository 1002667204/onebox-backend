package com.turing.onebox.home.service;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.RecycledInfo;
import com.turing.onebox.common.model.dto.StarredInfo;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.common.utils.UUIDUtils;
import com.turing.onebox.home.mapper.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.turing.onebox.home.mapper.FileInfoMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class FileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private StarredInfoMapper starredInfoMapper;

    @Autowired
    private FolderMapper folderMapper;

    @Autowired
    private RecycledInfoMapper recycledInfoMapper;

    /**
     * 获取文件列表
     * @param dir 当前文件夹
     * @return 
     */
    public List<FileInfo> fileList(Integer dir) {
        return fileInfoMapper.seleteFileByDir(dir);
    }

    /**
     * 获取星标文件列表
     * @return
     */
    public List<FileItem> starredList() {
        List<StarredInfo> fileInfoList = starredInfoMapper.selectAll();
        // 将文件列表转换为结果类
        List<FileItem> fileItemList = new ArrayList<>();
        for (StarredInfo fileInfo : fileInfoList){
            fileItemList.add(new FileItem(fileInfo));
        }
        return fileItemList;
    }

    /**
     * 将文件列表转换为结果类
     */
    public List<FileItem> transIntoFileItem(List<FileInfo> fileInfoList){
        List<FileItem> fileItemList = new ArrayList<>();
        for (FileInfo fileInfo : fileInfoList){
            fileItemList.add(new FileItem(fileInfo));
        }
        return fileItemList;
    }

    /**
     * 新建文件夹
     * TODO
     * @param folder
     * @return
     */
    public boolean newFolder(Folder folder) {
        // 判断当前目录下是否有同名文件夹
        if (fileInfoMapper.selectFolderByNameAndDir(folder) != 0){
            return false;
        }
        // 新增文件夹
        return fileInfoMapper.newFolder(folder) == 1;
    }


    /**
     * 删除文件(将文件移入回收站)
     * @param id
     * @return
     */
    public boolean deleteFile(Integer id) {
        // 设置文件的in_recycled字段为1
        if (fileInfoMapper.deleteFile(id) == 0) return false;
        // 在回收站中增加记录
        Date deleteTime = new Date();
        RecycledInfo recycledInfo = new RecycledInfo(UUIDUtils.getUUID(), DateUtils.formateDateTime(deleteTime), DateUtils.future30Days(new Date()), id);
        return fileInfoMapper.newRecycledInfo(recycledInfo) == 1;
    }

    /**
     * 删除文件(将文件从本地删除，同时删除表中的记录)
     *
     */
    public boolean removeFile(Integer id){
        // 获取文件表中的记录并删除
        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);
        if (fileInfo == null) return false;
        String path = fileInfo.getRealPath();
        // 删除文件表中的记录
        if (fileInfoMapper.deleteByPrimaryKey(id) == 0) return false;
        // 删除回收站中的记录
        if (recycledInfoMapper.deleteByFileId(id) == 0) return false;
        // 删除本地文件
        File file = new File(path);
        return file.delete();
    }


    /**
     * 删除文件夹(删除后更新文件列表)
     * @param id
     * @return
     */
    public boolean deleteFolder(Integer id) {
        if (0 ==folderMapper.editFolderRecycledById(id)){
            return false;
        }else {
            fileInfoMapper.editFileRecycledByFolderId(id);
            RecycledInfo recycledInfo = new RecycledInfo(id);
            recycledInfoMapper.insert(recycledInfo);
            return true;
        }
    }

    /**
     * 彻底删除文件夹(删除文件夹表中的记录，同时彻底删除该文件夹内的文件和文件夹)
     * TODO
     */
    public boolean removeFolder(Integer id){
        // 删除文件夹表中的记录
        if (folderMapper.deleteByPrimaryKey(id) == 0) return false;
        // 彻底删除文件夹内的文件
        int count = 0;
        for (FileInfo fileInfo : fileInfoMapper.seleteFileByDir(id)) {
            if (removeFile(fileInfo.getId())) count++;
        }
        return (count > 0);

        // 删除文件夹内的文件夹

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
        return fileInfoMapper.renameFile(id, newName) == 1;
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
        return fileInfoMapper.renameFolder(id, newName) == 1;
    }


    /**
     * 上传文件
     *
     * @param
     * @Author HuangYuhan
     * @Description 上传文件，controller层传输一个FileInfo文件类
     * 然后Dao层将该文件insert进数据库
     */
    public boolean uploadFile(FileInfo file) {
        int total = fileInfoMapper.insert(file);
        if (total == 1) {
            return true;
        }else {
            return false;
        }

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
    public boolean starredFile(Integer id, Integer starred){

        // 获取文件当前星标状态
        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);
        if (Objects.equals(fileInfo.getStar(), starred)) return false;
        // 修改文件星标字段
        if (fileInfoMapper.updateByPrimaryKey(fileInfo) == 0) return false;
        // 在starred表中添加记录
        fileInfo.setStar(starred);
        return fileInfoMapper.starredFile(fileInfo) == 1;
    }



    /**
     * 根据文件的名字和所属父类文件查询是否重命名
     * @Author HuangYuhan
     * @param fileName
     * @param dir
     * @return
     */
    public boolean queryFileByFileNameAndDir(String fileName, Integer dir) {

        int total = fileInfoMapper.selectFileByFileNameAndDir(fileName, dir);
        if (total == 0) {
//            只有不存在这个文件，才是true
            return true;
        }else{
            return false;
        }
    }
}
