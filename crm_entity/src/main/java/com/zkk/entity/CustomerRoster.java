package com.zkk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public CustomerRoster() {
    }

    public CustomerRoster(String name) {
        this.name = name;
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
}
