package com.turing.onebox.admin.service;

import com.turing.onebox.common.model.dto.ClassInfo;
import com.turing.onebox.common.model.result.SettingItem;
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
     * 将文件分类表转换为设置结果类
     */
    public List<SettingItem> transforToSettingItem(List<ClassInfo> classInfoList){
        return null;
    }

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
