package com.blog.blog.repository.JandiRepository;

import com.blog.blog.repository.entity.Jandi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JandiRepository extends JpaRepository<Jandi,Long>, JandiRepositoryCustom {
}
