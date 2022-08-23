package com.turing.onebox.common.utils;

import com.turing.onebox.common.model.dto.FileInfo;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.model.result.FileItem;

import java.util.ArrayList;
import java.util.List;

public class TransUtils {
    public static List<FileItem> fileInfoTransToFileItem(List<FileInfo> fileInfos){
        List<FileItem> fileItems = new ArrayList<>();
        for (FileInfo fileInfo : fileInfos){
            fileItems.add(new FileItem(fileInfo));
        }
        return fileItems;

    }
    public static List<FileItem> folderTransToFileItem(List<Folder> folders){
        List<FileItem> fileItems = new ArrayList<>();
        for (Folder folder : folders){
            fileItems.add(new FileItem(folder));
        }
        return fileItems;
    }
}
