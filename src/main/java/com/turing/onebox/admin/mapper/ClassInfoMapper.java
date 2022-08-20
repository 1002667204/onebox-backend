package com.turing.onebox.admin.mapper;

import com.turing.onebox.common.model.dto.ClassInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer id);

    List<ClassInfo> selectAll();

    int updateByPrimaryKey(ClassInfo record);

    List<ClassInfo> selectAllClassInfo();

    Integer updateClassInfo(ClassInfo classInfo);

    Integer clearClassInfo();
}