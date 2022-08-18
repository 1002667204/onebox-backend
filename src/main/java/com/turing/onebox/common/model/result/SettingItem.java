package com.turing.onebox.common.model.result;

import com.turing.onebox.common.model.dto.ClassInfo;
import lombok.Data;

import java.util.List;

/**
 * 设置结果类
 */
@Data
public class SettingItem {

    private Integer id;

    private String className;

    private List<String> extList;

}
