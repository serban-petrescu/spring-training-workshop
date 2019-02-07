package group.msg.training.school.util;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import group.msg.training.school.config.RandomOrgApiConfig;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RandomOrgClient implements RandomNumberGenerator {
    private final RestTemplate restTemplate;
    private final RandomOrgApiConfig config;

    @Override
    public Set<Integer> generateIntegers(int count, int min, int max) {
        log.info("Random ORG called with count = {}", count);
        return extractNumbersFromJson(restTemplate.postForObject(config.getUrl(),
                new ApiInput(new ApiParams(config.getApiKey(), count, min, max)), JsonNode.class));
    }

    private Set<Integer> extractNumbersFromJson(JsonNode node) {
        ArrayNode data = (ArrayNode) node.at(JsonPointer.compile("/result/random/data"));
        Set<Integer> result = new HashSet<>();
        for (Iterator<JsonNode> iterator = data.elements(); iterator.hasNext(); ) {
            result.add(iterator.next().asInt());
        }
        return result;
    }

    @Data
    private static class ApiInput {
        private final String jsonrpc = "2.0";
        private final String method = "generateIntegers";
        private final String id = UUID.randomUUID().toString();
        private final ApiParams params;

		ApiInput(ApiParams params) {
            this.params = params;
        }
    }

    @Data
    private static class ApiParams {
        private final String apiKey;
        private final int n;
        private final int min;
        private final int max;
        private final boolean replacement = false;

		ApiParams(String apiKey, int n, int min, int max) {
            this.apiKey = apiKey;
            this.n = n;
            this.max = max;
            this.min = min;
        }
    }

}
