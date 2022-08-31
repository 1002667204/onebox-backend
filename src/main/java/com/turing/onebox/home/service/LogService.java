package com.turing.onebox.home.service;

import com.turing.onebox.common.model.dto.LogInfo;
import com.turing.onebox.home.mapper.LogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.LogManager;

/**
 * @ClassName LogService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/14 22:16
 * @Version 1.0
 */
@Service
public class LogService {

    @Autowired
    private LogInfoMapper logInfoMapper;
    /**
     * 添加一条修改记录
     */
    public boolean addLogInfo(LogInfo logInfo){
        int total = logInfoMapper.insert(logInfo);
        if (total == 1) {
            return true;
        }else {
            return false;
        }

    }

    /**
     * 获取所有的操作日志
     * @return
     */
    public List<LogInfo> queryAllLog(){
        return logInfoMapper.selectAll();
    }



}
