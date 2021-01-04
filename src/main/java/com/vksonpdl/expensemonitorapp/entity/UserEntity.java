package com.vksonpdl.expensemonitorapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
@AllArgsConstructor
public abstract class UserEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique = true, nullable = false)
	private Long id;
	@Column(name = "tel_id")
	private String telId;
	@Column(name = "login_id")
	private String loginId;
	@Column(name = "password")
	private String password;
	@Column(name = "locked")
	private boolean locked;
	@Column(name = "inv_login_count")
	private boolean invLoginCount;
	

	
}
