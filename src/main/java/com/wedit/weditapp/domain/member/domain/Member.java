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
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status;

    // Builder를 통해서만 객체를 생성하도록 (일반 생성자는 protected)
    @Builder
    private Member(String email, String name) {
        this.email = email;
        this.name = name;
        this.role = MemberRole.USER;
        this.status = MemberStatus.ACTIVE;
    }

    // 사용자 생성 Method
    public static Member createUser(String email, String name) {
        return Member.builder()
                .email(email)
                .name(name)
                .build();
    }

    // 사용자 이메일 변경 Method
    public void updateEmail(String newEmail) {
        if (newEmail == null || newEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("공백이면 안됩니다.");
        }
        this.name = newEmail;
    }

    // 사용자 이름 변경 Method
    public void updateName(String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("공백이면 안됩니다.");
        }
        this.name = newName;
    }

    // 사용자 상태 변경 Method
    public void deactivate() {
        this.status = MemberStatus.INACTIVE;
    }

    // 사용자 역할 변경 Method
    public void updateRole(MemberRole newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("공백이면 안됩니다.");
        }
        this.role = newRole;
    }
}
