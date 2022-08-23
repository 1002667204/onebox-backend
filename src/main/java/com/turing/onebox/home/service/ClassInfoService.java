package com.turing.onebox.home.service;

import com.turing.onebox.admin.mapper.ClassInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HuangYuhan
 * @create 2022-08-20-17:33
 * 后缀名和类型的service层
 */
@Service
public class ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    public String queryClassNameByExt(String ext) {

        return classInfoMapper.selectClassNameByExt(ext);
    }
}
