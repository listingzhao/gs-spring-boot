package com.leyiju.base;

import com.leyiju.enums.ResponseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Builder
@Data
@ApiModel(value="ResponseEntity",description="响应Model")
public class ResponseEntity<Object> implements BaseResponseEntity {

    @ApiModelProperty(value="响应状态码",name="status", example = "200")
    private Integer status;

    @ApiModelProperty(value="文本信息",name="msg", example = "成功")
    private String msg;

    @ApiModelProperty(value="数据",name="data")
    private Object data;

    public static ResponseEntity success() {
        return ResponseEntity.builder().status(ResponseStatus.SUCCESS.getStatus())
                .msg(ResponseStatus.SUCCESS.getMsg()).build();
    }

    public static <T> ResponseEntity success(T data) {
        return ResponseEntity.builder()
                .data(data)
                .status(ResponseStatus.SUCCESS.getStatus())
                .msg(ResponseStatus.SUCCESS.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(ResponseStatus responseStatus, T data) {
        return ResponseEntity
                .builder()
                .data(data)
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .build();
    }

    public static <T> ResponseEntity success(int status, String msg, T data) {
        return ResponseEntity
                .builder()
                .data(data)
                .status(status)
                .msg(msg)
                .build();
    }


    // 错误信息

    public static ResponseEntity error() {
        return ResponseEntity.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }


    public static ResponseEntity error(String message) {
        return ResponseEntity
                .builder()
                .msg(message)
                .build();
    }

    public static ResponseEntity error(int status) {
        return ResponseEntity
                .builder()
                .status(status)
                .build();
    }

    public static ResponseEntity error(int status, String message) {
        return ResponseEntity
                .builder()
                .msg(message)
                .status(status)
                .build();
    }


    public static ResponseEntity error(ResponseStatus responseStatus) {
        return ResponseEntity.builder()
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .build();
    }

    public static <T> ResponseEntity error(ResponseStatus responseStatus, T data) {
        return ResponseEntity.builder()
                .status(responseStatus.getStatus())
                .msg(responseStatus.getMsg())
                .data(data)
                .build();
    }

}
