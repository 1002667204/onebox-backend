package com.turing.onebox.home.service;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.RecycledInfo;
import com.turing.onebox.common.model.dto.StarredInfo;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.common.utils.TransUtils;
import com.turing.onebox.common.utils.UUIDUtils;
import com.turing.onebox.home.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import com.turing.onebox.home.mapper.FileInfoMapper;

import org.springframework.stereotype.Service;



import java.io.File;

import java.util.*;


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
     * 获取指定Dir下所有的文件和文件夹
     *
     */
    public List<FileItem> queryFileItemByDir(Integer dir){
        List<FileInfo> fileInfos = fileInfoMapper.selectFileByDir(dir);
        List<Folder> folders = folderMapper.selectFolderByDir(dir);

        if (fileInfos == null && folders == null){
            return null;
        }
        List<FileItem> fileItems = new ArrayList<>();
        if (folders != null){
            for (Folder folder:folders){
                fileItems.add(new FileItem(folder));
            }
        }
        if (fileInfos != null){
            for (FileInfo fileInfo:fileInfos){
                fileItems.add(new FileItem(fileInfo));
            }
        }
        return fileItems;

    }
    public FileInfo queryFileById(Integer id){
        return fileInfoMapper.selectByPrimaryKey(id);
    }
    public Folder queryFolderById(Integer id){
        return folderMapper.selectFolderById(id);
    }
    /**
     *
     */
    public List<FileItem> queryFileByType(String type){

        List<FileInfo> fileInfos = fileInfoMapper.selectFileByType(type);
        return TransUtils.fileInfoTransToFileItem(fileInfos);
    }

    public Folder queryFolderByIdAndPassword(Map<String,Object> map){
        return folderMapper.selectFolderByIdAndPassword(map);

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
     * 新建文件夹
     * TODO
     * @param folder
     * @return
     */
    public boolean newFolder(Folder folder) {
        // 判断当前目录下是否有同名文件夹
        if (fileInfoMapper.selectFolderByNameAndDir(folder) != null){
            return false;
        }
        // 新增文件夹
        return folderMapper.insert(folder) == 1;
    }


    /**
     * 删除文件(将文件移入回收站)
     * @param id
     * @return
     */
    public boolean deleteFile(Integer id) {
        // 设置文件的in_recycled字段为1
        if (fileInfoMapper.editFileRecycledByFileId(id) == 0) return false;
        // 在回收站中增加记录
        RecycledInfo recycledInfo = new RecycledInfo(id);
        return recycledInfoMapper.insert(recycledInfo) == 1;
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
        recycledInfoMapper.deleteByFileId(id);
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
        recycledInfoMapper.deleteByFileId(id);
        // 彻底删除文件夹内的文件
        int count = 0;
        for (FileInfo fileInfo : fileInfoMapper.getFileInfoByDirId(id)) {
            if (removeFile(fileInfo.getId())) count++;
        }
        // 删除文件夹内的文件夹
        List<Folder> folders = folderMapper.getFolderByDirId(id);
        if (folders != null){
            for (Folder folder: folders){
                if (removeFolder(folder.getId())) count++;
            }
        }
        return count>0;

    }

    /**
     * 文件重命名
     * 如果文件名没变，不做任何操作
     * @param id
     * @param
     * @return
     */
    public boolean renameFile(Integer id, String newName) {
        FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(id);
        //先判断 文件名 是否改变
        if (newName.equals(fileInfo.getName())) {
            return false;
        }
        return fileInfoMapper.renameFile(id, newName)==1;
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
        fileInfo.setStar(starred);
        if (fileInfoMapper.updateByPrimaryKey(fileInfo) == 0) return false;
        // 如果星标状态为1则在starred表中添加记录，星标状态为0在starred表中删除记录
        if (starred == 1){
            return fileInfoMapper.starredFile(fileInfo) == 1;
        } else {
            return fileInfoMapper.unStarredFile(id) == 1;
        }
    }

    /**
     * 设置星标文件夹
     */
    public boolean starredFolder(Integer id, Integer starred){
        // 获取文件夹星标状态
        Folder folder = folderMapper.selectFolderById(id);
        if (Objects.equals(folder.getStar(), starred)) return false;
        // 修改文件夹星标字段
        folder.setStar(starred);
        if (folderMapper.updateByPrimaryKey(folder) == 0) return false;
        // 如果星标状态为1则在starred表中添加记录，星标状态为0在starred表中删除记录
        if (starred == 1){
            StarredInfo starredInfo = new StarredInfo();
            starredInfo.setId(folder.getId());
            starredInfo.setName(folder.getName());
            starredInfo.setDir(folder.getDir());
            if (folder.getSecret() == 1){
                starredInfo.setType("sercetFolder");
            } else {
                starredInfo.setType("folder");
            }
            starredInfo.setInRecycled(folder.getInRecycled());
            starredInfo.setCreateTime(folder.getCreateTime());
            return starredInfoMapper.insert(starredInfo) == 1;
        } else {
            return starredInfoMapper.deleteByPrimaryKey(id) == 1;
        }
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

    /**
     * 修改对应文件的dir属性
     */
    public boolean editDirByFileId(Integer sourceId,Integer targetId){
        FileInfo fileInfo = queryFileById(sourceId);
        //源Id是一个文件时
        if (fileInfo != null){
            //修改文件列表中的dir属性
            return fileInfoMapper.updateDirByFileId(sourceId,targetId) == 1;

        }else {
            //修改文件列表中的dir属性
            return folderMapper.updateDirByFolderId(sourceId,targetId) == 1;
        }

    }
}
