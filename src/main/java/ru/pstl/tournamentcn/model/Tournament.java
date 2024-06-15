package ru.pstl.tournamentcn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name="tournaments")
@AllArgsConstructor
@NoArgsConstructor
public class Tournament implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "tournament")
    List<Participant> participantList;
}
