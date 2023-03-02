package com.saferent.domain;

import com.saferent.domain.enums.*;
import lombok.*;

import javax.persistence.*;
import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="t_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Car car;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private LocalDateTime pickUpTime;

    @Column(nullable = false)
    private LocalDateTime dropOfTime;

    @Column(length = 150, nullable = false)
    private String pickUpLocation;

    @Column(length = 150, nullable = false)
    private String dropOfLocation;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private Double totalPrice;

}
