package com.vksonpdl.expensemonitorapp.telegram.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vksonpdl.expensemonitorapp.enumeration.UserStatusEnum;
import com.vksonpdl.expensemonitorapp.service.UserService;
import com.vksonpdl.expensemonitorapp.telegram.constants.TelegramActions;
import com.vksonpdl.expensemonitorapp.telegram.helper.BotReplayHelper;
import com.vksonpdl.expensemonitorapp.telegram.helper.msg.BotMessageHelper;
import com.vksonpdl.expensemonitorapp.telegram.helper.msg.BotTelCodeMessageHelper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExpenseMonitorBot extends TelegramLongPollingBot {

	@Value("${telegram.bot.username}")
	private String botUserName;

	@Value("${telegram.bot.token}")
	private String botToken;

	@Value("${app.name}")
	private String appName;

	@Autowired
	UserService userService;

	@Autowired
	BotMessageHelper botMessageHelper;
	
	@Autowired
	BotTelCodeMessageHelper botTelCodeMessageHelper;
	
	@Autowired
	BotReplayHelper botReplayHelper; 

	@Override
	public void onUpdateReceived(Update update) {
		try {

			if (update.hasMessage() && update.getMessage().hasText()) {
				String messageText = update.getMessage().getText();
				String telId = update.getMessage().getFrom().getUserName();
				UserStatusEnum userStatusEnum = userService.isTelegramUserRegistered(telId);
				Message replayMessage = update.getMessage().getReplyToMessage();

				SendMessage message = new SendMessage();
				message.setParseMode(ParseMode.HTML);
				message.setChatId(update.getMessage().getChatId().toString());

				switch (userStatusEnum) {
				case NOT_FOUND:
					if (messageText.equals(TelegramActions.MSG_REGISTER) && userService.registerTelegramUser(telId)) {
						message.setText(botMessageHelper.getUserResgisteredMessage(telId));
					} else {
						message.setText(botMessageHelper.getUserNotResgisteredMessage(telId));
					}

					break;

				case LOCKED:
					message.setText(botMessageHelper.getUserLockedMessage(telId));
					break;

				default:
					if(null!=replayMessage && replayMessage.getFrom().getUserName().equals(botUserName)) {
						message.setText(botReplayHelper.getMessageBasedOnReplay(update.getMessage(),telId));
					}else {
						switch (messageText) {
						case TelegramActions.MSG_GENERATE_TEL_CODE:
							int telCode = userService.generateTelegramCode(telId);
							message.setText(botTelCodeMessageHelper.getTelegramCodeMessage(telId,telCode));
							break;
						case TelegramActions.MSG_VALIDATE_TEL_CODE:
							message.setText(TelegramActions.REPLAY_VALIDATE_TEL_CODE);
							break;
						case TelegramActions.MSG_ADD_EXPENSE:
							message.setText(botMessageHelper.getTBDMessage(telId));
							break;
						default:
							message.setText(botMessageHelper.getStartMessage(telId));
							break;
						}
					}
					
					
					break;
				}

				try {
					
					execute(message);
				} catch (TelegramApiException e) {
					log.error("TelegramApiException From onUpdateReceived() : {}", e.getMessage());
				}
			} else {
				log.info("No Proper Message in the Update");
			}
		} catch (Exception e) {
			log.error("TelegramApiException : {}", e.getMessage());
		}

	}

	@Override
	public String getBotUsername() {
		return botUserName;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

}
