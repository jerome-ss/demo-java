package com.jerome.excelExport;

/**
 * 
 * @ClassName: ExportShipment
 * @Description: 导出出货信息到excel model
 * @author jerome
 * @date 2015年9月10日 下午2:17:58
 *
 */
public class ExportShipment {
	private int index;
	private String phone;
	private String iccid; // 卡唯一id
	private String province; // 卡所属省
	private String city; // 卡所属市
	private String remark; // 备注

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
