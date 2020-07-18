#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${middlePackage}.annotation;

import java.lang.annotation.*;

/**
 * 校验Authorization的注解
 *
 * @author liuzhihang
 * @date 2020/3/7 14:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckToken {

    /**
     * 校验Authorization
     *
     * @return
     */
    boolean checkAuthorization() default false;

}
