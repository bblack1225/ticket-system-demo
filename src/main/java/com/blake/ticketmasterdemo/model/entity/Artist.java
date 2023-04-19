package com.blake.ticketmasterdemo.model.entity;

import com.blake.ticketmasterdemo.enums.ArtistType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "artist")
public class Artist implements Serializable {

    @Id
    @Column(name = "artist_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "artist_description", nullable = false)
    private String artistDescription;

    @Column(name = "artist_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ArtistType artistType;

    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Artist artist = (Artist) o;
        return getArtistId() != null && Objects.equals(getArtistId(), artist.getArtistId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
