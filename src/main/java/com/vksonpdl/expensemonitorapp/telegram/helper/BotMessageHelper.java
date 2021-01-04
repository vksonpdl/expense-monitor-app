package com.vksonpdl.expensemonitorapp.telegram.helper;

import org.springframework.stereotype.Component;

@Component
public class BotMessageHelper {

	public static final String MSG_REGISTER = "/register";
	public static final String MSG_START = "/start";
	public static final String MSG_GENERATE_TEL_CODE = "/generateCode";
	public static final String MSG_ADD_EXPENSE = "/addExpense";
	
	private static final String NEW_LINE = " \n";

	StringBuilder messageBuilder;

	public String getUserNotResgisteredMessage(String telId) {

		messageBuilder = new StringBuilder();
		messageBuilder.append("Telegram Id: ").append(getBoldString(telId)).append(" Is not Registered !")
			.append(NEW_LINE).append("Do you want to ").append(getItalicsString(MSG_REGISTER));

		return messageBuilder.toString();
	}

	public String getUserLockedMessage(String telId) {

		messageBuilder = new StringBuilder();
		messageBuilder.append("Telegram Id: ").append(getBoldString(telId)).append(" Is Locked ");

		return messageBuilder.toString();
	}

	public String getUserResgisteredMessage(String telId) {
		messageBuilder = new StringBuilder();
		messageBuilder.append("Telegram Id: ").append(getBoldString(telId)).append(" Is Registered SuccessFull ")
				.append(getItalicsString(MSG_START)).append(" using the bot");

		return messageBuilder.toString();
	}
	
	public String getStartMessage(String telId) {
		messageBuilder = new StringBuilder();
		messageBuilder.append("Hi: ").append(getBoldString(telId)).append(NEW_LINE)
				.append(getItalicsString(MSG_GENERATE_TEL_CODE)).append(" : To Create new telegram code \n")
		.append(getItalicsString(MSG_ADD_EXPENSE)).append(" : To Add new Expense");

		return messageBuilder.toString();
	}
	
	public String getTBDMessage(String telId) {
		messageBuilder = new StringBuilder();
		messageBuilder.append("Hi: ").append(getBoldString(telId)).append(NEW_LINE)
				.append(getItalicsString("TBD"));

		return messageBuilder.toString();
	}
	
	
	private String getBoldString(String msg) {
		return "<b>".concat(msg).concat("</b>");
	}
	
	private String getItalicsString(String msg) {
		return "<i>".concat(msg).concat("</i>");
	}
}
