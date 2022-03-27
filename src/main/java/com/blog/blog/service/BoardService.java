package com.blog.blog.service;

import com.blog.blog.common.ResponseContainer.ResponseContainer;
import com.blog.blog.common.authorizations.tokenManager.TokenManager;
import com.blog.blog.common.exception.ServiceException;
import com.blog.blog.common.mapper.Mapper;
import com.blog.blog.repository.boardRepository.BoardRepository;
import com.blog.blog.repository.dto.BoardDto;
import com.blog.blog.repository.entity.Board;
import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@Transactional(rollbackOn = {Exception.class})
//@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Map<String,Object> fetchList(Long userNo, Integer page, Integer limit, String searchText) {
        Pageable pageInfo = PageRequest.of(page,limit, Sort.by("boardWrittenDate").descending());
        List<Board> list =  boardRepository.fetchList(userNo, searchText, pageInfo);
        Map<String,Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("list", list);
        return result;
    }

    public BoardDto fetchOne(Long userNo, Long boardNo) {
        Board board = boardRepository.findBoardByBoardNoAndUserNo(boardNo, userNo);
        BoardDto boardDto = Mapper.boardMapper(board);
        return boardDto;
    }

    public BoardDto writeBoard(Map<String, Object> data) throws ServiceException {
        User user = userRepository.findUserByUserNo(Long.valueOf((Integer) data.get("userNo")));
        if(Objects.isNull(user)){
            throw new ServiceException("잘못된 접근입니다.");
        }
        Board board = Board
                .builder()
                .userNo(user)
                .boardTitle((String) data.get("boardTitle"))
                .boardContent((String)data.get("boardContent"))
                .boardWrittenDate(new Date())
                .build();
        board = boardRepository.save(board);

        if(Objects.isNull(board.getBoardNo())){
            throw new ServiceException("작성에 실패했습니다.");
        }
        return Mapper.boardMapper(board);
    }

    public BoardDto modifyBoard(Map<String, Object> data) throws ServiceException {
        User user = userRepository.findUserByUserNo(Long.valueOf((Integer) data.get("userNo")));
        if(Objects.isNull(user)){
            throw new ServiceException("잘못된 접근입니다.");
        }
        Board board = boardRepository.findBoardByBoardNoAndUserNo(Long.valueOf((Integer) data.get("boardNo")), user.getUserNo());
        if(Objects.isNull(board)){
            throw new ServiceException("잘못된 접근입니다.");
        }
        if(!board.getBoardTitle().equals((String) data.get("boardTitle"))){
            board.setBoardTitle((String) data.get("boardTitle"));
        }
        if(!board.getBoardContent().equals((String) data.get("boardContent"))){
            board.setBoardContent((String) data.get("boardContent"));
        }
        board.setBoardUpdatedDate(new Date());
        boardRepository.save(board);
        return Mapper.boardMapper(board);
    }

    public Integer deleteBoard(Long userNo, Long boardNo) throws ServiceException {
        Board board = boardRepository.findBoardByBoardNoAndUserNo(boardNo, userNo);
        boardRepository.delete(board);
        board = boardRepository.findBoardByBoardNoAndUserNo(boardNo, userNo);
        if(!Objects.isNull(board)){
            throw new ServiceException("삭제에 실패했습니다.");
        }
        return 1;
    }
}
