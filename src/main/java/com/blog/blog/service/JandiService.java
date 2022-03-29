package com.blog.blog.service;

import com.blog.blog.repository.JandiRepository.JandiRepository;
import com.blog.blog.repository.boardRepository.BoardRepository;
import com.blog.blog.repository.entity.Jandi;
import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@ToString
@Slf4j
@RequiredArgsConstructor
public class JandiService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final JandiRepository jandiRepository;
    public void jandiCronJob(){
        //        List<Jandi> result = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for(User item : userList) {
            LocalDate now = LocalDate.now();
            LocalDate start = now.minusDays(1);
            Instant stInstant = now.atStartOfDay(ZoneId.systemDefault()).toInstant();
            Instant edInstant = start.atStartOfDay(ZoneId.systemDefault()).toInstant();
            Long count = boardRepository.boardCount(Date.from(stInstant), Date.from(edInstant), item.getUserNo());


            Jandi jandi = Jandi.builder().userNo(item).jandiCount(count.intValue()).jandiDate(start).build();
            log.warn("{}'s jandi : {}", item, jandi);
            jandiRepository.save(jandi);
//                    result.add(jandi);
        }

//        jandiRepository.saveAll(result);
    }
}
