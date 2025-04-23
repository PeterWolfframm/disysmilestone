package at.uastw.disysvz.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
public class EnergyRestController {
    private final Random random = new Random();

    @GetMapping("/energy/current")
    public double getCurrentEnergy() {
        //fuer testzwecke zufällige zahl zwischen 0 und 100
        return random.nextDouble() * 100;
    }
} 