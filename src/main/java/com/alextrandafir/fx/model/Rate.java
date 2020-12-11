package com.alextrandafir.fx.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {
    String currency;
    Double rate;
}
