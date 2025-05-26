package com.example.DepartmentService.controllers.department;

import com.example.DepartmentService.controllers.department.dto.CreateDepartmentRequest;
import com.example.DepartmentService.controllers.department.dto.DepartmentResponse;
import com.example.DepartmentService.controllers.department.dto.DepartmentsResponse;
import com.example.DepartmentService.controllers.department.dto.UpdateDepartmentRequest;
import com.example.DepartmentService.controllers.department.mapper.CreateRequestToDepartmentMapper;
import com.example.DepartmentService.controllers.department.mapper.DepartmentsToResponseMapper;
import com.example.DepartmentService.controllers.department.mapper.DepartmentToResponseMapper;
import com.example.DepartmentService.service.department.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
@RestController
public class DepartmentControllerImplementation implements DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentToResponseMapper departmentToResponseMapper;
    private final DepartmentsToResponseMapper departmentsToResponseMapper;
    private final CreateRequestToDepartmentMapper createRequestToDepartmentMapper;

    public DepartmentControllerImplementation(DepartmentService departmentService,
                                              DepartmentToResponseMapper departmentToResponseMapper,
                                              DepartmentsToResponseMapper departmentsToResponseMapper,
                                              CreateRequestToDepartmentMapper createRequestToDepartmentMapper) {

        this.departmentService = departmentService;
        this.departmentToResponseMapper = departmentToResponseMapper;
        this.departmentsToResponseMapper = departmentsToResponseMapper;
        this.createRequestToDepartmentMapper = createRequestToDepartmentMapper;
    }

    @Override
    public DepartmentsResponse getDepartments() {
        return departmentsToResponseMapper.apply(departmentService.findAll());
    }

    @Override
    public DepartmentResponse getDepartment(UUID uuid) {
        return departmentService.findById(uuid)
                .map(departmentToResponseMapper)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Department doesn't exist!"));
    }

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest request) {
        if(request.getName() == null || request.getName().isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department's name cannot be empty!");
        }

        if(request.getCity() == null || request.getCity().isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department's city cannot be empty!");
        }

        if(request.getNumberOfCases() <= 0)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of cases can't be below 0!");
        }

        var department = createRequestToDepartmentMapper.apply(request);
        departmentService.create(department);

        return departmentToResponseMapper.apply(department);
    }

    @Override
    public DepartmentResponse updateDepartment(UUID uuid, UpdateDepartmentRequest request) {
        var department = departmentService.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department doesn't exist!"));

        if(request.getName() == null || request.getName().isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department's name cannot be empty!");
        }

        if(request.getCity() == null || request.getCity().isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department's city cannot be empty!");
        }

        if(request.getNumberOfCases() <= 0)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number of cases can't be below 0!");
        }

        department.setName(request.getName());
        department.setCity(request.getCity());
        department.setNumberOfCases(request.getNumberOfCases());

        departmentService.update(department);

        return departmentToResponseMapper.apply(department);
    }

    @Override
    public void deleteDepartment(UUID uuid) {
        if(departmentService.findById(uuid).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department doesn't exist!");
        }

        departmentService.delete(uuid);
    }
}
