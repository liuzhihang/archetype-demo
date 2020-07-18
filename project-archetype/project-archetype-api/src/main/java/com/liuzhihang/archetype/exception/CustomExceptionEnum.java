package com.liuzhihang.archetype.exception;

/**
 * 异常枚举类型
 * <p>
 * <p>
 * 注意错误码不要为中文
 * <p>
 * code 为错误码
 * message 为前端文案
 * desc 为描述, 只会在日志中显示
 *
 * @author liuzhihang
 * @date 2019/12/17 14:47
 */
public enum CustomExceptionEnum {

    SUCCESS("00000", "success", "成功"),
    FAIL("00001", "System exception", "失败"),

    // 1XXXX本系统异常

    ILLEGAL_PARAMETER("10001", "System exception", "参数异常"),
    VALIDATION_OBJECT_IS_NULL("10002", "System exception", "校验对象为空"),
    VALIDATION_OBJECT_PARAM_ERROR("10003", "System exception", "自定义的内容"),
    VALIDATION_OBJECT_EXC("10004", "Operation failed, please check the parameters", "校验对象异常"),



    TEMP("99999", "Custom exception", "自定义异常"),
    ;

    private String code;

    private String msg;


    private String desc;

    CustomExceptionEnum(String code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 自定义描述信息
     *
     * @param desc
     * @return
     */
    public static CustomExceptionEnum buildValidationParamError(String desc) {
        CustomExceptionEnum tempEnum = TEMP;
        tempEnum.desc = desc;
        return tempEnum;
    }

    /**
     * 抛出当前枚举类型的异常
     */
    public CustomException throwCustomException(){
        return new CustomException(this);
    }


}
