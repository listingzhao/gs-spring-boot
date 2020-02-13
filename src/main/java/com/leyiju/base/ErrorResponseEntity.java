package com.leyiju.base;

import com.leyiju.enums.ResponseStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 错误返回实体
 */
@Builder
@Data
public class ErrorResponseEntity implements BaseResponseEntity {
    private Integer status;

    private String msg;

    private String error;

    private String exception;

    private String path;

    private Date timestamp;

    public static ErrorResponseEntity error(ResponseStatus responseStatus, Throwable e) {
        return ErrorResponseEntity.builder()
                .msg(responseStatus.getMsg())
                .status(responseStatus.getStatus())
                .error(e.getMessage())
                .timestamp(new Date())
                .exception(e.getClass().getName())
                .build();
    }
}
