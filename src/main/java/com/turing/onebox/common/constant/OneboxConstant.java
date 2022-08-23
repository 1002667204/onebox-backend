package com.turing.onebox.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;

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

//    public static final String PATH_SEPARATOR = "/";
    public static final String PATH_SEPARATOR = File.separator;

    /**
     * 是否是标星文件
     */
    public static final Integer IS_STARRED =1;
    public static final Integer IS_NOT_STARRED =0;

    /**
     * 文件是否在回收站
     */
    public static final Integer IN_RECYCLED = 1;
    public static final Integer NOT_IN_RECYCLED = 0;

    /**
     * 是否是秘密文件夹
     */
    public static final Integer IS_SECRET = 1;
    public static final Integer IS_NOT_SECRET = 0;



    /**
     * 系统产生的临时文件路径
     */
    public static String TEMP_FILE_PATH = "/.onebox/temp/";

    /**
     * 系统保存文件的根目录
     */
    public static String ROOT_FILE_PATH = System.getProperty("user.dir") + PATH_SEPARATOR + "onebox_files";


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
