package com.example.sportfieldschedulingsystem.api;

import com.example.sportfieldschedulingsystem.dto.ScheduleDTO;
import com.example.sportfieldschedulingsystem.dto.ScheduleDetailDTO;
import com.example.sportfieldschedulingsystem.service.ScheduleDetailService;
import com.example.sportfieldschedulingsystem.service.ScheduleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600), allowCredentials = "true")
@RestController
@RequestMapping("/api/schedule")
@PreAuthorize("isAuthenticated()")
public class ScheduleAPI{

    private final ScheduleService scheduleService;

    private final ScheduleDetailService scheduleDetailService;

    public ScheduleAPI(ScheduleService scheduleService, ScheduleDetailService scheduleDetailService) {
        this.scheduleService = scheduleService;
        this.scheduleDetailService = scheduleDetailService;
    }

    @GetMapping("/{id}")
    public ScheduleDTO getSchedule(@PathVariable("id") Long id) {
        return scheduleService.search(id);
    }

    @GetMapping("")
    public List<ScheduleDTO> getScheduleByUserID(@RequestParam("userid") long userID) {
        return scheduleService.findAllByUserID(userID);
    }

    @PostMapping("")
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO dto) {
        return scheduleService.save(dto);
    }

    @PostMapping("/detail")
    public ScheduleDetailDTO createScheduleDetail(@RequestBody ScheduleDetailDTO dto) {
        return scheduleDetailService.save(dto);
    }
}
