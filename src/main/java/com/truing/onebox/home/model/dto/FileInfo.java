package com.truing.onebox.home.model.dto;

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

    private String dir;

    private String type;

    private String ext;

    private Integer size;

    private String realPath;

    private Integer inRecycled;

}
