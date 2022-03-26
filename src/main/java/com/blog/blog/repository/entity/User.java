package com.blog.blog.repository.entity;

import com.blog.blog.common.EntityListener.UserLastSignListener;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@EntityListeners(UserLastSignListener.class)
@ToString
@DynamicUpdate
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no", nullable = false)
    private Long userNo;

    @Column(name = "user_id", length = 40)
    private String userId;

    @Column(name = "user_nickname", length = 45)
    private String userNickname;

    @Column(name = "user_password", length = 200)
    private String userPassword;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_reg_date")
    private Date userRegDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_last_signed_date")
    private Date userLastSignedDate;

    @OneToMany(mappedBy = "userNo")
    private List<Board> boards = new ArrayList<>();
}