package com.ithao.vueblog.shiro;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {
    //这是一个被封装的vo,非私密性的数据；
    private Long id;

    private String username;

    private String avatar;

    private String email;



}
