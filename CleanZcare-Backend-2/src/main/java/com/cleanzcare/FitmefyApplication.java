package com.cleanzcare;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FitmefyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitmefyApplication.class, args);
    }

    /**
     * Configure ModelMapper as a Spring bean so it can be injected anywhere.
     * - Uses STRICT matching to ensure only exact field names are mapped.
     * - Ignores null properties during mapping.
     */
    @Bean
    public ModelMapper modelMapper() {
        System.out.println("Creating ModelMapper bean...");
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }
}
