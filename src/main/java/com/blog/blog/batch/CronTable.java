package com.blog.blog.batch;

import com.blog.blog.service.JandiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Transactional
public class CronTable {
   private final JandiService jandiService;

//    @Scheduled(cron = "1 0 0 * * *")
//    public void Jandi(){
//        jandiService.jandiCronJob();
//    }
//
}
