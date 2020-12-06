package com.cos.blog.domain;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne // Many == Reply, One == Board
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne // Many == Reply, One == User
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    public void saveReply(Board board, User user, String content) {
        this.board = board;
        this.user = user;
        this.content = content;
    }
}
