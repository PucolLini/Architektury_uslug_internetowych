package com.example.DepartmentService.repositories.event;

import java.util.UUID;

public interface EventRepository {
    void sendCreateDepartmentEvent(UUID categoryId);

    void sendDeleteDepartmentEvent(UUID categoryId);
}
