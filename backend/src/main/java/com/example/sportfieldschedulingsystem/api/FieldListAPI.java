package com.example.sportfieldschedulingsystem.api;

import com.example.sportfieldschedulingsystem.api.output.FieldOutput;
import com.example.sportfieldschedulingsystem.dto.FieldDTO;
import com.example.sportfieldschedulingsystem.service.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:3000/", maxAge = (3600))
@RestController
@RequestMapping("/api/field")
@PreAuthorize("permitAll()")
public class FieldListAPI {

    private final FieldService fieldService;

    public FieldListAPI(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("")
    public FieldOutput getListField(@RequestParam("page") int page, @RequestParam("size") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<FieldDTO> result = fieldService.findAllByStatus(false, pageable);
        FieldOutput output = new FieldOutput(page, size, result.getTotalPages(), result.getContent());
        return output;
    }

    @GetMapping("/{type}")
    public FieldOutput getListFieldByType(@PathVariable("type") String type, @RequestParam("page") int page, @RequestParam("size") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<FieldDTO> result = fieldService.filterByType(type, pageable);

        FieldOutput output = new FieldOutput(page, size, result.getTotalPages(), result.getContent());
        return output;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FieldDTO createField(@RequestBody FieldDTO dto) {
        return fieldService.save(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public FieldDTO updateField(@RequestBody FieldDTO dto, @PathVariable("id") long id) {
        dto.setId(id);
        return fieldService.save(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteField(@PathVariable("id") long id) {
        if (null == fieldService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id: " + id);
        }
    }

    @GetMapping("/search")
    public FieldOutput searchField(@RequestParam("q") String q, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<FieldDTO> result = fieldService.search(q.toLowerCase().trim(), pageable);

        FieldOutput output = new FieldOutput(page, size, result.getTotalPages(), result.getContent());
        return output;
    }

    @GetMapping("/search/{type}")
    public FieldOutput searchFieldByType(@PathVariable("type") String type, @RequestParam("q") String q, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<FieldDTO> result = fieldService.searchFilteredByType(q, type, pageable);
        FieldOutput output = new FieldOutput(page, size, result.getTotalPages(), result.getContent());
        return output;
    }
}
