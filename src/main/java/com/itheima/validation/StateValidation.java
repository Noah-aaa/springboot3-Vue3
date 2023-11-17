package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author longteng
 * @date 2023/11/16 18:03
 **/
public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则，
        // s是校验的数据
        if(s == null)
            return false;
        if(s.equals("已发布")||s.equals("草稿"))
            return true;
        return false;
        // 返货false表示校验不通过，true表示通过
    }
}
