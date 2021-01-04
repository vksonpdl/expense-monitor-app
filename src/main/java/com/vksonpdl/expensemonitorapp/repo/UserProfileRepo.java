package com.vksonpdl.expensemonitorapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vksonpdl.expensemonitorapp.entity.UserProfileEntity;

public interface UserProfileRepo extends JpaRepository<UserProfileEntity, Long>{
	
	

}
