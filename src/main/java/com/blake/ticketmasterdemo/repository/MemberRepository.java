package com.blake.ticketmasterdemo.repository;

import com.blake.ticketmasterdemo.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MemberRepository extends JpaRepository<Member, Integer>, JpaSpecificationExecutor<Member> {

}