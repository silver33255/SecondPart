package com.panaskin.hibernapp.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "rowNumber")
    private String rowNumber;

    @Column(name = "seatNumber")
    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="filmSession_id")
    @ToString.Exclude
    private FilmSession filmSession;
}
