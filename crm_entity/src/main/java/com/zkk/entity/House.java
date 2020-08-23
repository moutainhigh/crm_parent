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
 * 房产
 *
 * @author ham
 */

@Entity
@Table(name = "house")
@SuppressWarnings("all")
public class House implements Serializable {
    private static final long serialVersionUID = -2094736881318249012L;
    public static final Integer HOUSE = 1;
    public static final Integer STORE = 2;
    public static final Integer OFFICE = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "place_area")
    private String placeArea;            // 住房位置（区）
    @Column(name = "place_street")
    private String placeStreet;            // 住房位置（街道）
    @Column(name = "place_detail")
    private String placeDetail;            // 住房位置（详情）
    @Column(name = "place_premise")
    private String placePremise;        // 住房位置（楼盘）
    @Column(name = "land_nature")
    private Integer landNature;            // 国土性质 1 集体 2 国有
    @Column(name = "construct_date")
    @JsonFormat(pattern = "yyyy")
    private Date constructDate;        // 建造年代
    @Column
    private BigDecimal area;                // 面积
    @Column
    private BigDecimal price;                // 价格
    @Column(name = "decorate_case")
    private Integer decorateCase;        // 装修情况 1精装 2简装 3清水
    @Column(name = "use_case")
    private String useCase;                // 使用情况
    @Column
    private BigDecimal rental;                // 租金/月
    @Column
    private Integer type;                // 类型 1住宅 2公寓 3别墅
    @Column(name = "customer_id")
    private Integer customerId;
    public House() {
        super();
    }

    public House(String placeArea, String placeStreet, String placeDetail,
                 String placePremise, Integer landNature, Date constructDate,
                 BigDecimal area, BigDecimal price, Integer decorateCase, String useCase,
                 BigDecimal rental, Integer type) {
        super();
        this.placeArea = placeArea;
        this.placeStreet = placeStreet;
        this.placeDetail = placeDetail;
        this.placePremise = placePremise;
        this.landNature = landNature;
        this.constructDate = constructDate;
        this.area = area;
        this.price = price;
        this.decorateCase = decorateCase;
        this.useCase = useCase;
        this.rental = rental;
        this.type = type;
    }

    //用于初始化空的房产对象
    public House(Integer id, String placeArea, String placeStreet,
                 String placeDetail, String placePremise, Integer landNature,
                 Date constructDate, BigDecimal area, BigDecimal price,
                 Integer decorateCase, String useCase, BigDecimal rental) {
        super();
        this.id = id;
        this.placeArea = placeArea;
        this.placeStreet = placeStreet;
        this.placeDetail = placeDetail;
        this.placePremise = placePremise;
        this.landNature = landNature;
        this.constructDate = constructDate;
        this.area = area;
        this.price = price;
        this.decorateCase = decorateCase;
        this.useCase = useCase;
        this.rental = rental;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceArea() {
        return placeArea;
    }

    public void setPlaceArea(String placeArea) {
        this.placeArea = placeArea;
    }

    public String getPlaceStreet() {
        return placeStreet;
    }

    public void setPlaceStreet(String placeStreet) {
        this.placeStreet = placeStreet;
    }

    public String getPlaceDetail() {
        return placeDetail;
    }

    public void setPlaceDetail(String placeDetail) {
        this.placeDetail = placeDetail;
    }

    public String getPlacePremise() {
        return placePremise;
    }

    public void setPlacePremise(String placePremise) {
        this.placePremise = placePremise;
    }

    public Integer getLandNature() {
        return landNature;
    }

    public void setLandNature(Integer landNature) {
        this.landNature = landNature;
    }

    public Date getConstructDate() {
        return constructDate;
    }

    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDecorateCase() {
        return decorateCase;
    }

    public void setDecorateCase(Integer decorateCase) {
        this.decorateCase = decorateCase;
    }

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    public BigDecimal getRental() {
        return rental;
    }

    public void setRental(BigDecimal rental) {
        this.rental = rental;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    //重写hashCode和equals方法,用于清除空对象
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((area == null) ? 0 : area.hashCode());
        result = prime * result
                + ((constructDate == null) ? 0 : constructDate.hashCode());
        result = prime * result
                + ((decorateCase == null) ? 0 : decorateCase.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((landNature == null) ? 0 : landNature.hashCode());
        result = prime * result
                + ((placeArea == null) ? 0 : placeArea.hashCode());
        result = prime * result
                + ((placeDetail == null) ? 0 : placeDetail.hashCode());
        result = prime * result
                + ((placePremise == null) ? 0 : placePremise.hashCode());
        result = prime * result
                + ((placeStreet == null) ? 0 : placeStreet.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((rental == null) ? 0 : rental.hashCode());
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
        House other = (House) obj;
        if (area == null) {
            if (other.area != null)
                return false;
        } else if (!area.equals(other.area))
            return false;
        if (constructDate == null) {
            if (other.constructDate != null)
                return false;
        } else if (!constructDate.equals(other.constructDate))
            return false;
        if (decorateCase == null) {
            if (other.decorateCase != null)
                return false;
        } else if (!decorateCase.equals(other.decorateCase))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (landNature == null) {
            if (other.landNature != null)
                return false;
        } else if (!landNature.equals(other.landNature))
            return false;
        if (placeArea == null) {
            if (other.placeArea != null)
                return false;
        } else if (!placeArea.equals(other.placeArea))
            return false;
        if (placeDetail == null) {
            if (other.placeDetail != null)
                return false;
        } else if (!placeDetail.equals(other.placeDetail))
            return false;
        if (placePremise == null) {
            if (other.placePremise != null)
                return false;
        } else if (!placePremise.equals(other.placePremise))
            return false;
        if (placeStreet == null) {
            if (other.placeStreet != null)
                return false;
        } else if (!placeStreet.equals(other.placeStreet))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (rental == null) {
            if (other.rental != null)
                return false;
        } else if (!rental.equals(other.rental))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "House [id=" + id + ", placeArea=" + placeArea
                + ", placeStreet=" + placeStreet + ", placeDetail="
                + placeDetail + ", placePremise=" + placePremise
                + ", landNature=" + landNature + ", constructDate="
                + constructDate + ", area=" + area + ", price=" + price
                + ", decorateCase=" + decorateCase + ", useCase=" + useCase
                + ", rental=" + rental + ", type=" + type + "]";
    }

    public String toTagString() {

        return formatNull("区", placeArea) +
                formatNull("街道", placeStreet) +
                formatNull("详细", placeDetail) +
                formatNull("楼盘", placePremise) +
                formatNull("使用情况", useCase);
    }

    private String formatNull(String name, String value) {
        if (value == null || value.trim().equals("")) {
            return "";
        }
        return value + "(" + name + ") ";
    }
}
