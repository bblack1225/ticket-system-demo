package com.blake.ticketmasterdemo.service;

import com.blake.ticketmasterdemo.enums.ResponseStatus;
import com.blake.ticketmasterdemo.exception.ServiceException;
import com.blake.ticketmasterdemo.model.entity.Artist;
import com.blake.ticketmasterdemo.model.vo.CreateArtistRequest;
import com.blake.ticketmasterdemo.model.vo.CreateArtistResponse;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebRequest;
import com.blake.ticketmasterdemo.model.vo.base.BaseWebResponse;
import com.blake.ticketmasterdemo.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateArtistService extends CommonService
        implements BaseApiService<CreateArtistRequest, CreateArtistResponse> {

    private final ArtistRepository artistRepository;

    @Override
    public BaseWebResponse<CreateArtistResponse> execute(BaseWebRequest<CreateArtistRequest> webRequest) {
        val request = webRequest.getTransReq();
        if(artistRepository.existsByArtistName(request.getArtistName())){
            throw new ServiceException(ResponseStatus.ARTIST_EXIST_ERROR_1001);
        }
        Artist artist = new Artist();
        artist.setArtistDescription(request.getArtistDescription());
        artist.setArtistName(request.getArtistName());
        artist.setArtistType(request.getArtistType());
        artist = artistRepository.save(artist);

        val response = new CreateArtistResponse();
        response.setArtistId(artist.getArtistId());
        response.setArtistName(artist.getArtistName());
        response.setArtistDescription(artist.getArtistDescription());
        response.setArtistType(artist.getArtistType());

        return packaging(response);
    }
}
