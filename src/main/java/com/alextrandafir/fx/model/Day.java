package com.alextrandafir.fx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class Day {
    private List<Rate> rates;

    public Day(){

    }
}
