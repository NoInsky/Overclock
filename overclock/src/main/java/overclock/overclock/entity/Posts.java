package overclock.overclock.entity;

import lombok.*;
import overclock.overclock.model.Address;
import overclock.overclock.model.BoardType;
import overclock.overclock.model.PartsType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "member")
@Builder
@AllArgsConstructor

public class Posts extends BaseEntity{ //게시물

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    private String title; //제목

    private String content;

    private int viewCount; //조회수

//    @Enumerated
//    private PartsType partsType;
    private String partsType; //게시판 카테고리

    @Embedded
    private Address address; //주소

    @Builder.Default
    @OneToMany(mappedBy = "ItemImg", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ItemImg> itemImgList = new ArrayList<>();


    @Builder
    public Posts(Member member, String title, String content,
                int viewCount, BoardType boardType, Address address) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.address = address;
        this.boardType = boardType;
    }
}
