package com.turing.onebox;

import com.turing.onebox.admin.mapper.ClassInfoMapper;
import com.turing.onebox.admin.mapper.SettingMapper;
import com.turing.onebox.common.model.dto.ClassInfo;
import com.turing.onebox.common.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.UUID;

@SpringBootTest
class OneboxApplicationTests {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Test
    void contextLoads() {
    }


    /**
     * 测试生成id
     */
    @Test
    void getCode(){
        for (int i = 0; i < 10; i++) {
            System.out.println(UUIDUtils.getUUID());
        }
    }

    /**
     * 测试SettingMapper的updateClassInfo方法
     */
    @Test
    void testUpdateClassInfo(){
        classInfoMapper.clearClassInfo();
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "pic", ".jpeg"));
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "pic", ".jpg"));
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "audio", ".mp3"));
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "audio", ".flac"));
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "audio", ".wav"));
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "video", ".mp4"));
        classInfoMapper.updateClassInfo(new ClassInfo(UUIDUtils.getUUID(), "video", ".mkv"));
    }

    @Test
    void testClearInfo(){
        classInfoMapper.clearClassInfo();
    }

    @Test
    void testFileSeparator(){
        System.out.println(File.separator);
    }
}
