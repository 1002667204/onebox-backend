package com.turing.onebox.admin.service;

import com.turing.onebox.admin.mapper.SettingMapper;
import com.turing.onebox.common.model.dto.ClassInfo;
import com.turing.onebox.common.model.result.SettingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService {

    @Autowired
    private SettingMapper settingMapper;

    /**
     * 获取文件分类表
     */
    public List<ClassInfo> getClassList() {
        return settingMapper.selectAllClassInfo();
    }

//    /**
//     * 将文件分类表转换为设置结果类
//     */
//    public List<SettingItem> transforToSettingItem(List<ClassInfo> classInfoList){
//        return null;
//    }

    /**
     * 更新文件分类表
     */
    public boolean modify(){
        return false;
    }

//    /**
//     * 添加后缀
//     */
//    public boolean addExt(){
//        return false;
//    }
//
//    /**
//     * 删除后缀
//     */
//    public boolean deleteExt(){
//        return false;
//    }
}
