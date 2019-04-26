package com.olejnik_macek.Olejnik_Macek.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "employee_type")
public class EmployeeType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employee_type_id;

    @NotBlank
    private String employee_type;

    public Integer getEmployee_type_id() {
        return employee_type_id;
    }

    public void setEmployee_type_id(Integer employee_type_id) {
        this.employee_type_id = employee_type_id;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }
}
