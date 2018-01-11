package group.msg.training.school.util;

import java.util.Set;

public interface RandomNumberGenerator {

    Set<Integer> generateIntegers(int count, int min, int max);

}
