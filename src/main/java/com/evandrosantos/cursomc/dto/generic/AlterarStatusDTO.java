package com.evandrosantos.cursomc.dto.generic;

import com.evandrosantos.cursomc.domain.enums.Status;

import java.io.Serializable;

public class AlterarStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Status status;

    public AlterarStatusDTO() { }

    public AlterarStatusDTO(Integer id, Status status) {
        setId(id);
        setStatus(status);
    }

    public AlterarStatusDTO(Status status) {
        this(null, status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
