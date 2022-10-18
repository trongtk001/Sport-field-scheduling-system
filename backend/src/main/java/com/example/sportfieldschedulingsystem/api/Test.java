package com.example.sportfieldschedulingsystem.api;


import com.example.sportfieldschedulingsystem.dto.ScheduleDTO;
import com.example.sportfieldschedulingsystem.mapper.ScheduleMapper;
import com.example.sportfieldschedulingsystem.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600))
@RestController
@RequestMapping("api/test")
@PreAuthorize("permitAll()")
public class Test {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleService scheduleService;
    private final ObjectMapper objectMapper;

    public Test(ScheduleMapper scheduleMapper, ScheduleService scheduleService) {
        this.scheduleMapper = scheduleMapper;
        this.scheduleService = scheduleService;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping()
    public ObjectNode test() throws InterruptedException {
        ObjectNode objectNode = objectMapper.createObjectNode();
        asyncMethodWithReturnType();
        objectNode.put("username", "admin");
        objectNode.put("password", "password");
        return objectNode;
    }

    @PostMapping
    public ObjectNode test(@RequestBody ScheduleDTO scheduleDTO) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("username", "admin");
        objectNode.put("password", "password");
        return objectNode;
    }

    public void asyncMethodWithReturnType() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Execute method asynchronously - "
                        + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}
