package com.turing.onebox.home.controller;

import com.turing.onebox.common.model.dto.RecycledInfo;
import com.turing.onebox.common.model.result.FileItem;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.home.service.RecycledService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private RecycledService recycledService;

    /**
     * 获取回收站文件列表 AjaxJson<List<FileItem>>
     * 每次获取时刷新回收站文件列表，删除过期文件
     */
    @PostMapping("/list")
    public AjaxJson<List<FileItem>> list(){

        return AjaxJson.getSuccessData(recycledService.recycledList());
    }

    /**
     * 清空回收站 AjaxJson.getSuccess()
     * 先删除服务器上的文件，再删除数据库中的记录
     */
    @PostMapping("/clear")
    public AjaxJson<?> clearRecycled(){
        recycledService.clear();
        return AjaxJson.getSuccess();
    }

    /**
     * 删除回收站中的某一个文件 AjaxJson.getSuccess()
     * 先删除服务器上的文件，再删除数据库中的记录
     */
    @PostMapping("/delete")
    public AjaxJson<?> completelyDeleteFile(Integer id){
        if (recycledService.completelyDeleteFile(id)){
            return AjaxJson.getSuccess();
        }

        return AjaxJson.getError();
    }

    /**
     * 还原文件 AjaxJson.getSuccess()
     */
    @PostMapping("/restore")
    public AjaxJson<?> restoreFile(Integer id){
        if (recycledService.restoreFile(id)){
            return AjaxJson.getSuccess();
        }
        return AjaxJson.getError();
    }
}
