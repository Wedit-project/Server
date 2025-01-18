package com.wedit.weditapp.domain.image.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wedit.weditapp.domain.image.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	// 특정 Invitation과 location으로 이미지 조회
	Optional<Image> findByInvitationIdAndLocation(Long invitationId, int location);

	// 특정 Invitation의 모든 이미지를 location 순서대로 조회
	List<Image> findByInvitationIdOrderByLocation(Long invitationId);
}
