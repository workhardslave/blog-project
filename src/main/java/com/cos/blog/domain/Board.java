package com.cos.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 쓸 예정, <html> 태그가 섞여서 디자인 됨

    @ColumnDefault("0")
    private int count; // 조회수

    /* @ManyToOne의 기본(default) 전략 : EAGER
    *  @OneToMany의 기본(default) 전략 : LAZY
    * */
    @ManyToOne(fetch = FetchType.EAGER) // Many == Board, One == User
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. (FK 저장), 그러나 자바는 오브젝트를 저장할 수 있다.

    /* @OneToMany가 지정되면 JPA에서는 무조건 여러 개의 데이터를 저장하기 위해 별도의 테이블을 생성
    *  별도의 테이블 생성을 원하지 않으면 특정 테이블을 조인할 것이라고 명시하거나(@JoinTable)
    *  기존 테이블을 이용해서 조인한다고 표현해주어야 함(@JoinColumn)
    *
    *  @JoinTable : 자동으로 생성되는 테이블 대신에 별도의 이름을 가진 테이블을 생성할 때 사용
    *  @JoinColumn : 이미 존재하는 테이블에 칼럼을 추가할 때 사용
    * */

    // JoinColumn을 하지 않았음 : 원자성 원칙이 깨지기 때문에
    // mappedBy : 연관관계의 주인이 아니다. (난 FK가 아니야~, DB에 컬럼 만들지마~)
    // FetchType.EAGER : 엔티티를 조회할 때 연관된 엔티티도 함께 조회, FetchType.LAZY : 엔티티를 조회할 때 연관된 엔티티를 실제 사용할 때 조회
    // cascadeType.REMOVE : 엔티티를 삭제시 연관된 엔티티도 같이 삭제
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    // Board가 호출되어 Reply를 가져올 때, Reply 안에 있는 Board를 가져오지 않게 설정 : 무한 참조 방지
    @JsonIgnoreProperties({"board"}) // JSON으로 파싱을 하지 않는다.
    @OrderBy("id DESC")
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Board(String title, String content, int count, User user) {
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
