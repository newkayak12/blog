package com.blog.blog.repository.boardRepository;

import com.blog.blog.repository.entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardCustomRepository {
    List<Board> fetchList(Long userNo, String searchText, Pageable page);
}
