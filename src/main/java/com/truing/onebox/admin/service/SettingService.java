package com.truing.onebox.admin.service;

import com.truing.onebox.home.model.dto.ClassInfo;
import com.truing.onebox.home.model.dto.FileItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    /**
     * 获取文件分类表
     */
    public List<ClassInfo> classList() {
        return null;
    }

    /**
     * 添加类别
     */
    public boolean addClass(){
        return false;
    }

    /**
     * 删除类别
     */
    public boolean deleteClass(){
        return false;
    }

    /**
     * 添加后缀
     */
    public boolean addExt(){
        return false;
    }

    /**
     * 删除后缀
     */
    public boolean deleteExt(){
        return false;
    }
}
