package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.Folder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface FolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Folder record);

    Folder selectByPrimaryKey(Integer id);

    List<Folder> selectAll();

    int updateByPrimaryKey(Folder record);

    int updateByFolderId(Integer id);
}