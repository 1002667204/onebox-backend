package com.truing.onebox.admin.controller;

import com.truing.onebox.common.utils.AjaxJson;
import com.truing.onebox.common.model.dto.ClassInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setting")
public class SettingController {

    /**
     * 获取文件分类表
     * @return AjaxJson<List<ClassInfo>>
     */
    @PostMapping("/class")
    public AjaxJson<List<ClassInfo>> list(){
        return null;
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
