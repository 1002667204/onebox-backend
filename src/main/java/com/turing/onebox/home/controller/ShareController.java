package com.turing.onebox.home.controller;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.utils.AjaxJson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShareController
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/14 22:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/")
public class ShareController {

    /**
     * 分享文件
     * 如果分享的是文件夹怎么办？是不是需要一个打包功能
     */
    @PostMapping("/share")
    public AjaxJson<?> shareFile(FileInfo fileInfo){
        return null;
    }

    /**
     * 分享文件夹
     */
    @PostMapping()
    public AjaxJson<?> shareFolder(Folder folder){
        return null;
    }

}
