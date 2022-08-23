package com.turing.onebox.home.service;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.model.dto.RecycledInfo;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.home.mapper.FileInfoMapper;
import com.turing.onebox.home.mapper.FolderMapper;
import com.turing.onebox.home.mapper.RecycledInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName RecycledService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/14 22:20
 * @Version 1.0
 */
@Service
public class RecycledService {

    @Resource
    private RecycledInfoMapper recycledInfoMapper;

    @Resource
    private FileInfoMapper fileInfoMapper;
    @Resource
    private FolderMapper folderMapper;

    @Resource
    private FileService fileService;
    /**
     * 获取回收站文件列表
     */
    public List<FileItem> recycledList(){
        List<RecycledInfo> recycledInfos;
        recycledInfos = recycledInfoMapper.selectAll();
        System.out.println(recycledInfos);
        if (recycledInfos.isEmpty()){
            return null;
        }else {
            Iterator<RecycledInfo> iter = recycledInfos.iterator();
            List<FileItem> fileItems = new ArrayList<>();
            while (iter.hasNext()){
                RecycledInfo recycledInfo = iter.next();
                //判断日期是否过期
                if ((DateUtils.formateDateTime(new Date()).compareTo(recycledInfo.getDestroyTime()))>=0){
                    Integer id = recycledInfo.getId();
                    //删除回收站记录
                    recycledInfoMapper.deleteByPrimaryKey(id);
                    //删除文件夹表和文件表记
                    //缺少删除实际文件方法
                    if (1 == fileInfoMapper.deleteByPrimaryKey(recycledInfo.getFileId())){
                        fileService.removeFile(id);
                    }else {
                        folderMapper.deleteByPrimaryKey(recycledInfo.getFileId());
                        fileService.removeFolder(id);
                    }

                    //删除返回值记录
                    iter.remove();
                }else {
                    FileInfo fileInfo = fileInfoMapper.selectByPrimaryKey(recycledInfo.getFileId());
                    if (fileInfo != null){
                        fileItems.add(new FileItem(fileInfo));
                    }else {
                        Folder folder = folderMapper.selectByPrimaryKey(recycledInfo.getFileId());
                        System.out.println(folder);
                        fileItems.add(new FileItem(folder));
                    }
                }

            }
            return fileItems;
        }
    }

    /**
     * 清空回收站
     */
    public void clear(){
        List<RecycledInfo> recycledInfos;
        recycledInfos = recycledInfoMapper.selectAll();
        if (!recycledInfos.isEmpty()){
            for (RecycledInfo recycledInfo:recycledInfos){
                //删除回收站记录
                recycledInfoMapper.deleteByPrimaryKey(recycledInfo.getId());
                //删除文件夹表和文件表记录
                //缺少删除实际文件方法
                if (1 == fileInfoMapper.deleteByPrimaryKey(recycledInfo.getFileId())){
                    fileService.removeFile(recycledInfo.getFileId());
                }else {
                    folderMapper.deleteByPrimaryKey(recycledInfo.getFileId());
                    fileService.removeFolder(recycledInfo.getFileId());
                }
            }
        }
    }

    /**
     * 删除回收站中的某一个文件
     */
    public boolean completelyDeleteFile(Integer id){
        RecycledInfo recycledInfo = recycledInfoMapper.selectByFileId(id);

        if (recycledInfo != null){
            //删除回收站记录
            recycledInfoMapper.deleteByPrimaryKey(recycledInfo.getId());
            //删除文件夹表和文件表记录
            //缺少删除实际文件方法
            if (1 == fileInfoMapper.deleteByPrimaryKey(recycledInfo.getFileId())){
                fileService.removeFile(recycledInfo.getFileId());
            }else {
                folderMapper.deleteByPrimaryKey(recycledInfo.getFileId());
                fileService.removeFolder(recycledInfo.getFileId());
            }

            return true;
        }
        return false;
    }

    /**
     * 还原文件
     */
    public boolean restoreFile(Integer id){

        if (1 != fileInfoMapper.editFileRecycledByFileId(id)){
            folderMapper.editFolderRecycledById(id);
        }
        return 0 != recycledInfoMapper.deleteByFileId(id);
    }

}
