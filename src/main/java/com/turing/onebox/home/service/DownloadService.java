package com.turing.onebox.home.service;

import com.turing.onebox.common.model.dto.FileInfo;

import com.turing.onebox.home.mapper.FileInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


/**
 * @ClassName DownloadService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 15:36
 * @Version 1.0
 */
@Service
public class DownloadService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    //根据文件id查询文件
    public FileInfo queryFileById(Integer id){
        return fileInfoMapper.selectByPrimaryKey(id);

    }



}
