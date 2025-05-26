package com.example.DepartmentService.repositories.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class EventRepositoryImplementation implements EventRepository {

    @Qualifier("officer-service")
    private final RestTemplate officerServiceRestTemplate;

    @Autowired
    public EventRepositoryImplementation(RestTemplate officerServiceRestTemplate) {
        this.officerServiceRestTemplate = officerServiceRestTemplate;
    }

    @Override
    public void sendCreateDepartmentEvent(UUID departmentId) {
        officerServiceRestTemplate.put("/departments/" + departmentId, null);
    }

    @Override
    public void sendDeleteDepartmentEvent(UUID departmentId) {
        officerServiceRestTemplate.delete("/departments/" + departmentId);
    }
}
