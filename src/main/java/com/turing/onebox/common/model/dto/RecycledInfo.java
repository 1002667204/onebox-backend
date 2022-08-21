package com.turing.onebox.common.model.dto;

import com.turing.onebox.common.utils.DateUtils;
import com.turing.onebox.common.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName RecycledInfo
 * @Description TODO
 * @Author Colin
 * @Date 2022/8/13 17:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class RecycledInfo {

    private Integer id;

    private String deleteTime;

    private String destroyTime;

    private Integer fileId;

}
