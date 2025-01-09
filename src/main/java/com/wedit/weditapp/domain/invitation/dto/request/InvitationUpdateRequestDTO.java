package com.wedit.weditapp.domain.invitation.dto.request;

import com.wedit.weditapp.domain.invitation.domain.Invitation;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class InvitationUpdateRequestDTO {
    @NotBlank(message = "groom cannot be blank")
    private String groom; // 신랑 이름

    @NotBlank(message = "bride cannot be blank")
    private String bride; // 신부 이름

    private String groomF; // 신랑 아버지 이름

    private String groomM;  // 신랑 어머니 이름

    private String brideF; // 신부 아버지 이름

    private String brideM; // 신부 어머니 이름

    @NotBlank(message = "address cannot be blank")
    private String address; // 주소

    private String extraAddress; // 상세 주소

    @NotBlank(message = "date cannot be blank")
    private LocalDate date; // 결혼식 날짜

    @NotBlank(message = "theme cannot be blank")
    private String theme; // 테마

    private String distribution; // 청첩장 URL

    private boolean guestBookOption; // 방명록 옵션

    private boolean decisionOption; // 참석 의사 수집 옵션

    private boolean accountOption; // 계좌 공개 옵션

    // BankAccountDTO 생성 후 활성화
    //private List<BankAccountDTO> bankAccounts; // 계좌 정보 리스트

    @Builder
    private InvitationUpdateRequestDTO(String groom, String bride, String groomF, String groomM, String brideF, String brideM, String address, String extraAddress, LocalDate date, String theme, String distribution, boolean guestBookOption, boolean decisionOption, boolean accountOption
                                       //, List<BankAccountDTO> bankAccounts
    ) {
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
        //this.bankAccounts = bankAccounts;
    }

    public static InvitationUpdateRequestDTO from(Invitation invitation
                                                  //, List<BankAccountDTO> bankAccounts
    ) {
        return InvitationUpdateRequestDTO.builder()
                .groom(invitation.getGroom())
                .bride(invitation.getBride())
                .groomF(invitation.getGroomF())
                .groomM(invitation.getGroomM())
                .brideF(invitation.getBrideF())
                .brideM(invitation.getBrideM())
                .address(invitation.getAddress())
                .extraAddress(invitation.getExtraAddress())
                .date(invitation.getDate())
                .theme(invitation.getTheme().name())
                .distribution(invitation.getDistribution())
                .guestBookOption(invitation.isGuestBookOption())
                .decisionOption(invitation.isDecisionOption())
                .accountOption(invitation.isAccountOption())
                //.bankAccounts(bankAccounts)
                .build();
    }
}