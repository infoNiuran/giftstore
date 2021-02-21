package com.niuran.giftstore.net;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author MrD on 2018/7/9.
 * @Description: 在需要登录验证的Controller的方法上使用此注解
 */

@Target({ElementType.METHOD})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有
public @interface LoginRequired {
}
