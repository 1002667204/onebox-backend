package com.turing.onebox.admin.controller;

import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.model.dto.ClassInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<ClassInfo> classInfoList = new ArrayList<>();
        classInfoList.add(new ClassInfo(132165, "audio", "mp3,flac,wav"));
        classInfoList.add(new ClassInfo(1613652, "video", "mp4,mkv"));

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
