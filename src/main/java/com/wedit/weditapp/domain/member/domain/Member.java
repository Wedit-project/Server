package com.wedit.weditapp.domain.member.domain;

import com.wedit.weditapp.domain.shared.BaseTimeEntity;
import com.wedit.weditapp.domain.shared.MemberRole;
import com.wedit.weditapp.domain.shared.MemberStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MemberRole role;

    @Column(nullable = false, unique = true)
    private String refresh_token;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status;

    // Builder를 통해서만 객체를 생성하도록 (일반 생성자는 protected)
    @Builder
    private Member(String kakaoId, String email, String name, MemberRole role, String password, MemberStatus status) {
        this.email = email;
        this.name = name;
        this.role = role != null ? role : MemberRole.USER;     // 유저 역할 기본값 USER
        this.password = password;
        this.status = status != null ? status : MemberStatus.ACTIVE; // 유저 상태 기본값 ACTIVE
    }

    public static Member createUser(String email, String name, String password) {
        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .role(MemberRole.USER) // 기본값 USER
                .status(MemberStatus.ACTIVE) // 기본값 ACTIVE
                .build();
    }

    // 상태 변경 예시 메서드
    public void deactivate() {
        this.status = MemberStatus.INACTIVE;
    }
}
