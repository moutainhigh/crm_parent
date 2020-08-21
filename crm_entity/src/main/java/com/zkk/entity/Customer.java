package com.zkk.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户
 *
 * @author ham
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 6224354187813218246L;

    /**
     * 添加客户的类型
     *
     * @author MrLiu
     */
    public static enum CustomerAddType {
        /**
         * 0:市场录入
         **/
        marketing,
        /**
         * 1:批量导入Excel表
         **/
        excel,
        /**
         * 2:业务员录入
         */
        salesman,
        /**
         * 3:批量导入客户到公共池
         **/
        excelCommonPool;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //------------------------------------------ 1.基本信息 start --------------------------------------------
    @Column(length = 100)
    private String name;                        // 名称
    @Column
    private String gender = "男";                // 性别
    @Column
    private Integer age;                        // 年龄
    @Column(length = 100)
    private String phone;                        // 电话
    @Column(name = "phone_remark")
    private String phoneRemark;                    // 电话备注

//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="customerId")
//	private List<Contacts> contacts;			// 通讯录

    @Column(name = "loan_type")
    private String loanType;                    // 贷款类型
    @ManyToOne
    @JoinColumn(name = "customer_source_id")
    private CustomerSource customerSource;        // 数据来源
    @Column(name = "other_info")
    @Lob
    @Type(type = "text")
    private String otherInfo;                    //其它消息,批量导入时使用
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;                    // 修改时间
    //------------------------------------------ 基本信息 end --------------------------------------------

    //------------------------------------------ 2.客户资产 start ------------------------------------------

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Set<House> houses = new HashSet<House>();                        // 房产:住房、商铺、写字楼
    //
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="customerId")
//	private List<Land> lands;						//土地
//
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Set<Car> cars = new HashSet<Car>();                            //汽车
    //
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="customerId")
//	private List<Enterprise> enterprises;			//企业
//
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Set<CreditCard> creditCards = new HashSet<CreditCard>();            //信用卡

    @Column(length = 500, name = "customer_asset_titile")
    private String customerAssetTitile;                //客户资产标题字符串

    @Lob
    @Type(type = "text")
    @Column(name = "customer_asset")
    private String customerAsset;                    //客户资产详细字符串

    //------------------------------------------ 客户资产 end --------------------------------------------

    //------------------------------------------ 3.辅助信息 start -------------------------------------------
    @Column(name = "month_income")
    private String monthIncome;                        //月收入
    @Column
    private String embodiment;                        // 体现
    @Column(name = "social_insurance")
    private String socialInsurance;                    // 社保
    @Column
    private String credit;                            // 征信
    @Column(name = "enterprise_nature")
    private String enterpriseNature;                // 单位性质
    @Column(name = "work_year")
    private String workYear;                        // 工作时间
    @Column(name = "required_money")
    private String requiredMoney;                    // 需求资金
    @Column(name = "repayment_limit")
    private String repaymentLimit;                    // 还款年限
    @Column(name = "use_date")
    @Temporal(TemporalType.DATE)
    private Date useDate;                            // 用款时间
    @Column(name = "lending_institution")
    private String lendingInstitution;                // 推荐贷款机构名称:银行/小贷/私人
    @Column
    private String census;                            // 户籍
    @Column
    private String marriage;                        // 婚姻状况
    @Column(name = "bond_expire_date")
    @Temporal(TemporalType.DATE)
    private Date bondExpireDate;                    // 债权到期时间
    @Column(name = "repayment_type")
    private String repaymentType;                    // 还款方式
    @Column(name = "fund_use")
    private String fundUse;                            // 用途
    @Column(name = "customer_character")
    private String customerCharacter;                // 客户性格

    //	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="customerId")
