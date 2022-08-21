package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.StarredInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface StarredInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StarredInfo record);

    StarredInfo selectByPrimaryKey(Integer id);

    List<StarredInfo> selectAll();

    int updateByPrimaryKey(StarredInfo record);
}