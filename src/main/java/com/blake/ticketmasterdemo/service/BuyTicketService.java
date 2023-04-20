package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.config.SystemConfig;
import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.enums.TicketStatus;
import com.blake.ticketmasterdemo.exception.ServiceException;
import com.blake.ticketmasterdemo.model.entity.Event;
import com.blake.ticketmasterdemo.model.entity.Member;
import com.blake.ticketmasterdemo.model.entity.Ticket;
import com.blake.ticketmasterdemo.model.vo.BuyTicketRequest;
import com.blake.ticketmasterdemo.model.vo.BuyTicketResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.repository.EventRepository;
import com.blake.ticketmasterdemo.repository.MemberRepository;
import com.blake.ticketmasterdemo.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BuyTicketService extends CommonService
            implements BaseApiService<BuyTicketRequest, BuyTicketResponse>{

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final SystemConfig systemConfig;

    private final MemberRepository memberRepository;
    @Override
    public BaseWebResponse<BuyTicketResponse> execute(BaseWebRequest<BuyTicketRequest> webRequest) {
        val request = webRequest.getTransReq();
        // check event exist and sale time is valid
        val currentEvent = eventRepository
                .findById(request.getEventId())
                .orElseThrow(() -> new ServiceException(ResponseStatus.EVENT_NOT_EXIST_ERROR_2002));
        checkEventAtSaleTime(currentEvent);
        int eventId = request.getEventId();
        int memberId = request.getMemberId();
        int buyQuantity = request.getQuantity();
        // check user exist
        Member member = memberRepository
                .findById(memberId)
                .orElseThrow(() -> new ServiceException(ResponseStatus.USER_NOT_EXIST_ERROR_3001));
        // check ticket is available
        List<Ticket> availableTickets = ticketRepository
                .findByEventIdAndStatusOrderByTicketNo(eventId, TicketStatus.AVAILABLE);
        if(availableTickets.isEmpty()){
            throw new ServiceException(ResponseStatus.TICKET_SOLD_OUT_ERROR_4001);
        }
        // check user can buy ticket
        Integer userTicketCount = ticketRepository
                .countByEventIdAndMemberId(eventId, memberId);
        if(userTicketCount >= systemConfig.getMaxTicketPurchasable()){
            throw new ServiceException(ResponseStatus.TICKET_MAX_PER_USER_ERROR_4002);
        }
        // buy ticket
        int quantityUserCanBuy = systemConfig.getMaxTicketPurchasable() - userTicketCount - buyQuantity;
        List<String> ticketNos = new ArrayList<>();
        for(int i = 0; i < quantityUserCanBuy; i++){
            Ticket ticket = availableTickets.get(i);
            ticket.setMemberId(memberId);
            ticket.setStatus(TicketStatus.SOLD);
            ticketRepository.save(ticket);
            ticketNos.add(ticket.getTicketNo());
        }
        BuyTicketResponse res = BuyTicketResponse.builder()
                .memberId(memberId)
                .memberEmail(member.getEmail())
                .result("SUCCESS")
                .eventId(eventId)
                .ticketNoList(ticketNos)
                .build();

        // update ticket status
        return packaging(res);
    }

    private void checkEventAtSaleTime(Event currentEvent) {
        val currentTime = getCurrentTime();
        if (currentTime.isBefore(currentEvent.getStartSaleTime()) ||
                currentTime.isAfter(currentEvent.getEndSaleTime())){
            throw new ServiceException(ResponseStatus.EVENT_NOT_AT_SALE_TIME_ERROR_2003);
        }
    }
}
