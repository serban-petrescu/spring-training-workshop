package group.msg.training.school.util;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Set;

@Primary
@Component
@RequiredArgsConstructor
public class CompositeRandomGenerator implements RandomNumberGenerator {
    private final RandomOrgClient randomOrgBasedGenerator;
    private final FallbackNumberGenerator fallbackNumberGenerator;

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public Set<Integer> generateIntegers(int count, int min, int max) {
        return randomOrgBasedGenerator.generateIntegers(count, min, max);
    }

    public Set<Integer> fallback(int count, int min, int max) {
        return fallbackNumberGenerator.generateIntegers(count, min, max);
    }

}
