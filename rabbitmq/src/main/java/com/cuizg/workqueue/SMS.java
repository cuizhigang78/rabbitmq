package com.cuizg.workqueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SMS
 * @Author Maxwell
 * @Date 2021/9/24 0:21
 * @Description SMS
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SMS {
    private String name;
    private String mobile;
    private String content;
}
