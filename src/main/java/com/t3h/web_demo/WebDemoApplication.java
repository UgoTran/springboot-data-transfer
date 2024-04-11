package com.t3h.web_demo;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootApplication
@RestController
@EnableScheduling
@Slf4j
public class WebDemoApplication {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);

    }

    //https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html
    public void beepForAnHour() {
        final Runnable beeper = () -> log.info("beep {}", LocalTime.now());
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
        scheduler.schedule(() -> {
            beeperHandle.cancel(true);
        }, 60 * 60, SECONDS);
    }

    @GetMapping("/")
    public ResponseEntity<String> root() {
        return ResponseEntity.ok("Vào thành công đường dẫn root /");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hellol");
    }

    //	# Naming strategy - gíup ORM - ánh xạ thuộc tính class & column
    @Bean
    public PhysicalNamingStrategy physical() {
        beepForAnHour();
        return new PhysicalNamingStrategyStandardImpl();
    }

    @Bean
    public ImplicitNamingStrategy implicit() {
        return new ImplicitNamingStrategyLegacyJpaImpl();
    }

}
