package com.blog.blog.repository.jandiRepository;

import com.blog.blog.repository.entity.Jandi;
import com.blog.blog.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JandiRepository extends JpaRepository<Jandi,Long>{
    public Jandi findJandiByUserNoAndJandiDate(User userNo, LocalDate jandiDate);
}
