package com.blog.blog.repository.boardRepository;

import com.blog.blog.repository.entity.Board;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.blog.blog.repository.entity.QBoard.board;

@Slf4j
public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {
    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public List<Board> fetchList(Long userNo, String searchText, Pageable page) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(board.userNo.userNo.eq(userNo));
        if(!StringUtils.isEmpty(searchText)){
            builder.and(board.boardTitle.contains(searchText));
        }
        List<Board>result = from(board)
        .where(builder)
        .offset(page.getOffset())
        .limit(page.getPageSize())
        .fetch();

        log.warn("LIST :: {}", result);
        return result;
    }
}
