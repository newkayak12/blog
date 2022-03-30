package com.blog.blog.controller;

import com.blog.blog.common.ResponseContainer.ResponseContainer;
import com.blog.blog.common.authorizations.annotation.Auth;
import com.blog.blog.service.JandiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/jandi")
@RequiredArgsConstructor
public class JandiController {
    private final JandiService jandiService;

    @RequestMapping(value = "/fetchList", method = RequestMethod.GET)
    @Auth
    public ResponseContainer fetchList(@RequestHeader(value = "Authorization") Object authorization, Integer gap){
        Long userNo = Long.valueOf((Integer)((Map<String,Object>) authorization).get("userNo"));
        return new ResponseContainer(200, "", jandiService.fetchList(userNo, gap));
    }
}
