package com.turing.onebox.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName ClassInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class ClassInfo {

    private Integer id;

    private String className;

    private String include;

}
