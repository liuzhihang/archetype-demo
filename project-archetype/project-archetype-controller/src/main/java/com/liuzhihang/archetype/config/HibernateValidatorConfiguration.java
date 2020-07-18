package com.liuzhihang.archetype.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author liuzhihang
 * @date 2020/1/2 20:06
 */
@Configuration
public class HibernateValidatorConfiguration {

    @Bean
    public static Validator validator() {
        return Validation.byProvider(HibernateValidator.class).configure()
                // 快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true).buildValidatorFactory().getValidator();
    }

}
