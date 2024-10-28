package com.fengshui.common.services.impl;

import com.fengshui.common.repository.postgresql.dto.FengShuiConsulationDTO;
import com.fengshui.common.services.FengshuiYearGenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FengshuiYearGenderServiceImpl implements FengshuiYearGenderService {
    private final String[] heavenlyStems = {"Jia", "Yi", "Bing", "Ding", "Wu", "Ji", "Geng", "Xin", "Ren", "Gui"};
    private final String[] earthlyBranches = {"Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig"};
    private final String[] fiveElements = {"Wood", "Wood", "Fire", "Fire", "Earth", "Earth", "Metal", "Metal", "Water", "Water"};


    @Override
    public ResponseEntity<FengShuiConsulationDTO> calculateFengShui(int year) {
        int stemIndex = (year - 4) % 10;
        int branchIndex = (year - 4) % 12;
        int elementIndex = (year - 4) % 10;

        String heavenlyStem = heavenlyStems[stemIndex];
        String earthlyBranch = earthlyBranches[branchIndex];
        String element = fiveElements[elementIndex];
        String fishRecommendation = getFishRecommendation(element);
        String tankDirection = getTankDirection(element);

        FengShuiConsulationDTO response = new FengShuiConsulationDTO(
                heavenlyStem,
                earthlyBranch,
                element,
                fishRecommendation,
                tankDirection
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public String getFishRecommendation(String element) {
        switch (element) {
            case "Fire":
                return "For Fire element: keep 2 or 7 koi fish, preferably red, to bring good fortune.";
            case "Water":
                return "For Water element: keep 1 or 6 koi fish, preferably black or white, to bring good luck.";
            case "Metal":
                return "For Metal element: keep 4 or 9 koi fish, preferably butterfly koi or yellow, white fish.";
            case "Wood":
                return "For Wood element: keep 3 or 8 koi fish with red, white, or black patterns (like Sanke, Karasu, Shiro Utsuri).";
            case "Earth":
                return "For Earth element: keep 5 or 10 koi fish, preferably yellow or red.";
            default:
                return "";
        }
    }

    @Override
    public String getTankDirection(String element) {
        switch (element) {
            case "Wood":
                return "East direction, as Wood prefers sunlight.";
            case "Fire":
                return "South direction, as Fire symbolizes warmth.";
            case "Metal":
                return "West direction, as Metal favors cooler surroundings.";
            case "Water":
                return "North direction, as Water is associated with coolness.";
            case "Earth":
                return "Center or West direction, as Earth is related to stability.";
            default:
                return "";
        }
    }
}
