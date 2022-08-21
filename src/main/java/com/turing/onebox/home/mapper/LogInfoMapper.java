package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.LogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    List<LogInfo> selectAll();

    int updateByPrimaryKey(LogInfo record);
}