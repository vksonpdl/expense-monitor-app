package com.vksonpdl.expensemonitorapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vksonpdl.expensemonitorapp.entity.UserEntity;
import com.vksonpdl.expensemonitorapp.entity.UserProfileEntity;
import com.vksonpdl.expensemonitorapp.enumeration.UserStatusEnum;
import com.vksonpdl.expensemonitorapp.repo.UserRepo;
import com.vksonpdl.expensemonitorapp.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public boolean registerTelegramUser(String telId) {
		boolean isRegistered = Boolean.FALSE;

		try {
			UserEntity user = new UserEntity();
			user.setTelId(telId);
			user.setInvLoginCount(0);
			user.setLocked(false);

			UserProfileEntity userProfile = new UserProfileEntity();
			userProfile.setUserEntity(user);

			user.setUserProfileEntity(userProfile);
			userRepo.save(user);
			isRegistered = Boolean.TRUE;

		} catch (Exception e) {
			log.error("Exception from registerTelegramUserfor Telegram Id :{}, Exception: {}", telId, e.getMessage());
			e.printStackTrace();
			isRegistered = Boolean.FALSE;
		}

		return isRegistered;
	}

	@Override
	public UserStatusEnum isTelegramUserRegistered(String telId) {

		UserStatusEnum userEnum = UserStatusEnum.LOCKED;
		UserEntity user = userRepo.findByTelId(telId);

		if (null != user) {
			if (user.isLocked()) {
				log.warn("Telegram Id: {} is Locked, Invalid Login Count: {}", telId, user.getInvLoginCount());
				userEnum = UserStatusEnum.LOCKED;
			} else {
				userEnum = UserStatusEnum.ACTIVE;
				log.info("Telegram Id: {} is Present, Invalid Login Count: {}", telId, user.getInvLoginCount());
			}
		} else {
			log.warn("Telegram Id: {} is not registered", telId);
			userEnum = UserStatusEnum.NOT_FOUND;
		}

		return userEnum;
	}

}
