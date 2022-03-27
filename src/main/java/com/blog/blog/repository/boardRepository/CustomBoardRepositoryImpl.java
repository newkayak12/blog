package com.blog.blog.repository.boardRepository;

import com.blog.blog.repository.entity.Board;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.blog.blog.repository.entity.QBoard.board;
@Repository
public class CustomBoardRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository {
    public CustomBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public List<Board> fetchList(Long userNo, String searchText, Pageable page) {
        BooleanBuilder builder = new BooleanBuilder();


        builder.and(board.userNo.userNo.eq(userNo));
        if(!StringUtils.isEmpty(searchText)){
            builder.and(board.boardTitle.contains(searchText));
        }

        return from(board)
        .where(builder)
        .offset(page.getOffset())
        .limit(page.getPageSize())
        .fetch();
    }
}
