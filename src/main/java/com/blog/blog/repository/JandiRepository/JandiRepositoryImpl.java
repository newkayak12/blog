package com.blog.blog.repository.JandiRepository;

import com.blog.blog.repository.entity.Jandi;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class JandiRepositoryImpl extends QuerydslRepositorySupport implements JandiRepositoryCustom {
    public JandiRepositoryImpl() {
        super(Jandi.class);
    }

}
