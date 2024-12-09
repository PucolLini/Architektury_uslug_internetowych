package com.example.service;

import com.example.model.PoliceDepartment;
import com.example.repository.PoliceDepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PoliceDepartmentServiceTest {

    @InjectMocks
    private PoliceDepartmentService departmentService;

    @Mock
    private PoliceDepartmentRepository departmentRepository;

    @Test
    public void testGetAllCategories() {
        List<PoliceDepartment> categories = List.of(
                new PoliceDepartment(UUID.randomUUID(), "Dep1", "London", 100),
                new PoliceDepartment(UUID.randomUUID(), "Dep2", "Manchester", 50)
        );

        Mockito.when(departmentRepository.findAll()).thenReturn(categories);

        List<PoliceDepartment> result = departmentService.getAllDepartments();
        assertEquals(2, result.size());
        assertEquals("Dep1", result.get(0).getName());
        assertEquals("London", result.get(0).getCity());
    }
}
