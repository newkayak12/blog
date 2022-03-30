package com.blog.blog.service;

import com.blog.blog.common.exception.ServiceException;
import com.blog.blog.common.mapper.Mapper;
import com.blog.blog.repository.boardRepository.BoardRepository;
import com.blog.blog.repository.dto.BoardDto;
import com.blog.blog.repository.entity.Board;
import com.blog.blog.repository.entity.Jandi;
import com.blog.blog.repository.entity.User;
import com.blog.blog.repository.userRepository.UserRepository;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@Transactional(rollbackOn = {Exception.class})
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final  BoardRepository boardRepository;
    private final JandiService jandiService;

    public Map<String,Object> fetchList(Long userNo, Integer page, Integer limit, String searchText) {
        Pageable pageInfo = PageRequest.of(page,limit, Sort.by("boardWrittenDate").descending());
        log.warn("PAGE1 :: {}", pageInfo.getOffset());
        log.warn("PAGE2 :: {}", pageInfo.getPageNumber());
        log.warn("PAGE3 :: {}", pageInfo.getPageSize());
        Page<Board> list =  boardRepository.fetchList(userNo, searchText, pageInfo);
        Map<String,Object> result = new HashMap<>();
        result.put("pageInfo", pageInfo);
        result.put("hasNext", list.hasNext());
        result.put("totalCount", list.getTotalElements());
        result.put("list", list.getContent());
        return result;
    }

    public BoardDto fetchOne(Long userNo, Long boardNo) throws ServiceException {
        Board board = boardRepository.findBoardByBoardNoAndUserNo(boardNo, userRepository.findUserByUserNo(userNo));
        if(Objects.isNull(board)){
            throw new ServiceException("게시글이 존재하지 않습니다.");
        }
        BoardDto boardDto = Mapper.boardMapper(board);
        return boardDto;
    }

    public BoardDto writeBoard(Map<String, Object> data) throws ServiceException {
        User user = userRepository.findUserByUserNo((Long) data.get("userNo"));
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
        jandiService.plantJani(user);
        return Mapper.boardMapper(board);
    }

    public BoardDto modifyBoard(Map<String, Object> data) throws ServiceException {
        User user = userRepository.findUserByUserNo((Long) data.get("userNo"));
        if(Objects.isNull(user)){
            throw new ServiceException("잘못된 접근입니다.");
        }
        Board board = boardRepository.findBoardByBoardNoAndUserNo(Long.valueOf((Integer) data.get("boardNo")), user);
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
        Board board = boardRepository.findBoardByBoardNoAndUserNo(boardNo, userRepository.findUserByUserNo(userNo));
        boardRepository.delete(board);
        board = boardRepository.findBoardByBoardNoAndUserNo(boardNo, userRepository.findUserByUserNo(userNo));
        if(!Objects.isNull(board)){
            throw new ServiceException("삭제에 실패했습니다.");
        }
        return 1;
    }
}
