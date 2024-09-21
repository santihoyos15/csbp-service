package com.csbp.csbp.domain;

public enum RoleEnum {
    ADMIN(1, "admin"),
    EMPLEADO(2, "empleado"),
    CLIENTE(3, "cliente"),
    ;

    private Integer id;
    private String code;

    RoleEnum(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
