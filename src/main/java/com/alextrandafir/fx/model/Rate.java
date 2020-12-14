package com.alextrandafir.fx.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode
public class Rate implements Serializable {
    String currency;
    Double rate;
}
