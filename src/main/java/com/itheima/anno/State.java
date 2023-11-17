package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author longteng
 * @date 2023/11/16 17:58
 **/
@Documented // 元注解
@Constraint(
        validatedBy = {StateValidation.class} // 指定提供校验规则的类
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    // 提供校验失败后的提示信息
    String message() default "state参数的值只能是已发布或者草稿";

    // 指定分组，
    Class<?>[] groups() default {};

    // 负载，获取到state注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
