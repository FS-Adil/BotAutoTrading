package org.example.telegabotat.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.telegabotat.bot.ExchangeRatesBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
public class ExchangeRatesBotConfiguration {
    private final AppProperties appProperties;

    @Bean
    @SneakyThrows
    public TelegramBotsApi telegramBotsApi(ExchangeRatesBot exchangeRatesBot) {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        exchangeRatesBot.setBotUsername(appProperties.getBot().getName());
        api.registerBot(exchangeRatesBot);
        return api;
    }
}
