package com.example.OfficerService.controllers.officer.mapper;

import com.example.OfficerService.controllers.officer.dto.OfficersResponse;
import com.example.OfficerService.entities.Officer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class OfficersToResponseMapper implements Function<List<Officer>, OfficersResponse> {

    public OfficersResponse apply(List<Officer> officers) {
        return OfficersResponse.builder()
                .officers(officers.stream()
                        .map(character ->
                            OfficersResponse.Officer.builder()
                                    .id(character.getId())
                                    .name(character.getName())
                                    .build())
                        .toList()
                ).build();
    }
}
