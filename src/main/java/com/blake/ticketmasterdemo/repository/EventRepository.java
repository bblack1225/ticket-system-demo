package com.blake.ticketmasterdemo.repository;

import com.blake.ticketmasterdemo.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

    boolean existsByEventName(String eventName);
}