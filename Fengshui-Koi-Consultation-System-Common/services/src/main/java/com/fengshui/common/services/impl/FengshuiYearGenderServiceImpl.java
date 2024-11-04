package com.fengshui.common.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fengshui.common.repository.postgresql.dto.AICalculateDTO;
import com.fengshui.common.repository.postgresql.dto.ChatRequest;
import com.fengshui.common.repository.postgresql.dto.ChatResponse;
import com.fengshui.common.repository.postgresql.dto.FengShuiConsulationDTO;
import com.fengshui.common.services.FengshuiYearGenderService;
import com.fengshui.common.shared.Request.HeavenlyEarthlyModel.CalcutateByGPTRequestModel;
import com.fengshui.common.shared.Response.HeavenlyEarthly.CalcutateByGPTResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FengshuiYearGenderServiceImpl implements FengshuiYearGenderService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${chatgpt.key}")
    private String chatGptKey;
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
    public ResponseEntity<CalcutateByGPTResponseModel> calculateFengShuiByGPT(CalcutateByGPTRequestModel data) {
        // Static values for Feng Shui
        String fengshuiProduce = "Wood produces Fire: Wood is needed to feed fire.\n" +
                "Fire produces Earth: Fire, after burning wood, creates earth in the form of ash.\n" +
                "Earth produces Metal: Earth is where metal can be found. Minerals are mined from the soil.\n" +
                "Metal produces Water: This is less intuitive. The common explanation is when metal is being heated and cooled, water is captured in the air in the form of condensation.\n" +
                "Water produces Wood: Water is needed for plants to grow.";
        String fengshuiWeaken = "Fire weakens Wood: When fire is created, there will be less wood (or no more wood if the fire keeps burning).\n" +
                "Wood weakens Water: Wood absorbs water in order to grow.\n" +
                "Water weakens Metal: Water is why rusts grow on metal and steel.\n" +
                "Metal weakens Earth: To mine minerals, we’ll need to excavate earth.\n" +
                "Earth weakens Fire: Earth cannot burn, and fire can be extinguished if enough earth is placed on top of it.";
        String controllingCycle = "Fire controls Metal: Fire melts metal.\n" +
                "Metal controls Wood: Solid metal can cut down wood.\n" +
                "Wood controls Earth: Wood depletes nutrients from earth (the reason why we need fertilizers).\n" +
                "Earth controls Water: Earth controls how water flows (like land formations and water dams).\n" +
                "Water controls Fire: Water extinguishes fire.";

        // Prompt for ChatGPT
        String prompt = "Analyze feng shui based on the compatibility of personal destiny with the characteristics of the pond and Koi fish. Compare on fengshui element and direction (The Feng Shui element accounts for 70% of the score, while other factors make up the remaining 30%) based on: " +
                data.getPersonalFengshuiData() + " and compare with target: " + data.getTargetCompareData() +
                " Return JSON in format and only return object: {\"description\": \"<50 words>\", \"element\": \"<element explanation, max 20 words>\", \"score\": <0-100>}, using high scores for fengshuiProduce matches, low for fengshuiWeaken mismatches. " +
                fengshuiProduce + fengshuiWeaken + controllingCycle;

        // API Request Setup
        String model = "gpt-4";
        ChatRequest request = new ChatRequest(model, prompt, 1, 0);
        String url = "https://api.openai.com/v1/chat/completions";

        ChatResponse responseGPT = restTemplate.postForObject(url, request, ChatResponse.class);

        // Validate response
        if (responseGPT == null || responseGPT.getChoices() == null || responseGPT.getChoices().isEmpty()) {
            return ResponseEntity.ok(new CalcutateByGPTResponseModel(true, null, "No response from ChatGPT"));
        }
//        String jsonRegex = "\\{.*?\"description\"\\s*:\\s*\".*?\",\\s*\"element\"\\s*:\\s*\".*?\",\\s*\"score\"\\s*:\\s*\\d+\\s*\\}";
        String jsonRegex = "\\{\\s*\"description\"\\s*:\\s*\".*?\"\\s*,\\s*\"element\"\\s*:\\s*\".*?\"\\s*,\\s*\"score\"\\s*:\\s*\\d+\\s*\\}";
        // Parse response content to AICalculateDTO
        String responseContent = responseGPT.getChoices().get(0).getMessage().getContent();
        ObjectMapper mapper = new ObjectMapper();
        AICalculateDTO resultDto;
        try {
            // Use regex to find JSON object in responseContent
            Pattern pattern = Pattern.compile(jsonRegex);
            Matcher matcher = pattern.matcher(responseContent);

            if (matcher.find()) {
                String jsonObject = matcher.group(); // Extract the JSON object

                resultDto = mapper.readValue(jsonObject, AICalculateDTO.class);
            } else {
                return ResponseEntity.ok(new CalcutateByGPTResponseModel(true, null, "No JSON object found in response"));
            }
        } catch (JsonProcessingException e) {
            return ResponseEntity.ok(new CalcutateByGPTResponseModel(true, null, "Error parsing response: " + e.getMessage()));
        }

        // Return parsed response
        CalcutateByGPTResponseModel response = new CalcutateByGPTResponseModel(false, resultDto, null);
        return ResponseEntity.ok(response);
    }



//    public static AICalculateDTO extractMessageFromJSONResponse(String response) {
//
//        JSONObject jsonResponse = new JSONObject(response);
//        String content = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
//
//        // Example parsing - adjust based on actual format
//        String[] parts = content.split(","); // Split based on expected format
//        fengShuiResponse.setDescription(parts[0].split(":")[1].trim().replaceAll("\"", "")); // Extract description
//        fengShuiResponse.setScore(Integer.parseInt(parts[1].split(":")[1].trim())); // Extract score
//
//        return fengShuiResponse;
//    }

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
