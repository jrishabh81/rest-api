package com.example.restapi.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class ScheduledThread {

//    @Scheduled(fixedRate = 1000, initialDelay = 2000)
//    @SchedulerLock(name = "DragonFlyReportTask_ScheduledTask", lockAtLeastForString = "PT2M", lockAtMostForString = "PT3M")
    public void scheduled() {
        log.info("This is a fixed rate scheduled function: {}", LocalDateTime.now());
    }
}
