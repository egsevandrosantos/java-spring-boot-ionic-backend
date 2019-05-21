package com.evandrosantos.cursomc.resources.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msg;
    private Long timestamp;

    public StandardError() { }

    public StandardError(Integer status, String msg, Long timestamp) {
        setStatus(status);
        setMsg(msg);
        setTimestamp(timestamp);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return sdf.format(timestamp);
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
