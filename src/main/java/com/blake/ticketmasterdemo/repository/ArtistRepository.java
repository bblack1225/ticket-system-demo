package com.blake.ticketmasterdemo.repository;

import com.blake.ticketmasterdemo.model.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArtistRepository extends JpaRepository<Artist, Integer>, JpaSpecificationExecutor<Artist> {

    boolean existsByArtistName(String name);
}
