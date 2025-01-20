package com.wedit.weditapp.domain.comments.domain;

import com.wedit.weditapp.domain.invitation.domain.Invitation;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitation_id", nullable = false)
    private Invitation invitation;

    @Builder
    private Comments(String name, String content, Invitation invitation){
        this.name = name;
        this.content = content;
        this.invitation = invitation;
    }

    public static Comments createComment(String name, String content, Invitation invitation){
        return Comments.builder()
                .name(name)
                .content(content)
                .invitation(invitation)
                .build();
    }

}
