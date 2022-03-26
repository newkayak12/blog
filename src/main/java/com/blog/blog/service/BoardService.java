package com.blog.blog.service;

import com.blog.blog.common.ResponseContainer.ResponseContainer;
import com.blog.blog.common.authorizations.tokenManager.TokenManager;
import com.blog.blog.repository.boardRepository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackOn = {Exception.class})
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Map<String,Object> fetchList(Long userNo, Integer page, Integer limit, String searchText) {
        return null;
    }

    public Map<String,Object> fetchOne(Long userNo, Long boardNo) {
        return null;
    }

    public Map<String, Object> writeBoard(Map<String, Object> data) {
        return null;
    }

    public Map<String, Object> modifyBoard(Map<String, Object> data) {
        return null;
    }

    public Map<String, Object> deleteBoard(Long userNo, Long boardNo) {
        return null;
    }
}
