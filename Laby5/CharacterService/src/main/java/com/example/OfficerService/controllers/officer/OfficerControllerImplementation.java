package com.example.OfficerService.controllers.officer;

import com.example.OfficerService.controllers.officer.mapper.OfficerToResponseMapper;
import com.example.OfficerService.controllers.officer.mapper.OfficersToResponseMapper;
import com.example.OfficerService.controllers.officer.mapper.CreateRequestToOfficerMapper;
import com.example.OfficerService.services.officer.OfficerService;
import com.example.OfficerService.controllers.officer.dto.OfficerResponse;
import com.example.OfficerService.controllers.officer.dto.CreateOfficerRequest;
import com.example.OfficerService.controllers.officer.dto.UpdateOfficerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.OfficerService.controllers.officer.dto.OfficersResponse;
import com.example.OfficerService.services.department.DepartmentService;

import java.util.UUID;
import lombok.extern.java.Log;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Log
public class OfficerControllerImplementation implements OfficerController {
    private final OfficerService officerService;
    private final DepartmentService departmentService;

    private final OfficerToResponseMapper officerToResponseMapper;
    private final OfficersToResponseMapper officersToResponseMapper;
    private final CreateRequestToOfficerMapper createRequestToOfficerMapper;

    @Autowired
    public OfficerControllerImplementation(OfficerService officerService,
                                           DepartmentService departmentService,
                                           OfficerToResponseMapper officerToResponseMapper,
                                           OfficersToResponseMapper officersToResponseMapper,
                                           CreateRequestToOfficerMapper createRequestToOfficerMapper) {
        this.officerService = officerService;
        this.departmentService = departmentService;
        this.officerToResponseMapper = officerToResponseMapper;
        this.officersToResponseMapper = officersToResponseMapper;
        this.createRequestToOfficerMapper = createRequestToOfficerMapper;
    }
    @Override
    public OfficersResponse getOfficers() {
        return officersToResponseMapper.apply(officerService.findAll());
    }
    @Override
    public OfficerResponse getOfficer(UUID uuid) {
        return officerService.findById(uuid)
                .map(officerToResponseMapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Officer nie istnieje"));
    }

    @Override
    public OfficersResponse getOfficersByDepartment(UUID uuid) {
        var department = departmentService.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department nie istnieje"));

        return officersToResponseMapper.apply(officerService.findByDepartment(department));
    }

    @Override
    public OfficerResponse createOfficer(UUID uuid, CreateOfficerRequest request) {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Officer's name is required!");
        }
        if (request.getBadgeNumber() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Badge number has to be >= 0!");
        }
        if (request.getYearsOfService() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Years of service have to be >= 0!");
        }
        if (request.getRank() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rank has to be >= 0!");
        }


        var department = departmentService.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown department!"));

        var officer = createRequestToOfficerMapper.apply(request);
        officer.setDepartment(department);
        officerService.create(officer);

        return officerToResponseMapper.apply(officer);
    }

    @Override
    public OfficerResponse updateOfficer(UUID uuid, UpdateOfficerRequest request) {
        var officer = officerService.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Officer doesn't exists!"));

        if (request.getName() != null) {
            officer.setName(request.getName());
        }
        if (request.getBadgeNumber() != null) {
            if (request.getBadgeNumber() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Badge number has to be >= 0!");
            }
            officer.setBadgeNumber(request.getBadgeNumber());
        }
        if (request.getYearsOfService() != null) {
            if (request.getYearsOfService() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Years of service have to be >= 0!");
            }
            officer.setYearsOfService(request.getYearsOfService());
        }
        if (request.getRank() != null) {
            if (request.getRank() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rank has to be >= 0!");
            }
            officer.setRank(request.getRank());
        }

        officerService.update(officer);

        return officerToResponseMapper.apply(officer);
    }

    @Override
    public void deleteOfficer(UUID uuid) {
        if (officerService.findById(uuid).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Officer doesn't exists!");
        }

        officerService.delete(uuid);
    }
}
