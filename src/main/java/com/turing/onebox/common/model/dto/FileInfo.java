package com.turing.onebox.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FileInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 16:54
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
