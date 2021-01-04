package com.vksonpdl.expensemonitorapp.service;

import com.vksonpdl.expensemonitorapp.enumeration.UserStatusEnum;

public interface UserService {
	
	public boolean registerTelegramUser(String telId);
	public UserStatusEnum isTelegramUserRegistered(String telId);

}
