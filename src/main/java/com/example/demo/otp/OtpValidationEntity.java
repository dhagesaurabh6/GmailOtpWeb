package com.example.demo.otp;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp_validation")
public class OtpValidationEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "alert_id", unique = true, nullable = false)
	private Integer alert_id;
	
	@Column(name = "url", nullable = true)
	private String url;
	
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "resource_id")
	private String resource_id;
	
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "status")
	private String status;

	public Integer getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(Integer alert_id) {
		this.alert_id = alert_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OtpValidationEntity [alert_id=" + alert_id + ", url=" + url + ", uid=" + uid + ", resource_id="
				+ resource_id + ", otp=" + otp + ", action=" + action + ", status=" + status + "]";
	}
	
	
	
	
	

	
}
