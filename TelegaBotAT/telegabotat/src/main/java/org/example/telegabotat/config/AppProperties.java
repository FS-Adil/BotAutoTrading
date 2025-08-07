package org.example.telegabotat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private BotProperties bot;

    @Getter
    @Setter
    @Configuration
    public static class BotProperties {
        private String token;
        private String name;
    }
}
