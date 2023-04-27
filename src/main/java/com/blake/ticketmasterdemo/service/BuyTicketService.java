package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.config.SystemConfig;
import com.blake.ticketmasterdemo.enums.MemberStatus;
import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.enums.TicketStatus;
import com.blake.ticketmasterdemo.exception.ServiceException;
import com.blake.ticketmasterdemo.model.entity.Member;
import com.blake.ticketmasterdemo.model.entity.Ticket;
import com.blake.ticketmasterdemo.model.vo.BuyTicketRequest;
import com.blake.ticketmasterdemo.model.vo.BuyTicketResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.repository.EventRepository;
import com.blake.ticketmasterdemo.repository.MemberRepository;
import com.blake.ticketmasterdemo.repository.TicketRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BuyTicketService extends CommonService
            implements BaseApiService<BuyTicketRequest, BuyTicketResponse>{

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final SystemConfig systemConfig;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public BaseWebResponse<BuyTicketResponse> execute(BaseWebRequest<BuyTicketRequest> webRequest) {
            val request = webRequest.getTransReq();
            int eventId = request.getEventId();
            int memberId = request.getMemberId();
            int requestQuantity = request.getQuantity();
            // check event exist and sale time is valid
            checkEventAtSaleTime(eventId);

            // check user exist
            val member = obtainMember(memberId);
            log.info("current memberId: {}", member.getMemberId());

            int quantityUserCanBuy = obtainMemberTicketQuantity(eventId, memberId, requestQuantity);

            // buy ticket
            val ticketNoBuySuccessfully = buyTicket(eventId, memberId, quantityUserCanBuy);
            log.info("member Id: {} has bought ticketNo: {}", memberId, ticketNoBuySuccessfully);

            BuyTicketResponse res = BuyTicketResponse.builder()
                    .memberId(memberId)
                    .memberEmail(member.getEmail())
                    .result("SUCCESS")
                    .eventId(eventId)
                    .ticketNoList(ticketNoBuySuccessfully)
                    .build();
            return packaging(res);
    }

    private void buyTicketAndGetTicketNo(List<Ticket> availableTickets, int memberId) {
        availableTickets.forEach(ticket -> {
            ticket.setMemberId(memberId);
            ticket.setStatus(TicketStatus.SOLD);
        });
        ticketRepository.saveAll(availableTickets);
    }

    private Integer obtainMemberTicketQuantity(int eventId, int memberId, int requestQuantity) {
        val ticketMemberAlreadyBuy = ticketRepository
                .countByEventIdAndMemberId(eventId, memberId);
        if(ticketMemberAlreadyBuy + requestQuantity > systemConfig.getMaxTicketPurchasable()){
            throw new ServiceException(ResponseStatus.TICKET_MAX_PER_USER_ERROR_4002);
        }

        // 會員可以買幾張票
        int countMemberCanBuy = systemConfig.getMaxTicketPurchasable() - ticketMemberAlreadyBuy;
       // 如果可以買三張，但只買一張的話
        return Math.min(countMemberCanBuy, requestQuantity);
    }

    private List<String> buyTicket(int eventId, int memberId, int quantityUserCanBuy) {
        PageRequest pageRequest = PageRequest
                .of(0, quantityUserCanBuy, Sort.by(Sort.Direction.ASC, "ticketNo"));

        val availableTickets = ticketRepository
                .findByEventIdAndStatusOrderByTicketNo(eventId, TicketStatus.AVAILABLE, pageRequest);
        List<String> ticketNoList = availableTickets.stream().map(Ticket::getTicketNo).toList();
        log.info("MemberId: {} get ticketNo: {}", memberId, ticketNoList);
        if(availableTickets.isEmpty()){
            throw new ServiceException(ResponseStatus.TICKET_SOLD_OUT_ERROR_4001);
        }

        if(quantityUserCanBuy > availableTickets.size()){
            throw new ServiceException(ResponseStatus.TICKET_INSUFFICIENT_REMAINING_TICKETS_ERROR_4004);
        }

        buyTicketAndGetTicketNo(availableTickets, memberId);
        return ticketNoList;
    }

    private Member obtainMember(int memberId) {
        val member = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new ServiceException(ResponseStatus.USER_NOT_EXIST_ERROR_3001));
        if(!member.getStatus().equals(MemberStatus.ACTIVE)){
            throw new ServiceException(ResponseStatus.USER_NOT_ACTIVE_ERROR_3003);
        }
        return member;
    }

    private void checkEventAtSaleTime(@NotNull int eventId) {
        val currentEvent = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new ServiceException(ResponseStatus.EVENT_NOT_EXIST_ERROR_2002));
        val currentTime = getCurrentTime();
        if (currentTime.isBefore(currentEvent.getStartSaleTime()) ||
                currentTime.isAfter(currentEvent.getEndSaleTime())){
            throw new ServiceException(ResponseStatus.EVENT_NOT_AT_SALE_TIME_ERROR_2003);
        }
    }
}
