package com.inertia.lockersapi.domain.locker;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "rent_locker")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentLocker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "locker_id", nullable = false)
    private UUID locker_id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "rent_start_date", nullable = false)
    private Date rent_start_date;

    @Column(name = "rent_finish_date")
    private Date rent_finish_date;
}
