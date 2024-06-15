package ru.pstl.tournamentcn.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoParticipantRequest implements Serializable {
    @NotNull(message = "The value of the required parameter playerID is missing")
    private long playerId;
    private Integer cntIn;
    private Integer place;
    private BigDecimal payout;
}
