package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.enums.EventStatus;
import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.enums.TicketStatus;
import com.blake.ticketmasterdemo.exception.ServiceException;
import com.blake.ticketmasterdemo.model.entity.Event;
import com.blake.ticketmasterdemo.model.entity.Ticket;
import com.blake.ticketmasterdemo.model.vo.CreateEventRequest;
import com.blake.ticketmasterdemo.model.vo.CreateEventResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.repository.ArtistRepository;
import com.blake.ticketmasterdemo.repository.EventRepository;
import com.blake.ticketmasterdemo.repository.TicketRepository;
import com.blake.ticketmasterdemo.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.leftPad;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateEventService extends CommonService
        implements BaseApiService<CreateEventRequest, CreateEventResponse>{

    private final EventRepository eventRepository;
    private final ArtistRepository artistRepository;

    private final TicketRepository ticketRepository;
    @Override
    public BaseWebResponse<CreateEventResponse> execute(BaseWebRequest<CreateEventRequest> webRequest) {
        val request = webRequest.getTransReq();
        if(!artistRepository.existsById(request.getArtistId())){
            throw new ServiceException(ResponseStatus.ARTIST_NOT_EXIST_ERROR_0002);
        }

        if(eventRepository.existsByEventName(request.getEventName())){
            throw new ServiceException(ResponseStatus.EVENT_NAME_EXIST_ERROR_0003);
        }

        Event event = new Event();
        event.setArtistId(request.getArtistId());
        event.setEventStatus(EventStatus.NOT_STARTED);
        event.setEventName(request.getEventName());
        event.setStartTime(DateUtils.dateStrToLocalDateTime(request.getStartTime()));
        event.setEndTime(DateUtils.dateStrToLocalDateTime(request.getEndTime()));
        event.setTotalTickets(request.getTotalTicket());
        event = eventRepository.save(event);
        insertEventTicket(event, request.getTotalTicket());
        val res = CreateEventResponse.builder()
                .eventId(event.getEventId())
                .eventStatus(event.getEventStatus().name())
                .eventName(event.getEventName())
                .startTime(event.getStartTime().toString())
                .totalTickets(event.getTotalTickets()).build();
        return packaging(res);
    }

    private void insertEventTicket(Event event, Integer totalTicket) {
        List<Ticket> eventTickets = new ArrayList<>();
        for(int i = 1; i <= totalTicket; i++){
            val ticket = new Ticket();
            ticket.setEventId(event.getEventId());
            ticket.setStatus(TicketStatus.AVAILABLE);

            String ticketNoPrefix = event.getEventId() + "-";
            int numberOfDigit = String.valueOf(totalTicket).length();
            String seqStr = String.valueOf(i);
            String ticketNo = ticketNoPrefix + StringUtils.leftPad(seqStr, numberOfDigit, "0");
            ticket.setTicketNo(ticketNo);
            eventTickets.add(ticket);
        }
        ticketRepository.saveAll(eventTickets);
    }
}
