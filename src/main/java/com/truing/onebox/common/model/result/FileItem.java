package com.truing.onebox.common.model.result;

import com.truing.onebox.common.model.dto.FileInfo;
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

    private String type; // audio video folder sercetFolder

    private Integer star;

    public FileItem(){}

    public FileItem(FileInfo fileInfo){
        this.name = fileInfo.getName();
    }

}
