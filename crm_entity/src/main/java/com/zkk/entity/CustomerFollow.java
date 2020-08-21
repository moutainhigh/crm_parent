package com.zkk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * 客户跟进信息
 * @author MrLiu
 *
 */
@Entity
@Table(name = "customer_follow")
public class CustomerFollow implements Serializable{
	private static final long serialVersionUID = -2079991765585813330L;
	public static final Integer PHONE = 1;		//电话联系
	public static final Integer SMS= 2;			//邀约上门
	public static final Integer VISIT= 3;		//上门洽谈
	public static final Integer SIGN= 4;		//成功签约
	public static final Integer SERVICE= 5;		//售后服务
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "remind_time")
	@Temporal(TemporalType.DATE)
	private Date remindTime;				//提醒反馈时间
	@Column
	private Integer type;					//类型
	@Column
	private Integer visibility;				//是否自留
	@Lob
	@Type(type="text")
	@Column(name = "remind_content")
	private String remindContent;			//提醒内容
	@Lob
	@Type(type="text")
	@Column(name = "feedback_content")
	private String feedbackContent;			//反馈内容
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;				//对应客户
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Emp employee;				//员工
	@Column(name = "edt_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date edtTime = new Date();		//录入时间

	@Column(name = "edt_date")
	@Temporal(TemporalType.DATE)
	private Date edtDate = new Date();		// 录入日期
	@Column(name = "company_id")
	private Integer companyId;				//公司ID
	public CustomerFollow() {
		super();
	}
	//对应客户跟进列表的构造函数
	public CustomerFollow(Integer id, Date remindTime, Integer type,
			Integer visibility, String remindContent, String feedbackContent,
			String employeeName, Date edtTime) {
		this.id = id;
		this.remindTime = remindTime;
		this.type = type;
		this.visibility = visibility;
		this.remindContent = remindContent;
		this.feedbackContent = feedbackContent;
		Emp employee = new Emp();
		employee.setName(employeeName);
		this.employee = employee;
		this.edtTime = edtTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemindContent() {
		return remindContent;
	}
	public void setRemindContent(String remindContent) {
		this.remindContent = remindContent;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}
	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Emp getEmployee() {
		return employee;
	}
	public void setEmployee(Emp employee) {
		this.employee = employee;
	}
	public Date getEdtTime() {
		return edtTime;
	}
	public void setEdtTime(Date edtTime) {
		this.edtTime = edtTime;
	}
	public Integer getVisibility() {
		return visibility;
	}
	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}
	@Override
	public String toString() {
		return "CustomerFollow [id=" + id + ", remindTime=" + remindTime
				+ ", type=" + type + ", visibility=" + visibility
				+ ", remindContent=" + remindContent + ", feedbackContent="
				+ feedbackContent + ", edtTime=" + edtTime + "]";
	}
	public Date getEdtDate() {
		return edtDate;
	}
	public void setEdtDate(Date edtDate) {
		this.edtDate = edtDate;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

}
