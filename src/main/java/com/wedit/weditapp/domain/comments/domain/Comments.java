package com.wedit.weditapp.domain.comments.domain;

import com.wedit.weditapp.domain.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "invitation_id", nullable = false)
//    private Invitations invitations;

    @Builder
    private Comments(String name, String content){
        this.name = name;
        this.content = content;
    }

    public static Comments createComment(String name, String content){
        return Comments.builder()
                .name(name)
                .content(content)
                .build();
    }

}
