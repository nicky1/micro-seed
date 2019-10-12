package com.waffle.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yixiaoshuang
 * @date 2019-10-12 19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentWork {

    private String studentWorkId;
    private String name;

    private long userId;
    private String userName;
}
