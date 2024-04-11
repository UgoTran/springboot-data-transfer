package com.t3h.web_demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableAsync
@Component // dn class nay la 1 bean
public class ScheduleServiceAsync {
    @Async
//    @Scheduled(fixedDelay = 3500)
    public void scheduleFixedDelayTask() {
        log.info("[{}] Fixed delay task  @Async - {}",
                Thread.currentThread().getName(), System.currentTimeMillis() / 1000);
    }
}
