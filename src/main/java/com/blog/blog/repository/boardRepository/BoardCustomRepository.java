package com.blog.blog.repository.boardRepository;

import com.blog.blog.repository.entity.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface BoardCustomRepository {
    List<Board> fetchList(Long userNo, String searchText, Pageable page);
}
