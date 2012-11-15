package com.ztgame.admin.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name", length = 32, nullable = false)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private boolean gender;
	
	@Column(name = "exp", nullable = false)
	private long exp = 0;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	private Date createDate = new Date();

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

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
