package com.wedit.weditapp.domain.invitation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wedit.weditapp.domain.image.service.ImageService;
import com.wedit.weditapp.domain.invitation.domain.Invitation;
import com.wedit.weditapp.domain.invitation.domain.repository.InvitationRepository;
import com.wedit.weditapp.domain.invitation.dto.request.InvitationCreateRequestDTO;
import com.wedit.weditapp.domain.member.domain.Member;
import com.wedit.weditapp.domain.shared.Theme;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvitationService {
	private final InvitationRepository invitationRepository;
	private final ImageService imageService;

	/**
	 * Invitation 저장 및 이미지 처리
	 *
	 * @param member  요청한 사용자 정보
	 * @param request Invitation 생성 요청 DTO
	 * @param images  업로드할 이미지 리스트
	 * @return 저장된 Invitation
	 */
	@Transactional
	public Invitation saveInvitationWithImages(Member member, InvitationCreateRequestDTO request, List<MultipartFile> images) {
		// Invitation 생성
		Invitation invitation = Invitation.builder()
			.member(member)
			.groom(request.getGroom())
			.bride(request.getBride())
			.groomF(request.getGroomF())
			.groomM(request.getGroomM())
			.brideF(request.getBrideF())
			.brideM(request.getBrideM())
			.address(request.getAddress())
			.extraAddress(request.getExtraAddress())
			.date(request.getDate())
			.theme(Theme.valueOf(request.getTheme()))
			.guestBookOption(request.isGuestBookOption())
			.decisionOption(request.isDecisionOption())
			.accountOption(request.isAccountOption())
			.build();

		invitation = invitationRepository.save(invitation);

		// 이미지 저장
		imageService.saveImages(images, invitation);

		return invitation;
	}
}