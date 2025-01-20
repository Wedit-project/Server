package com.wedit.weditapp.domain.comments.service;

import com.wedit.weditapp.domain.comments.domain.Comments;
import com.wedit.weditapp.domain.comments.domain.repository.CommentRepository;
import com.wedit.weditapp.domain.comments.dto.response.CommentResponseDTO;
import com.wedit.weditapp.global.error.ErrorCode;
import com.wedit.weditapp.global.error.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private static final int PAGE_SIZE = 4; // 한 페이지에 보여줄 방명록 개수

    private final CommentRepository commentRepository;

    public Page<CommentResponseDTO> findAllCommentsByInvitationId(Long invitationId, int page){

//        boolean existsInvitation = invitationRepository.existById(invitationId);
//        if(!existsInvitation){
//            throw new CommonException(ErrorCode.INVITATION_NOT_FOUND);
//        }

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("createdAt").descending());
        Page<Comments> comments = commentRepository.findByInvitationId(invitationId, pageable);

        return comments.map(CommentResponseDTO::from);
    }
}
