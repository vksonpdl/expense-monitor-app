package com.vksonpdl.expensemonitorapp.telegram.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import com.vksonpdl.expensemonitorapp.enumeration.TelCodeStatusEnum;
import com.vksonpdl.expensemonitorapp.service.UserService;
import com.vksonpdl.expensemonitorapp.telegram.constants.TelegramActions;
import com.vksonpdl.expensemonitorapp.telegram.helper.msg.BotMessageHelper;
import com.vksonpdl.expensemonitorapp.telegram.helper.msg.BotTelCodeMessageHelper;

@Component
public class BotReplayHelper {
	
	@Autowired
	BotMessageHelper botMessageHelper;
	
	@Autowired
	BotTelCodeMessageHelper botTelCodeMessageHelper;
	
	@Autowired
	UserService userService;

	public String getMessageBasedOnReplay(Message message, String telId) {
		
		String userMessage = message.getText();
		

		String messageText = "";

		switch (message.getReplyToMessage().getText()) {
		case TelegramActions.REPLAY_VALIDATE_TEL_CODE:
			TelCodeStatusEnum telCodeStatusEnum = userService.validateTelCode(telId, userMessage);
			messageText = botTelCodeMessageHelper.getTelegramCodeValidateMessage(telId, telCodeStatusEnum);
			break;

		default:
			messageText = botMessageHelper.getStartMessage(telId);
			break;
		}

		return messageText;
	}

}
