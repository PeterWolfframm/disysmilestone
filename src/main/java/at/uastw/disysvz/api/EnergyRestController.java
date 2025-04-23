package at.uastw.disysvz.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.time.Instant;
import java.util.Map;

@RestController
public class EnergyRestController {
    private final Random random = new Random();

    @GetMapping("/energy/current")
    public double getCurrentEnergy() {
        //für testzwecke zufällige zahl zwischen 0 und 100
        return random.nextDouble() * 100;
    }

    @GetMapping("/energy/historic")
    public List<Map<String, Object>> getHistoricEnergy() {
        return Arrays.asList(
            Map.of("created", Instant.now().minusSeconds(3600 * 5).toString(), "leistung", 45.7),
            Map.of("created", Instant.now().minusSeconds(3600 * 4).toString(), "leistung", 67.2),
            Map.of("created", Instant.now().minusSeconds(3600 * 3).toString(), "leistung", 82.1),
            Map.of("created", Instant.now().minusSeconds(3600 * 2).toString(), "leistung", 34.9),
            Map.of("created", Instant.now().minusSeconds(3600).toString(), "leistung", 91.3),
            Map.of("created", Instant.now().toString(), "leistung", 73.5)
        );
    }
} 