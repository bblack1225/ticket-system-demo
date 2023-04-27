package com.blake.ticketmasterdemo.repository;

import com.blake.ticketmasterdemo.model.entity.BuyTicketLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BuyTicketLogRepository extends JpaRepository<BuyTicketLog, Integer>, JpaSpecificationExecutor<BuyTicketLog>
{
}
