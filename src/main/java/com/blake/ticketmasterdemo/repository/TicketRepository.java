package com.blake.ticketmasterdemo.repository;

import com.blake.ticketmasterdemo.enums.TicketStatus;
import com.blake.ticketmasterdemo.model.entity.Ticket;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = """
                SELECT t FROM Ticket t WHERE t.eventId = :eventId AND t.status = :status
                """)
    List<Ticket> findByEventIdAndStatusOrderByTicketNo(Integer eventId, TicketStatus status, Pageable pageable);
    Integer countByEventIdAndMemberId(Integer eventId, Integer memberId);
}