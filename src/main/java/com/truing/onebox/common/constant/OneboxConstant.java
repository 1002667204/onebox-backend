package com.truing.onebox.common.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName OneboxConstant
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/15 17:57
 * @Version 1.0
 */
@Configuration
public class OneboxConstant {

    public static final Character PATH_SEPARATOR_CHAR = '/';

    public static final String PATH_SEPARATOR = "/";


    /**
     * 系统产生的临时文件路径
     */
    public static String TEMP_FILE_PATH = "/.onebox/temp/";


    /**
     * 最大支持文件大小为 ? MB 的音乐文件解析封面, 歌手等信息.
     */
    public static Long AUDIO_MAX_FILE_SIZE_MB = 1L;


    /**
     * 最大支持文本文件大小为 ? KB 的文件内容.
     */
    public static Long TEXT_MAX_FILE_SIZE_KB = 100L;


//    @Autowired(required = false)
//    public void setTmpFilePath(@Value("${onebox.temp.path}") String tmpFilePath) {
//        OneboxConstant.TEMP_FILE_PATH = tmpFilePath;
//    }
//
//
//    @Autowired(required = false)
//    public void setAudioMaxFileSizeMb(@Value("${onebox.preview.audio.maxFileSizeMb}") Long maxFileSizeMb) {
//        OneboxConstant.AUDIO_MAX_FILE_SIZE_MB = maxFileSizeMb;
//    }
//
//
//    @Autowired(required = false)
//    public void setTextMaxFileSizeMb(@Value("${zfile.preview.text.maxFileSizeKb}") Long maxFileSizeKb) {
//        OneboxConstant.TEXT_MAX_FILE_SIZE_KB = maxFileSizeKb;
//    }

}
