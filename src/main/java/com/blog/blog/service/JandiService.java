package com.blog.blog.service;

import com.blog.blog.repository.jandiRepository.JandiRepository;
import com.blog.blog.repository.boardRepository.BoardRepository;
import com.blog.blog.repository.entity.Jandi;
import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class JandiService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final JandiRepository jandiRepository;

//
//    public void jandiCronJob(){
//        List<Jandi> result = new ArrayList<>();
//        List<User> userList = userRepository.findAll();
//        for(User item : userList) {
//            LocalDate now = LocalDate.now();
//            LocalDate start = now.minusDays(1);
//            Instant edInstant = now.atStartOfDay(ZoneId.systemDefault()).toInstant();
//            Instant stInstant = start.atStartOfDay(ZoneId.systemDefault()).toInstant();
//
//            log.warn("stInstant : {}", stInstant);
//            Long count = boardRepository.boardCount(Date.from(stInstant), Date.from(edInstant), item.getUserNo());
//            log.warn("count : {}", count);
//
//            Jandi jandi = Jandi.builder().userNo(item).jandiCount(count.intValue()).jandiDate(start).build();
//            log.warn("{}'s jandi : {}", item, jandi);
////            jandiRepository.save(jandi);
//                    result.add(jandi);
//        }
//        jandiRepository.saveAll(result);
//    }

    public void plantJani(User user){
        Jandi jandi = jandiRepository.findJandiByUserNoAndJandiDate(user, LocalDate.now());
        if(Objects.isNull(jandi)){
            jandi = Jandi.builder().userNo(user).jandiCount(0).jandiDate(LocalDate.now()).build();
        }
        jandi.setJandiCount(jandi.getJandiCount()+1);
        jandiRepository.save(jandi);
    }



    public List<Integer> fetchList(Long userNo, Integer gap) {
        User user = userRepository.findUserByUserNo(userNo);
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusDays(gap);
        List<Integer> list = new ArrayList<>();
        start.datesUntil(now.plusDays(1)).forEach(date->{
            log.warn("?????????????? {}", date);
            Jandi temp = jandiRepository.findJandiByUserNoAndJandiDate(user,date);
            if(Objects.isNull(temp)){
                list.add(0);
            } else {
                list.add(temp.getJandiCount());
            }

        });

        return list;
    }
}
