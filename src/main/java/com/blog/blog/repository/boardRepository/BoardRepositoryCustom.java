package com.blog.blog.repository.boardRepository;

import com.blog.blog.repository.entity.Board;
import com.querydsl.core.QueryResults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    public Page<Board> fetchList(Long userNo, String searchText, Pageable page);
}
