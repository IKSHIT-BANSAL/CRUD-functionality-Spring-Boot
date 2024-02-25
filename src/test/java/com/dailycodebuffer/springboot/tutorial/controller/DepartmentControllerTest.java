package com.dailycodebuffer.springboot.tutorial.controller;

import com.dailycodebuffer.springboot.tutorial.entity.Department;
import com.dailycodebuffer.springboot.tutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department=Department.builder()
                .departmentAddress("Ahmdabad")
                .departmentName("IT")
                .departmentCode("IT-06")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment=Department.builder()
                .departmentAddress("Ahmdabad")
                .departmentName("IT")
                .departmentCode("IT-06")
                .build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"IT\",\n" +
                        "    \"departmentAddress\":\"Ahmdabad\",\n" +
                        "    \"departmentCode\":\"IT-06\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchDepartmentbyId() throws DepartmentNotFoundException {
        Mockito.when(departmentService.fetchDepartmentbyId(1L))
                .thenReturn(department);

//        mockMvc.perform(get(urlTemplate: "/departments/1")
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status.isOK())
//                .andExpect(jsonPath(expression:"$.departmentName")
//                .value(department.getDepartmentName()));
    }
}