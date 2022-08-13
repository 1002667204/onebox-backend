package com.truing.onebox.home.model.dto;

import lombok.Data;

/**
 * @ClassName LogInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:01
 * @Version 1.0
 */
@Data
public class LogInfo {

    private Integer id;

    private Integer method;

    private String fileName;

    private String modifyTime;

}
