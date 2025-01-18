package com.wedit.weditapp.domain.invitation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wedit.weditapp.domain.invitation.domain.Invitation;
import com.wedit.weditapp.domain.invitation.dto.request.InvitationCreateRequestDTO;
import com.wedit.weditapp.domain.invitation.service.InvitationService;
import com.wedit.weditapp.domain.member.domain.Member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/invitations")
@RequiredArgsConstructor
public class InvitationController {
	private final InvitationService invitationService;

	@PostMapping
	public ResponseEntity<Long> createInvitation(
		@RequestParam("images") List<MultipartFile> images,
		@Validated @ModelAttribute InvitationCreateRequestDTO request
	) {
		// Member 객체는 인증 정보를 통해 가져와야 함
		Member member = getAuthenticatedMember();

		Invitation invitation = invitationService.saveInvitationWithImages(member, request, images);
		return ResponseEntity.ok(invitation.getId());
	}

	private Member getAuthenticatedMember() {
		// TODO: 인증된 사용자 정보를 가져오는 로직 구현
		return new Member();
	}
}
