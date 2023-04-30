package com.example.temp.common.annotate;

import lombok.Data;

import java.lang.annotation.*;

/**
 * @author LQF
 * @date 2023/4/30
 * @apiNote
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ForUpdate {
    //字段描述
    String fieldName() default "";
    //目前没啥大用
    int index() default 0;
    //来源 shop店铺 pro商品
    String source() default "";
}
