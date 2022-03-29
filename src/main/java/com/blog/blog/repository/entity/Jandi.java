package com.blog.blog.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@ToString
@Table(name = "jandi")
public class Jandi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jandi_no", nullable = false)
    private Long jandiNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User userNo;

    @Column(name = "jandi_count")
    private Integer jandiCount;

    @Column(name = "jandi_date")
    private LocalDate jandiDate;

}