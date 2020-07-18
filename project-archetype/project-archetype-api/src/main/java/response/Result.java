package response;

import com.liuzhihang.archetype.exception.CustomException;
import com.liuzhihang.archetype.exception.CustomExceptionEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuzhihang
 * @date 2020/7/14 20:42
 */
@Data
public class Result<T> implements Serializable {

    private String code;
    private String message;
    private T data;


    public Result<T> buildFail(CustomException customException) {
        return this.buildFail(customException.getCode(), customException.getMsg());
    }

    public Result<T> buildFail(CustomExceptionEnum customExceptionEnum) {
        return this.buildFail(customExceptionEnum.getCode(), customExceptionEnum.getMsg());
    }


    public Result<T> buildFail(String msg) {
        return this.buildFail(CustomExceptionEnum.FAIL.getCode(), msg);
    }

    public Result<T> buildFail(String code, String msg) {
        return this.buildFail(code, msg, null);
    }

    public Result<T> buildFail(String code, String msg, T data) {
        this.setCode(code);
        this.setMessage(msg);
        this.setData(data);
        return this;
    }

    public Result<T> buildSuccess(T data) {
        this.setCode(CustomExceptionEnum.SUCCESS.getCode());
        this.setMessage(CustomExceptionEnum.SUCCESS.getMsg());
        this.setData(data);
        return this;
    }

    public Result<T> buildSuccess(String msg, T data) {
        this.setCode(CustomExceptionEnum.SUCCESS.getCode());
        this.setMessage(msg);
        this.setData(data);
        return this;
    }

}
