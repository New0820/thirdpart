package com.example.temp.common.annotate;

import java.lang.annotation.*;

/**
 * @author LQF
 * @date 2023/4/30
 * @apiNote
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProUpdate {
    //字段描述
    String fieldName() default "";
}
