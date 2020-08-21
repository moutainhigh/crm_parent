package com.zkk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author SamKK
 * @version 1.0
 * @date 2020/8/10 16:01
 */
@Entity
@Table(name = "emp")
public class Emp extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String account;
    @Column
    private String password;
    @Column(name = "id_card")
    private String idcard;
    @Column
    private Short state=0;
    @Column
    private String address;
    @Column
    private String corent;
    @Column(name = "entry_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;
    @Column(name = "dismission_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dismissionDate;
    @Column
    private String gender;
    @Column(name = "head_img")
    private String headImg;
    @Column
    private String email;
    @Column
    private String mobile;
    @Column
    private String tel;
    @Column(name = "job_no")
    private String jobNo;
    @Column(name = "contact_name")
    private String contactName;
    @Column
    private String relation;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column
    private String remark;
    /**
     * 0为不冻结，1为冻结
     */
    @Column(name = "is_ice")
    private Integer isIce = 0;
    /**
     * 邮箱是否发送 0未发送 1发送中 2发送成功 3 发送失败
     */
    @Column(name = "is_send")
    private Integer isSend = 0;
    /**
     * 配置关联映射,员工和公司部门都是多对一
     */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;
    /**
     * 员工和角色都是多对多
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "emp_role",joinColumns = @JoinColumn(name = "emp_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList = new ArrayList<>();

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCorent() {
        return corent;
    }

    public void setCorent(String corent) {
        this.corent = corent;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDismissionDate() {
        return dismissionDate;
    }

    public void setDismissionDate(Date dismissionDate) {
        this.dismissionDate = dismissionDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsIce() {
        return isIce;
    }

    public void setIsIce(Integer isIce) {
        this.isIce = isIce;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
