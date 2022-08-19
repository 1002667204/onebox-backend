package com.turing.onebox.admin.mapper;

import com.turing.onebox.common.model.dto.ClassInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SettingMapper
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/18 20:52
 * @Version 1.0
 **/
@Repository
public interface SettingMapper {

    List<ClassInfo> selectAllClassInfo();

    Integer updateClassInfo(@Param("id") Integer id, @Param("className") String className, @Param("ext") String ext);

}
