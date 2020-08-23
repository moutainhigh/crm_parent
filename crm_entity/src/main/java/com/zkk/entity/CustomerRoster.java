package com.zkk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 批量导入客户的名单
 */
@Entity
@Table(name = "customer_roster")
public class CustomerRoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column(name = "create_date")
    private Date date = new Date();
    @Column(name = "add_person_id")
    private Integer addPersonId;
    public CustomerRoster() {
    }

    public CustomerRoster(String name) {
        this.name = name;
    }

    public CustomerRoster(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAddPersonId() {
        return addPersonId;
    }

    public void setAddPersonId(Integer addPersonId) {
        this.addPersonId = addPersonId;
    }
}
