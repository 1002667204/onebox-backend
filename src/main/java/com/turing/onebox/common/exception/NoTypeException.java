package com.turing.onebox.common.exception;

/**
 * @author HuangYuhan
 * @create 2022-08-24-14:45
 * 异常类：文件后缀对应的文件不存在异常
 */
public class NoTypeException extends Exception {

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public NoTypeException(String data) {
        value=data;
    }

    @Override
    public String toString() {
        return "NoTypeException{" +
                "value='" + value + '\'' +
                '}';
    }
}
