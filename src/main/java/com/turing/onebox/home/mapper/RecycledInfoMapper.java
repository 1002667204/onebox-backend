package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.RecycledInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecycledInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByFileId(Integer id);

    RecycledInfo selectByFileId(Integer id);

    int insert(RecycledInfo record);

    RecycledInfo selectByPrimaryKey(Integer id);

    List<RecycledInfo> selectAll();

    int updateByPrimaryKey(RecycledInfo record);
}