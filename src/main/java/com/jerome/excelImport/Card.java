package com.jerome.excelImport;

import java.util.Date;

/**
 * 
 * @ClassName: Card
 * @Description: 电信卡实体类
 * @author jerome
 * @date 2015年9月2日 下午8:24:49
 *
 */
public class Card {
	private long id;
	private Date createDate;
	private Date updateDate;
	private String phone;
	private String iccid; // 卡唯一id
	private String province; // 卡所属省
	private String city; // 卡所属市
	private String remark; // 备注

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Card [phone=" + phone + ", iccid=" + iccid + ", province=" + province + ", city=" + city + ", remark="
				+ remark + ", getId()=" + getId() + ", getCreateDate()=" + getCreateDate() + ", getUpdateDate()="
				+ getUpdateDate() + "]";
	}

}
