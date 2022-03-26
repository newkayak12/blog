package com.blog.blog.controller;

import com.blog.blog.common.ResponseContainer.ResponseContainer;
import com.blog.blog.common.authorizations.annotation.Auth;
import com.blog.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @RequestMapping(value = "/fetchList", method = RequestMethod.GET)
    @Auth
    public ResponseContainer fetchList(@RequestHeader(value = "Authorization") Object authorization, @RequestParam Integer page, @RequestParam Integer limit, @RequestParam String searchText){
       Long _userNo = Long.valueOf((Integer)((Map<String,Object>) authorization).get("userNo"));
        return new ResponseContainer(200, "", boardService.fetchList(_userNo, page, limit, searchText);)
    }
    @RequestMapping(value = "/fetchOne", method = RequestMethod.GET)
    @Auth
    public ResponseContainer fetchOne(@RequestHeader(value = "Authorization") Object authorization, @RequestParam Integer boardNo){
        Long _boardNo = Long.valueOf(boardNo);
        Long _userNo = Long.valueOf((Integer)((Map<String,Object>) authorization).get("userNo"));
        return new ResponseContainer(200, "", boardService.fetchOne(_userNo,_boardNo));
    }
    @RequestMapping(value = "/writeBoard", method = RequestMethod.POST)
    @Auth
    public ResponseContainer writeBoard(@RequestHeader(value = "Authorization") Object authorization, @RequestBody Map<String,Object> data){
        Long _userNo = Long.valueOf((Integer)((Map<String,Object>) authorization).get("userNo"));
        data.put("userNo",_userNo);
        Map<String,Object> result = null;
        result = boardService.writeBoard(data);
        return new ResponseContainer(200, "작성이 완료되었습니다.",result );
    }
    @RequestMapping(value = "/modifyBoard", method = RequestMethod.PATCH)
    @Auth
    public ResponseContainer modifyBoard(@RequestHeader(value = "Authorization") Object authorization, @RequestBody Map<String,Object> data){
        Long _userNo = Long.valueOf((Integer)((Map<String,Object>) authorization).get("userNo"));
        data.put("userNo",_userNo);
        Map<String,Object> result = null;
        result = boardService.modifyBoard(data);
        return new ResponseContainer(200, "수정가 완료되었습니다.",result );
    }
    @RequestMapping(value = "/deleteBoard", method = RequestMethod.DELETE)
    @Auth
    public ResponseContainer deleteBoard(@RequestHeader(value = "Authorization") Object authorization, @RequestParam Integer boardNo){
        Long _boardNo = Long.valueOf(boardNo);
        Long _userNo = Long.valueOf((Integer)((Map<String,Object>) authorization).get("userNo"));
        Map<String,Object> result = null;
        result = boardService.deleteBoard(_userNo,_boardNo);
        return new ResponseContainer(200, "삭제가 완료되었습니다.",result );
    }
}
