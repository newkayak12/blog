package com.blog.blog.batch;

import com.blog.blog.repository.JandiRepository.JandiRepository;
import com.blog.blog.repository.boardRepository.BoardRepository;
import com.blog.blog.repository.entity.Jandi;
import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import com.blog.blog.service.JandiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Transactional
public class CronTable {
   private final JandiService jandiService;

    @Scheduled(cron = "0 27 22 * * *")
    public void Jandi(){
        jandiService.jandiCronJob();
    }
//
}
