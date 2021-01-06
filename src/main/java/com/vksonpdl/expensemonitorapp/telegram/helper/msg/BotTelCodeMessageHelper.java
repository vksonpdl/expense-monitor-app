package com.vksonpdl.expensemonitorapp.telegram.helper.msg;

import org.springframework.stereotype.Component;

import com.vksonpdl.expensemonitorapp.enumeration.TelCodeStatusEnum;
import com.vksonpdl.expensemonitorapp.telegram.constants.TelegramActions;

@Component
public class BotTelCodeMessageHelper {

	private static final String NEW_LINE = " \n";
	StringBuilder messageBuilder;

	private String getBoldString(String msg) {
		return "<b>".concat(msg).concat("</b>");
	}

	private String getItalicsString(String msg) {
		return "<i>".concat(msg).concat("</i>");
	}

	public String getTelegramCodeMessage(String telId, int telCode) {
		messageBuilder = new StringBuilder();
		messageBuilder.append("Hi ").append(getBoldString(telId)).append(NEW_LINE).append("Telegram Code: ")
				.append(getItalicsString(String.valueOf(telCode))).append(NEW_LINE)
				.append("Code is valid for 10 minutes.");
		return messageBuilder.toString();
	}

	public String getTelegramCodeValidateMessage(String telId, TelCodeStatusEnum telCodeStatusEnum) {
		messageBuilder = new StringBuilder();
		messageBuilder.append("Hi ").append(getBoldString(telId)).append(NEW_LINE);

		switch (telCodeStatusEnum) {
		case EXPIRED:
			messageBuilder.append("The Code you have passed is expired").append(NEW_LINE).append(getItalicsString(TelegramActions.MSG_GENERATE_TEL_CODE));
			break;
		case INVALID:
			messageBuilder.append("The Code you have passed is invalid !");
			break;
		case NOTFOUND:
			messageBuilder.append("You have not created any Telegram code !").append(NEW_LINE).append(getItalicsString(TelegramActions.MSG_GENERATE_TEL_CODE));
			break;

		default:
			messageBuilder.append("The Code you have passed is valid !");	
			break;
		}

		return messageBuilder.toString();
	}

}
