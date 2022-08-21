package com.turing.onebox.common.model.result;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.Folder;
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
        this.id = fileInfo.getId();
        this.name = fileInfo.getName();
        this.ext = fileInfo.getExt();
        this.size = fileInfo.getSize();
        this.type = fileInfo.getType();
        this.star = fileInfo.getStar();
    }

    public FileItem(Folder folder){
        this.id = folder.getId();
        this.name = folder.getName();
        this.ext = null;
        this.size = null;
        this.type = "folder";
        this.star = folder.getStar();
    }

}
