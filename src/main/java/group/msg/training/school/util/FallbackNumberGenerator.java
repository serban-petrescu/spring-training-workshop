package group.msg.training.school.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@Component
public class FallbackNumberGenerator implements RandomNumberGenerator {

    @Override
    public Set<Integer> generateIntegers(int count, int min, int max) {
        log.info("Java Random called with count = {}", count);
        if (count > 0 && min >= 0 && max >= min && max - min + 1 >= count) {
            Set<Integer> result = new HashSet<>();
            Random random = new Random();
            while (result.size() < count) {
                result.add(min + random.nextInt(max - min));
            }
            return result;
        } else {
           throw new IllegalArgumentException();
        }
    }
}
