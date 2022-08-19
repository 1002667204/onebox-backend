package com.turing.onebox.admin.controller;

import com.turing.onebox.admin.service.SettingService;
import com.turing.onebox.common.model.result.SettingItem;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.model.dto.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    /**
     * 获取文件分类表
     * @return AjaxJson<List<SettingItem>>
     */
    @PostMapping("/class")
    public AjaxJson<List<ClassInfo>> list(){
        List<ClassInfo> classInfoList = settingService.getClassList();
        return AjaxJson.getSuccessData(classInfoList);
    }


    // 每次修改设置后前端都返回整个文件分类表

//    /**
//     * 添加后缀
//     */
//    @PostMapping("/ext/add")
//    public AjaxJson<?> addExt(Integer id, String newExt){
//        return null;
//    }
//
//    /**
//     * 删除后缀
//     */
//    @PostMapping("/ext/delete")
//    public AjaxJson<?> deleteExt(Integer id, String newExt){
//        return null;
//    }


}
