package com.dto;

import lombok.Data;

import java.util.Map;

@Data
public class DeviationResponse {
    private Map<String, Integer> actualPercentages;
    private Map<String, Integer> standardPercentages;
    private Map<String, Integer> deviations;
}