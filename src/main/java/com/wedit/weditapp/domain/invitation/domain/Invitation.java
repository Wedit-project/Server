package com.wedit.weditapp.domain.invitation.domain;

import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.shared.BaseTimeEntity;
import com.wedit.weditapp.domain.shared.Theme;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "invitations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(length = 20, nullable = false)
    private String groom; // 신랑 이름

    @Column(length = 20, nullable = false)
    private String bride; // 신부 이름

    @Column(length = 20)
    private String groomF; // 신랑 아버지 이름

    @Column(length = 20)
    private String groomM; // 신랑 어머니 이름

    @Column(length = 20)
    private String brideF; // 신부 아버지 이름

    @Column(length = 20)
    private String brideM; // 신부 어머니 이름

    @Column(length = 255, nullable = false)
    private String address; // 주소

    @Column(length = 255)
    private String extraAddress; // 세부 주소

    @Column(nullable = false)
    private LocalDate date; // 결혼식 날짜

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Theme theme; // 청첩장 테마

    @Column(length = 255)
    private String distribution; // 청첩장 url

    @Column(nullable = false)
    private boolean guestBookOption; // 방명록 여부

    @Column(nullable = false)
    private boolean decisionOption; // 참석 의사 수집 여부

    @Column(nullable = false)
    private boolean accountOption; // 계좌 정보 공개 여부

    @Builder
    private Invitation(Member member, String groom, String bride, String groomF, String groomM, String brideF, String brideM, String address, String extraAddress, LocalDate date, Theme theme, String distribution, boolean guestBookOption, boolean decisionOption, boolean accountOption) {
        // 필수 필드 검증
        if (member == null || groom == null || bride == null || address == null || date == null || theme == null) {
            throw new IllegalArgumentException("필수 필드 누락");
        }

        this.member = member;
        this.groom = groom;
        this.bride = bride;
        this.groomF = groomF;
        this.groomM = groomM;
        this.brideF = brideF;
        this.brideM = brideM;
        this.address = address;
        this.extraAddress = extraAddress;
        this.date = date;
        this.theme = theme;
        this.distribution = distribution;
        this.guestBookOption = guestBookOption;
        this.decisionOption = decisionOption;
        this.accountOption = accountOption;
    }

    public static Invitation createInvitation(Member member, String groom, String bride, String groomF, String groomM, String brideF, String brideM, String address, String extraAddress, LocalDate date, Theme theme, String distribution) {
        return Invitation.builder()
                .member(member)
                .groom(groom)
                .bride(bride)
                .groomF(groomF)
                .groomM(groomM)
                .brideF(brideF)
                .brideM(brideM)
                .address(address)
                .extraAddress(extraAddress)
                .date(date)
                .theme(theme)
                .distribution(distribution)
                .guestBookOption(false)
                .decisionOption(false)
                .accountOption(false)
                .build();
    }

    public void tureGuestBook() {
        this.guestBookOption = true;
    }

    public void trueDecisionOption() {
        this.decisionOption = true;
    }

    public void trueAccountOption() {
        this.accountOption = true;
    }

    public void update(String groom, String bride, String groomF, String groomM, String brideF, String brideM, String address, String extraAddress, LocalDate date, Theme theme, String distribution, boolean guestBookOption, boolean decisionOption, boolean accountOption) {
        if (member == null || groom == null || bride == null || address == null || date == null || theme == null) {
            throw new IllegalArgumentException("필수 필드 누락");
        }

        this.groom = groom;
        this.bride = bride;
        this.groomF = groomF;
        this.groomM = groomM;
        this.brideF = brideF;
        this.brideM = brideM;
        this.address = address;
        this.extraAddress = extraAddress;
        this.date = date;
        this.theme = theme;
        this.distribution = distribution;
        this.guestBookOption = guestBookOption;
        this.decisionOption = decisionOption;
        this.accountOption = accountOption;
    }
}
