package com.turing.onebox.home.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName RecycledService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/14 22:20
 * @Version 1.0
 */
@Service
public class RecycledService {

    /**
     * 获取回收站文件列表
     */
    public boolean recycledList(){
        return false;
    }

    /**
     * 清空回收站
     */
    public boolean clear(){
        return false;
    }

    /**
     * 删除回收站中的某一个文件
     */
    public boolean completelyDeleteFile(Integer id){
        return false;
    }

    /**
     * 还原文件
     */
    public boolean restoreFile(Integer id){
        return false;
    }

}
