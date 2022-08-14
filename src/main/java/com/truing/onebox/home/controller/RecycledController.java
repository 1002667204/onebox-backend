package com.truing.onebox.home.controller;

import com.truing.onebox.common.model.dto.RecycledInfo;
import com.truing.onebox.common.utils.AjaxJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RecycledController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/14 22:19
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/recycled")
public class RecycledController {

    /**
     * 获取回收站文件列表
     */
    @PostMapping("/list")
    public AjaxJson<?> list(){
        return null;
    }

    /**
     * 清空回收站
     */
    @PostMapping("/clear")
    public AjaxJson<?> clearRecycled(){
        return null;
    }

    /**
     * 删除回收站中的某一个文件
     */
    @PostMapping("/delete")
    public AjaxJson<?> completelyDeleteFile(RecycledInfo recycledInfo){
        return null;
    }

    /**
     * 还原文件
     */
    @PostMapping("/restore")
    public AjaxJson<?> restoreFile(RecycledInfo recycledInfo){
        return null;
    }
}
