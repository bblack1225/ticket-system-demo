package com.blake.ticketmasterdemo.model.entity;

import com.blake.ticketmasterdemo.enums.MemberStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "member")
public class Member implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer userId;

        @Column(nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private MemberStatus status;

        @Column(nullable = false, updatable = false)
        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime createDate;

        @Column(nullable = false)
        @UpdateTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        private LocalDateTime updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Member member = (Member) o;
        return getUserId() != null && Objects.equals(getUserId(), member.getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
