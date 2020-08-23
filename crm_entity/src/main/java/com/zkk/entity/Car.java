package com.zkk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 汽车
 * @author ham
 *
 */
@Entity
@Table(name="car")
public class Car implements Serializable{
	private static final long serialVersionUID = 5090823655958329L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String brand;				//品牌

	@Column(name="price")
	private BigDecimal price;				// 购入价值

	@Column(name="buy_date")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
	private Date buyDate;				// 购入时间

	@Column(name="loan_status")
	private String loanStatus;			// 贷款情况

	@Column(name="refund_month")
	private BigDecimal refundMonth;			// 月供

	@Column(name="refund_date")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
	private Date refundDate;			// 还款时间

	@Column(name = "customer_id")
	private Integer customerId;
	public Car() {
		super();
	}
	public Car(Integer id, String brand, BigDecimal price, Date buyDate,
			String loanStatus, BigDecimal refundMonth, Date refundDate) {
		super();
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.buyDate = buyDate;
		this.loanStatus = loanStatus;
		this.refundMonth = refundMonth;
		this.refundDate = refundDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public BigDecimal getRefundMonth() {
		return refundMonth;
	}
	public void setRefundMonth(BigDecimal refundMonth) {
		this.refundMonth = refundMonth;
	}
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((loanStatus == null) ? 0 : loanStatus.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((refundDate == null) ? 0 : refundDate.hashCode());
		result = prime * result
				+ ((refundMonth == null) ? 0 : refundMonth.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (buyDate == null) {
			if (other.buyDate != null)
				return false;
		} else if (!buyDate.equals(other.buyDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loanStatus == null) {
			if (other.loanStatus != null)
				return false;
		} else if (!loanStatus.equals(other.loanStatus))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (refundDate == null) {
			if (other.refundDate != null)
				return false;
		} else if (!refundDate.equals(other.refundDate))
			return false;
		if (refundMonth == null) {
			if (other.refundMonth != null)
				return false;
		} else if (!refundMonth.equals(other.refundMonth))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", price=" + price
				+ ", buyDate=" + buyDate + ", loanStatus=" + loanStatus
				+ ", refundMonth=" + refundMonth + ", refundDate=" + refundDate
				+ "]";
	}

	public String toTagString() {
		return  formatNull("品牌", brand)+
				formatNull("贷款请款", loanStatus);
	}
	private String formatNull(String name,String value){
		if (value==null||value.trim().equals("")) {
			return "";
		}
		return name+":"+value+" ";
	}
}
