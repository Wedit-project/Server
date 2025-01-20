package com.wedit.weditapp.domain.invitation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wedit.weditapp.domain.bankAccounts.service.BankAccountService;
import com.wedit.weditapp.domain.image.service.ImageService;
import com.wedit.weditapp.domain.invitation.domain.Invitation;
import com.wedit.weditapp.domain.invitation.domain.repository.InvitationRepository;
import com.wedit.weditapp.domain.invitation.dto.request.InvitationCreateRequestDTO;
import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.member.domain.repository.MemberRepository;
import com.wedit.weditapp.domain.shared.Theme;
import com.wedit.weditapp.global.error.ErrorCode;
import com.wedit.weditapp.global.error.exception.CommonException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitationService {
	private final InvitationRepository invitationRepository;
	private final ImageService imageService;
	private final MemberRepository memberRepository;
	private final BankAccountService bankAccountService;

	public Void createInvitation(Long memberId, InvitationCreateRequestDTO request, List<MultipartFile> images) {
		Member member = getMember(memberId);
		// Invitation 생성
		Invitation invitation = Invitation.createInvitation(
			member,
			request.getGroom(),
			request.getBride(),
			request.getGroomF(),
			request.getGroomM(),
			request.getBrideF(),
			request.getBrideM(),
			request.getAddress(),
			request.getExtraAddress(),
			request.getDate(),
			Theme.valueOf(request.getTheme()), // 문자열을 Enum으로 변환
			request.getDistribution(),
			request.isGuestBookOption(),
			request.isDecisionOption(),
			request.isAccountOption()
		);

		// 초대장 저장
		invitationRepository.save(invitation);

		// 계좌 정보 저장
		if (request.isAccountOption() && request.getBankAccounts() != null) {
			bankAccountService.createBankAccounts(request.getBankAccounts(), invitation);
		}

		// 이미지 저장
		imageService.saveImages(images, invitation);

		return null;
		//return InvitationResponseDTO.from(invitationRepository.save(invitation));
	}

	private Member getMember(Long memberId) {
		return memberRepository.findById(memberId)
			.orElseThrow(() -> new CommonException(ErrorCode.USER_NOT_FOUND));
	}
}