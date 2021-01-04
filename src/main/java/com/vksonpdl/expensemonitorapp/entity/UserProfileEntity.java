package com.vksonpdl.expensemonitorapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserProfileEntity implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",unique = true, nullable = false)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "e_mail")
	private String eMail;
	
	@Column(name = "e_mail_code")
	private String eMailCode;
	
	@Column(name = "e_mail_code_timestamp")
	private LocalDateTime eMailCodeTimestamp;
	
	@Column(name = "tel_code")
	private String telCode;
	
	@Column(name = "tel_code_timestamp")
	private LocalDateTime telCodeTimestamp;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
}
