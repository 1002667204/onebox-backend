package com.truing.onebox.home.model.dto;

import lombok.Data;

/**
 * @ClassName ShareInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:03
 * @Version 1.0
 */
@Data
public class ShareInfo {

    private Integer id;

    private String readPath;

    private String expireTime;

    private String shortLink;

}
