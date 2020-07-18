#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${middlePackage}.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import ${package}.${middlePackage}.exception.CustomException;
import ${package}.${middlePackage}.exception.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 对添加 hibernate.validator 注解的字段进行校验
 * <p>
 * 使用前 需要引入 hibernate-validator 依赖
 *
 * @author liuzhihang
 * @date 2017/11/22 11:08
 */
@Slf4j
public class ValidationUtils {

    private ValidationUtils(){

    }

    /**
     * 快速失败模式, 如果验证第一个失败, 则直接返回失败
     * <p>
     * 使用如下声明会默认, 验证所有字段
     * private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 会 验证 所有字段
     *
     * @param obj
     * @param <T>
     * @return 返回所有不符合的信息
     */
    public static <T> void validate(T obj) throws CustomException {

        if (obj == null) {
            log.error("ValidationUtils校验对象为空");
            throw new CustomException(CustomExceptionEnum.VALIDATION_OBJECT_IS_NULL);
        }

        StringBuilder errorDesc = new StringBuilder();

        Set<ConstraintViolation<T>> violationSet = validator.validate(obj);
        if (CollectionUtils.isNotEmpty(violationSet)) {
            for (ConstraintViolation<T> violation : violationSet) {
                errorDesc.append(violation.getMessage());
            }
            log.error("ValidationUtils校验对象为不通过, error:{}", errorDesc.toString());
            throw new CustomException(CustomExceptionEnum.buildValidationParamError(errorDesc.toString()));
        }

    }

    /**
     * 验证指定字段 是否符合信息
     *
     * @param obj
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T> void validateOneField(T obj, String fieldName) throws CustomException {


        if (obj == null) {
            log.error("ValidationUtils校验对象为空");
            throw new CustomException(CustomExceptionEnum.VALIDATION_OBJECT_IS_NULL);
        }

        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, fieldName);
        if (CollectionUtils.isNotEmpty(violationSet)) {
            for (ConstraintViolation<T> violation : violationSet) {
                log.error("ValidationUtils校验对象为不通过, error:{}", violation.getMessage());
                throw new CustomException(CustomExceptionEnum.buildValidationParamError(violation.getMessage()));
            }
        }
    }
}
