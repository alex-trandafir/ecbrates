package com.alextrandafir.fx.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Day implements Serializable {

    private LocalDate date;
    private List<Rate> rates;

}