//	private List<Amountliability> amountliabilitys;	// 债券构造
    @Column(name = "debt_total")
    private Double debtTotal;                        // 负债总额
    @Column(name = "referrer_id")
    private Integer referrerId;                        //推荐人ID
    @Column(name = "customer_level")
    private String customerLevel = "中";                //客户等级
    @Column(name = "attention_level")
    private String attentionLevel = "C";            //关注等级
    @Column(name = "data_percent")
    private Double dataPercent;                        //资料完成比
    //------------------------------------------ 辅助信息 end -------------------------------------------

    //------------------------------------------ 4.其它消息 start -----------------------------------------
    @Column
    private Integer visibility;                        //客户可见性,0:只有0才是自留客户,1/null都不是自留客户,默认为null
    @Column(name = "call_count")
    private Integer callCount = 0;                    // 通话次数
    @Column(name = "visit_count")
    private Integer visitCount = 0;                    // 上门次数
    @Column(name = "add_type")
    private Integer addType;                        //录入客户类型,0:市场录入,1:批量导入,2:业务员添加
    @Column(name = "sign_state")
    private String signState = "未签单";                //签单状态:已签单/未签单
    //普通字段：只有前端显示需要的属性，而不需要存在数据库的熟悉
    @Transient
    private Integer countdown;                        //倒计时
    @Column(name = "turn_detail")
    private String turnDetail;                        // 移交
    @Column(name = "public_view")
    private Integer publicView;                        // 释放到公共池（null没有到公共池）
    @Column(name = "is_follow")
    private Integer isFollow;                        //是否跟进(null:从未跟进,0:已跟进至少一次)
    @Column(name = "fllow_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fllowDate = new Date();                // 跟进时间
    //------------------------------------------ 其它消息 end ------------------------------------------

    //------------------------------------- 5.客户从属关系相关字段 start ------------------------------------
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Dept dept;                    // 属于哪个部门
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Emp emp;                        // 属于哪个员工
    @Column
    private Integer state = CustomerState.unassigned.ordinal();    // 客户状态:默认为刚录入
    @Column(name = "add_person_id")
    private Integer addPersonId;                    // 录入人员id
    @Column(name = "add_person_name")
    private String addPersonName;                    // 录入人员名称
    @Column(name = "create_date")
    private Date createDate = new Date();            // 录入时间(年月日)
    @Column(name = "create_time")
    private Date createTime = new Date();            // 录入时间(年月日时分秒毫秒)
    @ManyToOne
    @JoinColumn(name = "customer_roster_id")
    private CustomerRoster customerRoster;            //批量导入客户的名单对象
    //------------------------------------- 客户从属关系相关字段 end ------------------------------------
    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;                        // 释放时间 年月日

    @Column(name = "release_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseTime;                        // 释放时间 年月日时分秒

    @Column(name = "receive_date")
    @Temporal(TemporalType.DATE)
    private Date receiveDate;                        // 领取公共池的时间

    //2016-3-6 批量释放到公共池记录信息
    //释放类型(releaseType):1(job自动释放),2(在客户移交列表批量释放),3(admin在数据库操作)
    @Column(name = "release_type")
    private Integer releaseType;
    // 释放人id(releaseId):批量释放时,记录是操作人id,0表示系统
    @Column(name = "release_id")
    private Integer releaseId;

    @Column(name = "company_id")
    private Integer companyId;                //公司ID
    @Column(name = "company_name")
    private String companyName;                //公司名称


    // ------------------------------------- 客户最后一次提醒的跟进记录start ---------------------------------
    // 当添加跟进记录时,提醒类型和提醒时间不为null时,则更新最后一次跟进
    @Column(name = "last_follow_id")
    private Integer lastFollowId;                    // 最后一次提醒的跟进id
    @Column(name = "last_remind_content")
    private String lastRemindContent;                // 最后一次提示的内容
    @Column(name = "last_follow_type")
    private Integer lastFollowType;                    // 最后一次提醒的跟进类型
    @Column(name = "last_remind_time")
    @Temporal(TemporalType.DATE)
    private Date lastRemindTime;                    // 最后一次提醒的时间
    // ------------------------------------- 客户最后一次提醒的跟进记录end -----------------------------------

    // ------------------------------------- 权证客户最后一次提醒的跟进记录start ---------------------------------
    // 当添加跟进记录时,提醒类型和提醒时间不为null时,则更新最后一次跟进
    @Column(name = "last_warrant_follow_id")
    private Integer lastWarrantFollowId;                    // 权证人员最后一次提醒的跟进id
    @Column(name = "last_warrant_remind_content")
    private String lastWarrantRemindContent;                // 权证人员最后一次提示的内容
    @Column(name = "last_warrant_follow_type")
    private Integer lastWarrantFollowType;                    // 权证人员最后一次提醒的跟进类型
    @Column(name = "last_warrant_remind_time")
    @Temporal(TemporalType.DATE)
    private Date lastWarrantRemindTime;                    // 权证人员最后一次提醒的时间
    // ------------------------------------- 权证客户最后一次提醒的跟进记录end -----------------------------------

    public Customer() {
        super();
    }

    public Customer(Integer id) {
        super();
        this.id = id;
    }

    public Customer(Integer id, Integer visibility, Integer state) {
        super();
        this.id = id;
        this.visibility = visibility;
        this.state = state;
    }

    public CustomerRoster getCustomerRoster() {
        return customerRoster;
    }

    public void setCustomerRoster(CustomerRoster customerRoster) {
        this.customerRoster = customerRoster;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneRemark() {
        return phoneRemark;
    }

    public void setPhoneRemark(String phoneRemark) {
        this.phoneRemark = phoneRemark;
    }

    //	public List<Contacts> getContacts() {
//		return contacts;
//	}
//	public void setContacts(List<Contacts> contacts) {
//		this.contacts = contacts;
//	}
    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public CustomerSource getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(CustomerSource customerSource) {
        this.customerSource = customerSource;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
    }

    //	public List<Land> getLands() {
//		return lands;
//	}
//	public void setLands(List<Land> lands) {
//		this.lands = lands;
//	}
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    //	public List<Enterprise> getEnterprises() {
//		return enterprises;
//	}
//	public void setEnterprises(List<Enterprise> enterprises) {
//		this.enterprises = enterprises;
//	}
    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public String getCustomerAssetTitile() {
        return customerAssetTitile;
    }

    public void setCustomerAssetTitile(String customerAssetTitile) {
        this.customerAssetTitile = customerAssetTitile;
    }

    public String getCustomerAsset() {
        return customerAsset;
    }

    public void setCustomerAsset(String customerAsset) {
        this.customerAsset = customerAsset;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getEmbodiment() {
        return embodiment;
    }

    public void setEmbodiment(String embodiment) {
        this.embodiment = embodiment;
    }

    public String getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(String socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getWorkYear() {
        return workYear;
    }

    public void setWorkYear(String workYear) {
        this.workYear = workYear;
    }

    public String getRequiredMoney() {
        return requiredMoney;
    }

    public void setRequiredMoney(String requiredMoney) {
        this.requiredMoney = requiredMoney;
    }

    public String getRepaymentLimit() {
        return repaymentLimit;
    }

    public void setRepaymentLimit(String repaymentLimit) {
        this.repaymentLimit = repaymentLimit;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public String getLendingInstitution() {
        return lendingInstitution;
    }

    public void setLendingInstitution(String lendingInstitution) {
        this.lendingInstitution = lendingInstitution;
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public Date getBondExpireDate() {
        return bondExpireDate;
    }

    public void setBondExpireDate(Date bondExpireDate) {
        this.bondExpireDate = bondExpireDate;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getFundUse() {
        return fundUse;
    }

    public void setFundUse(String fundUse) {
        this.fundUse = fundUse;
    }

    public String getCustomerCharacter() {
        return customerCharacter;
    }

    public void setCustomerCharacter(String customerCharacter) {
        this.customerCharacter = customerCharacter;
    }

    //	public List<Amountliability> getAmountliabilitys() {
//		return amountliabilitys;
//	}
//	public void setAmountliabilitys(List<Amountliability> amountliabilitys) {
//		this.amountliabilitys = amountliabilitys;
//	}
    public Double getDebtTotal() {
        return debtTotal;
    }

    public void setDebtTotal(Double debtTotal) {
        this.debtTotal = debtTotal;
    }

    public Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Integer referrerId) {
        this.referrerId = referrerId;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getAttentionLevel() {
        return attentionLevel;
    }

    public void setAttentionLevel(String attentionLevel) {
        this.attentionLevel = attentionLevel;
    }

    public Double getDataPercent() {
        return dataPercent;
    }

    public void setDataPercent(Double dataPercent) {
        this.dataPercent = dataPercent;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getCallCount() {
        return callCount;
    }

    public void setCallCount(Integer callCount) {
        this.callCount = callCount;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getAddType() {
        return addType;
    }

    public void setAddType(Integer addType) {
        this.addType = addType;
    }

    public String getSignState() {
        return signState;
    }

    public void setSignState(String signState) {
        this.signState = signState;
    }

    public Integer getCountdown() {
        //获取从客户录入后到当前系统时间差
        Integer days = getDistanceDays(getCreateTime());
        //计算逻辑
        return Constant.RELEASE_DAY - days;
    }

    public void setCountdown(Integer countdown) {
        this.countdown = countdown;
    }

    public String getTurnDetail() {
        return turnDetail;
    }

    public void setTurnDetail(String turnDetail) {
        this.turnDetail = turnDetail;
    }

    public Integer getPublicView() {
        return publicView;
    }

    public void setPublicView(Integer publicView) {
        this.publicView = publicView;
    }

    public Integer getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(Integer isFollow) {
        this.isFollow = isFollow;
    }

    public Date getFllowDate() {
        return fllowDate;
    }

    public void setFllowDate(Date fllowDate) {
        this.fllowDate = fllowDate;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAddPersonId() {
        return addPersonId;
    }

    public void setAddPersonId(Integer addPersonId) {
        this.addPersonId = addPersonId;
    }

    public String getAddPersonName() {
        return addPersonName;
    }

    public void setAddPersonName(String addPersonName) {
        this.addPersonName = addPersonName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Temporal(TemporalType.DATE)
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    public Integer getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Integer releaseId) {
        this.releaseId = releaseId;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getLastFollowId() {
        return lastFollowId;
    }

    public void setLastFollowId(Integer lastFollowId) {
        this.lastFollowId = lastFollowId;
    }

    public String getLastRemindContent() {
        return lastRemindContent;
    }

    public void setLastRemindContent(String lastRemindContent) {
        this.lastRemindContent = lastRemindContent;
    }

    public Integer getLastFollowType() {
        return lastFollowType;
    }

    public void setLastFollowType(Integer lastFollowType) {
        this.lastFollowType = lastFollowType;
    }

    public Date getLastRemindTime() {
        return lastRemindTime;
    }

    public void setLastRemindTime(Date lastRemindTime) {
        this.lastRemindTime = lastRemindTime;
    }

    public Integer getLastWarrantFollowId() {
        return lastWarrantFollowId;
    }

    public void setLastWarrantFollowId(Integer lastWarrantFollowId) {
        this.lastWarrantFollowId = lastWarrantFollowId;
    }

    public String getLastWarrantRemindContent() {
        return lastWarrantRemindContent;
    }

    public void setLastWarrantRemindContent(String lastWarrantRemindContent) {
        this.lastWarrantRemindContent = lastWarrantRemindContent;
    }

    public Integer getLastWarrantFollowType() {
        return lastWarrantFollowType;
    }

    public void setLastWarrantFollowType(Integer lastWarrantFollowType) {
        this.lastWarrantFollowType = lastWarrantFollowType;
    }

    public Date getLastWarrantRemindTime() {
        return lastWarrantRemindTime;
    }

    public void setLastWarrantRemindTime(Date lastWarrantRemindTime) {
        this.lastWarrantRemindTime = lastWarrantRemindTime;
    }

    /***
     * @description: 获取指定日期距离当前系统时间的天数
     * @param oldDate 日期参数
     * @return java.lang.Integer 距离天数
     * @exception
     */
    private Integer getDistanceDays(Date oldDate) {
        if (oldDate == null) {
            return null;
        }
        //获取毫秒数差
        long startTime = oldDate.getTime();
        long endTime = new Date().getTime();
        long dis = Math.abs(endTime - startTime);
//        long days = dis/(24*60*60*1000)//结果自动取整数部分
//        return Integer.parseInt(String.valueOf(days));
        Long days = dis / (24 * 60 * 60 * 1000);//结果自动取整数部分
        return days.intValue();
    }
}
