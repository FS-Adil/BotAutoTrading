package org.example.telegabotat.bot;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.telegabotat.config.AppProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
@Component
public class ExchangeRatesBot extends TelegramLongPollingBot {
    private String botUserName;

    private static final String START = "/start";
    private static final String USD = "/usd";
    private static final String EUR = "/eur";
    private static final String HELP = "/help";

    public ExchangeRatesBot(
            @Value("${app.bot.token}") String botToken
    ) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        switch (message) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
//            case USD -> usdCommand(chatId);
//            case EUR -> eurCommand(chatId);
//            case HELP -> helpCommand(chatId);
            default -> unknownCommand(chatId);
        }

    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    public void setBotUsername(String username) {
        botUserName = username;
    }

    private void startCommand(Long chatId, String userName) {
        var text = """
                Добро пожаловать в бот, %s!
                
                Здесь Вы сможете узнать официальные курсы валют на сегодня, установленные ЦБ РФ.
                
                Для этого воспользуйтесь командами:
                /usd - курс доллара
                /eur - курс евро
                
                Дополнительные команды:
                /help - получение справки
                """;
        var formattedText = String.format(text, userName);
        sendMessage(chatId, formattedText);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения", e);
        }
    }

    private void unknownCommand(Long chatId) {
        var text = "Не удалось распознать команду!";
        sendMessage(chatId, text);
    }
}
