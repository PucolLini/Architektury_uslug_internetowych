package com.example.OfficerService.controllers.officer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.OfficerService.controllers.officer.dto.*;



import java.util.UUID;
public interface OfficerController {
    @GetMapping("/officers")
    @ResponseStatus(HttpStatus.OK)
    OfficersResponse getOfficers();

    @GetMapping("/officers/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    OfficerResponse getOfficer(@PathVariable UUID uuid);

    @GetMapping("/departments/{uuid}/officers")
    @ResponseStatus(HttpStatus.OK)
    OfficersResponse getOfficersByDepartment(@PathVariable UUID uuid);

    @PostMapping("/departments/{uuid}/officers") // POST here, because we only need to create a new object
    @ResponseStatus(HttpStatus.CREATED)
    OfficerResponse createOfficer(@PathVariable UUID uuid, @RequestBody CreateOfficerRequest request);

    @PatchMapping("/officers/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    OfficerResponse updateOfficer(@PathVariable UUID uuid, @RequestBody UpdateOfficerRequest request);

    @DeleteMapping("/officers/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOfficer(@PathVariable UUID uuid);
}