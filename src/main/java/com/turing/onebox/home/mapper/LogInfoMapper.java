package com.turing.onebox.home.mapper;

import com.turing.onebox.common.model.dto.LogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    List<LogInfo> selectAll();

    int updateByPrimaryKey(LogInfo record);
}