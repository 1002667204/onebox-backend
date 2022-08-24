package com.turing.onebox.common.model.result;

import java.util.ArrayList;

/**
 * @ClassName ReSettingItem
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/23 22:17
 * @Version 1.0
 */
public class ReSettingItem {

    private String[] audio;
    private String[] code;
    private String[] document;
    private String[] video;

    public String[] getAudio() { return audio; }
    public void setAudio(String[] value) { this.audio = value; }

    public String[] getCode() { return code; }
    public void setCode(String[] value) { this.code = value; }

    public String[] getDocument() { return document; }
    public void setDocument(String[] value) { this.document = value; }

    public String[] getVideo() { return video; }
    public void setVideo(String[] value) { this.video = value; }

}
