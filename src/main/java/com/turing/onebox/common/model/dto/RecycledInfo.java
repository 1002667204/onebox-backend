package com.turing.onebox.common.model.dto;

import lombok.Data;

/**
 * @ClassName RecycledInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:02
 * @Version 1.0
 */
@Data
public class RecycledInfo {

    private Integer id;

    private String deleteTime;

    private String destroyTime;

    private Integer fileId;

}
