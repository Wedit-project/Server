package com.wedit.weditapp.global.oauth2.dto;

import com.wedit.weditapp.global.oauth2.userInfo.KakaoOAuth2UserInfo;
import com.wedit.weditapp.global.oauth2.userInfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

    // 이건 나중에 서비스나 엔티티 생성 로직에서 처리하는 게 더 나음
//    public User toEntity(SocialType socialType, OAuth2UserInfo oauth2UserInfo) {
//        return User.builder()
//                .socialType(socialType)
//                .socialId(oauth2UserInfo.getId())
//                .email(UUID.randomUUID() + "@socialUser.com")
//                .nickname(oauth2UserInfo.getNickname())
//                .imageUrl(oauth2UserInfo.getImageUrl())
//                .role(Role.GUEST)
//                .build();
//    }

}
