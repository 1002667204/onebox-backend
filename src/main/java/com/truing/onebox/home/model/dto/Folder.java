package com.truing.onebox.home.model.dto;

import lombok.Data;

/**
 * @ClassName Folder
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 16:58
 * @Version 1.0
 */
@Data
public class Folder {

    private Integer id;

    private String name;

    private String dir;

    private Integer secret;

    private Integer inRecycled;

    private String password;

}
