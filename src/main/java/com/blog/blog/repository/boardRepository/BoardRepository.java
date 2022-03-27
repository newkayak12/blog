package com.blog.blog.repository.boardRepository;

import com.blog.blog.repository.entity.Board;
import com.blog.blog.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>{
    public Board findBoardByBoardNoAndUserNo(Long boardNo, User userNo);
}
