package com.vksonpdl.expensemonitorapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vksonpdl.expensemonitorapp.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{
	
	boolean existsByTelId(String telId);
	
	UserEntity findByTelId(String telId);
	
}
