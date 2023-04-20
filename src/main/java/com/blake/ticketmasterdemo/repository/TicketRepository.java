package com.blake.ticketmasterdemo.repository;

import com.blake.ticketmasterdemo.enums.TicketStatus;
import com.blake.ticketmasterdemo.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    List<Ticket> findByEventIdAndStatusOrderByTicketNo(Integer eventId, TicketStatus status);
    Integer countByEventIdAndMemberId(Integer eventId, Integer memberId);
}