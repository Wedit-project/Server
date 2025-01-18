package com.wedit.weditapp.domain.invitation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wedit.weditapp.domain.image.domain.Image;
import com.wedit.weditapp.domain.image.domain.repository.ImageRepository;
import com.wedit.weditapp.domain.invitation.domain.Invitation;
import com.wedit.weditapp.domain.invitation.domain.repository.InvitationRepository;
import com.wedit.weditapp.domain.invitation.dto.request.InvitationCreateRequestDTO;
import com.wedit.weditapp.domain.shared.S3Service;
import com.wedit.weditapp.domain.shared.Theme;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvitationService {
	private final InvitationRepository invitationRepository;
	private final ImageRepository imageRepository;
	private final S3Service s3Service;

	@Transactional
	public Long createInvitation(InvitationCreateRequestDTO dto) {
		// 1. DTO 데이터 검증
		if (dto.getImages() == null || dto.getImages().size() != 4) {
			throw new IllegalArgumentException("4장의 이미지를 업로드해야 합니다.");
		}

		// 2. 초대장 저장
		Invitation invitation = Invitation.builder()
			.groom(dto.getGroom())
			.bride(dto.getBride())
			.groomF(dto.getGroomF())
			.groomM(dto.getGroomM())
			.brideF(dto.getBrideF())
			.brideM(dto.getBrideM())
			.address(dto.getAddress())
			.extraAddress(dto.getExtraAddress())
			.date(dto.getDate())
			.theme(Theme.valueOf(dto.getTheme().toUpperCase())) // Enum 변환
			.distribution(dto.getDistribution())
			.guestBookOption(dto.isGuestBookOption())
			.decisionOption(dto.isDecisionOption())
			.accountOption(dto.isAccountOption())
			.build();
		invitationRepository.save(invitation);

		// 3. 이미지 파일 S3 업로드 및 저장
		List<Image> images = new ArrayList<>();
		for (int i = 0; i < dto.getImages().size(); i++) {
			MultipartFile file = dto.getImages().get(i);
			String imageUrl = s3Service.upload(file); // S3 업로드 후 URL 반환

			Image image = Image.builder()
				.url(imageUrl)
				.location(i + 1) // 1, 2, 3, 4로 location 부여
				.invitation(invitation)
				.build();
			images.add(image);
		}
		imageRepository.saveAll(images);

		// 초대장 ID 반환
		return invitation.getId();
	}
}

