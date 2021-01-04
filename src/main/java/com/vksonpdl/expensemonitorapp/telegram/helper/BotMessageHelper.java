package com.vksonpdl.expensemonitorapp.telegram.helper;

import org.springframework.stereotype.Component;

@Component
public class BotMessageHelper {

	public static final String MSG_REGISTER = "/Register";
	public static final String MSG_START = "/Start";

	StringBuilder messageBuilder;

	public String getUserNotResgisteredMessage(String telId) {

		messageBuilder = new StringBuilder();
		messageBuilder.append("Telegram Id: <b>").append(telId).append("</b> Is not Registered ! \n Do you want to ")
				.append(MSG_REGISTER);

		return messageBuilder.toString();
	}

	public String getUserLockedMessage(String telId) {

		messageBuilder = new StringBuilder();
		messageBuilder.append("Telegram Id: <b>").append(telId).append("</b> Is Locked ");

		return messageBuilder.toString();
	}

	public String getUserResgisteredMessage(String telId) {
		messageBuilder = new StringBuilder();
		messageBuilder.append("Telegram Id: <b>").append(telId).append("</b> Is Registered SuccessFull ")
				.append(MSG_START).append(" using the bot");

		return messageBuilder.toString();
	}
}
