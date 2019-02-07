package group.msg.training.school.controllers.rest;

import group.msg.training.school.util.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/rest/random")
@RequiredArgsConstructor
public class RandomRestController {
    private final RandomNumberGenerator generator;

	@GetMapping("/integers")
    public Set<Integer> integers(@RequestParam int count, @RequestParam int min, @RequestParam int max) {
        return generator.generateIntegers(count, min, max);
    }
}
