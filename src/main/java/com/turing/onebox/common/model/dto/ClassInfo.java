package com.turing.onebox.common.model.dto;

import com.turing.onebox.common.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ClassInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfo {

    private Integer id;

    private String className;

    private String ext;

    public ClassInfo(String className, String ext){
        this.id = UUIDUtils.getUUID();
        this.className = className;
        this.ext = ext;
    }
}
