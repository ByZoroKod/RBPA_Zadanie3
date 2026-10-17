package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlaceBidRequest {
    private Long bidderId;
    private BigDecimal amount;
}
