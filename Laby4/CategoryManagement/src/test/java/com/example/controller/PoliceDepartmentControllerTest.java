package com.example.controller;

import com.example.model.PoliceDepartment;
import com.example.service.PoliceDepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PoliceDepartmentController.class)
public class PoliceDepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PoliceDepartmentService policeDepartmentService;

    @Test
    public void testGetAllDepartments() throws Exception {
        // Mockowanie danych
        PoliceDepartment department1 = new PoliceDepartment();
        department1.setName("Central City PD");
        department1.setCity("New York");

        PoliceDepartment department2 = new PoliceDepartment();
        department2.setName("Metro PD");
        department2.setCity("Las Vegas");

        List<PoliceDepartment> departments = List.of(department1, department2);

        // Mockowanie metody serwisu
        Mockito.when(policeDepartmentService.getAllDepartments()).thenReturn(departments);

        // Wykonanie zapytania i sprawdzenie odpowiedzi
        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Central City PD")))
                .andExpect(jsonPath("$[0].city", is("New York")))
                .andExpect(jsonPath("$[1].name", is("Metro PD")))
                .andExpect(jsonPath("$[1].city", is("Las Vegas")));
    }
}
