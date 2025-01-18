package com.wedit.weditapp.global.oauth2.domain;

import com.wedit.weditapp.domain.shared.MemberRole;
import com.wedit.weditapp.domain.shared.MemberStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
    private String email;
    private MemberRole role;
    private MemberStatus status;
    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, MemberRole role, MemberStatus status) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
