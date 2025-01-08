package com.wedit.weditapp.domain.decisions.domain;

import com.wedit.weditapp.domain.shared.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "decisions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Decisions extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer addPerson;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "invitation_id", nullable = false)
//    private Invitations invitations;


    @Builder
    private Decisions(String name, String phoneNumber, Integer addPerson){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addPerson = addPerson;
    }

    public static Decisions createDecision(String name, String phoneNumber, Integer addPerson){
        return Decisions.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .addPerson(addPerson)
                .build();
    }
}
