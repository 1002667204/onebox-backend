package com.turing.onebox.home.controller;

import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.home.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping("/list")
    public AjaxJson<?> getLog(){
        return  AjaxJson.getSuccessData(logService.queryAllLog());
    }
}
