package com.blog.blog.repository.entity;

import com.blog.blog.repository.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no", nullable = false)
    private Long boardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @Column(name = "board_title", length = 200)
    private String boardTitle;

    @Lob
    @Column(name = "board_content", columnDefinition = "TEXT")
    private String boardContent;

    @Column(name = "board_written_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date boardWrittenDate;

    @Column(name = "board_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date boardUpdatedDate;


}