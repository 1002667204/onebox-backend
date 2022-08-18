package com.turing.onebox.common.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 返回uuid的值
     * @return
     */
    public static Integer getUUID(){
//        return UUID.randomUUID().toString().replaceAll("-","");
        return UUID.randomUUID().hashCode();
    }
}