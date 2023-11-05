package com.cuizg.direct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WeatherMessage
 * @Author Maxwell
 * @Date 2021/9/25 16:34
 * @Description WeatherMessage
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherMessage {

    private String country;
    private String desc;
}
