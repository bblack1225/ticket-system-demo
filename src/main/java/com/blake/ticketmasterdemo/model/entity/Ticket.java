package com.blake.ticketmasterdemo.model.entity;


import com.blake.ticketmasterdemo.enums.TicketStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @Column(nullable = false)
    private Integer eventId;

    @Column(nullable = false)
    private String ticketNo;

    @Nullable
    private Integer userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return getTicketId() != null && Objects.equals(getTicketId(), ticket.getTicketId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
