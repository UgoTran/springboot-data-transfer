package com.t3h.web_demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
public class ScheduleService {

//    @Scheduled(fixedDelay = 3000)
    public void scheduleFixedDelayTask() {
        log.info("[{}] Fixed delay task - {}",
                Thread.currentThread().getName(), System.currentTimeMillis() / 1000);
    }

    @Scheduled(cron = "30 * * * * ?")
    public void scheduleTaskUsingCronExpression() {
        log.info("chạy sau mỗi 30s - " + LocalTime.now());
    }
}
