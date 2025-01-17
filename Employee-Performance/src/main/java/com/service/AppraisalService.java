package com.service;

import com.dto.DeviationResponse;
import com.dto.SuggestionResponse;
import com.model.Employee;
import com.model.Rating;
import com.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppraisalService {
    private final RatingRepository ratingRepository;

    public AppraisalService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public DeviationResponse calculateDeviation(List<Employee> employees) {
        long totalEmployees = employees.size();
        Map<String, Integer> actualCounts = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRating, Collectors.reducing(0, e -> 1, Integer::sum)));

        Map<String, Integer> actualPercentages = actualCounts.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (int) (e.getValue() * 100 / totalEmployees)));

        List<Rating> ratings = ratingRepository.findAll();
        Map<String, Integer> standardPercentages = ratings.stream()
                .collect(Collectors.toMap(Rating::getCategory, Rating::getStandardPercentage));

        Map<String, Integer> deviations = new HashMap<>();
        for (String category : standardPercentages.keySet()) {
            int standard = standardPercentages.getOrDefault(category, 0);
            int actual = actualPercentages.getOrDefault(category, 0);
            deviations.put(category, actual - standard);
        }

        DeviationResponse response = new DeviationResponse();
        response.setActualPercentages(actualPercentages);
        response.setStandardPercentages(standardPercentages);
        response.setDeviations(deviations);

        return response;
    }

    public List<SuggestionResponse> suggestRevisions(List<Employee> employees, DeviationResponse deviations) {
        List<SuggestionResponse> suggestions = new ArrayList<>();
        deviations.getDeviations().forEach((rating, deviation) -> {
            if (deviation > 0) {
                employees.stream()
                        .filter(e -> e.getRating().equals(rating))
                        .limit(deviation)
                        .forEach(employee -> suggestions.add(
                                new SuggestionResponse(employee.getId(), rating, getNextLowerRating(rating))));
            }
        });
        return suggestions;
    }

    private String getNextLowerRating(String rating) {
        List<String> order = List.of("A", "B", "C", "D", "E");
        int index = order.indexOf(rating);
        return index < order.size() - 1 ? order.get(index + 1) : rating;
    }

    public Rating addOrUpdateRating(Rating rating) {
        return ratingRepository.save(rating);
    }
}