package com.truing.onebox.common.model.result;

import lombok.Data;

/**
 * 文件查询结果类
 */
@Data
public class FileItem {

    private Integer id;

    private String name;

    private String ext;

    private Integer size;

    private String type;

}
