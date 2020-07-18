#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${middlePackage}.exception;

/**
 * @author liuzhihang
 * @date 2019/12/17 14:45
 */
public class CustomException extends RuntimeException {

    private String code;

    private String msg;


    public CustomException(CustomExceptionEnum exceptionEnum) {
        super(exceptionEnum.getDesc() + " -> " + exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
