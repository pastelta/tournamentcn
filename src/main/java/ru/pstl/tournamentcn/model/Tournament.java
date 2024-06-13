package ru.pstl.tournamentcn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name="tournaments")
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tournament_generator")
    private Long id;
    @Column(name="tournament_name")
    private String tournamentName;
    @Column(name="date_start")
    private Date dateStart;
    @Column(name = "time_start")
    private Time timeStart;
    @Column(name = "status_id")
    private Integer statusId;
    @Column(name="buying_in")
    private BigDecimal buyingIn;
}
