package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.Folder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Folder record);

    Folder selectByPrimaryKey(Integer id);

    List<Folder> selectAll();

    int updateByPrimaryKey(Folder record);
}