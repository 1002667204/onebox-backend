package com.turing.onebox.admin.service;

import com.turing.onebox.admin.mapper.ClassInfoMapper;
import com.turing.onebox.admin.mapper.SettingMapper;
import com.turing.onebox.common.model.dto.ClassInfo;
import com.turing.onebox.common.model.result.SettingItem;
import com.turing.onebox.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SettingService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    /**
     * 获取文件分类表
     */
    public List<ClassInfo> getClassList() {
        return classInfoMapper.selectAll();
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
//    @Transactional(rollbackFor = Exception.class)
    public boolean updateClassList(List<ClassInfo> newClassInfoList){
        int sum = 0;
        // 清空config_class表
        Integer i = classInfoMapper.clearClassInfo();
        // 更新config_class表
        for (ClassInfo classInfo: newClassInfoList) {
            classInfo.setId(UUIDUtils.getUUID());
            int current = classInfoMapper.updateClassInfo(classInfo);
            sum += current;
        }
        if (sum == newClassInfoList.size()){
            return true;
        } else {
            // 抛出异常？
            return false;
        }
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
