package ru.itis.pashin.mailintegrationservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationContextConfig {

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

}
