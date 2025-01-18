package com.wedit.weditapp.domain.invitation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedit.weditapp.domain.invitation.dto.request.InvitationCreateRequestDTO;
import com.wedit.weditapp.domain.invitation.service.InvitationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/invitations")
@RequiredArgsConstructor
public class InvitationController {
	private final InvitationService invitationService;

	@PostMapping
	public ResponseEntity<String> createInvitation(@Valid @ModelAttribute InvitationCreateRequestDTO dto) {
		// 초대장 생성
		Long invitationId = invitationService.createInvitation(dto);

		return ResponseEntity.ok("Invitation created successfully with ID: " + invitationId);
	}
}
