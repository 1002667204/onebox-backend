package com.truing.onebox.common.model.dto;

import lombok.Data;

/**
 * @ClassName FileInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 16:54
 * @Version 1.0
 */
@Data
public class FileInfo {

    private Integer id;

    private String name;

    private Integer dir;

    private String type;

    private String ext;

    private Integer size;

    private String realPath;

    private Integer inRecycled;

    private Integer star;

}
