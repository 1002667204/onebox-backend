package com.turing.onebox.admin.service;

import com.turing.onebox.common.constant.OneboxConstant;
import com.turing.onebox.common.model.dto.Folder;
import com.turing.onebox.common.utils.AjaxJson;
import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.home.mapper.FolderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * @ClassName InitService
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/23 22:29
 * @Version 1.0
 */
@Service
public class InitService {

    @Autowired
    private FolderMapper folderMapper;

    /**
     * 检查根目录并创建
     * @return
     */
    public boolean checkRootDir(){
        // 判断根目录是否存在，如果不存在则创建

        File rootDir = new File(OneboxConstant.ROOT_FILE_PATH);
        System.out.println(OneboxConstant.ROOT_FILE_PATH);
        if (!rootDir.exists()){
            if (!rootDir.mkdir()) {
                System.out.println("本地创建根文件夹失败");
                return false;
            }
            // 在文件夹表中添加根目录的记录
            Folder folder = new Folder();
            folder.setId(1);
            folder.setInRecycled(0);
            folder.setSecret(0);
            folder.setStar(0);
            folder.setCreateTime(DateUtils.formateDateTime(new Date()));
            if (folderMapper.insert(folder) == 0){
                System.out.println("folder表中添加根文件夹记录失败，请检查数据库");
            }
        }
        return true;
    }

}
