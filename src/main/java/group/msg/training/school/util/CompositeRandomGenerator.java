package group.msg.training.school.util;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Set;

@Primary
@Component
public class CompositeRandomGenerator implements RandomNumberGenerator {
    private final RandomOrgClient randomOrgBasedGenerator;
    private final FallbackNumberGenerator fallbackNumberGenerator;

    public CompositeRandomGenerator(RandomOrgClient randomOrgBasedGenerator,
                                    FallbackNumberGenerator fallbackNumberGenerator) {
        this.randomOrgBasedGenerator = randomOrgBasedGenerator;
        this.fallbackNumberGenerator = fallbackNumberGenerator;
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public Set<Integer> generateIntegers(int count, int min, int max) {
        return randomOrgBasedGenerator.generateIntegers(count, min, max);
    }

    public Set<Integer> fallback(int count, int min, int max) {
        return fallbackNumberGenerator.generateIntegers(count, min, max);
    }

}
