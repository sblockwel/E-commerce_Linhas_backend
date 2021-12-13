package com.lb.ecommerce.controller;

import com.lb.ecommerce.data_models.PeopleResponse;
import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.models.UserRole;
import com.lb.ecommerce.repository.PeopleRepository;
import com.lb.ecommerce.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class PeopleController {

    @Autowired
    private PeopleRepository repository;

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person pS = repository.save(person);
        return ResponseEntity.ok(pS);
    }

    @GetMapping
    public ResponseEntity<List<PeopleResponse>> get(@RequestParam(required = false) String type) {
        List<PeopleResponse> responseList = new ArrayList<>();
        if (type == null || type.isEmpty() ){
            List<Person> people = repository.findAll();
            responseList = MapperUtils.mapList(people, PeopleResponse.class);
        }
        else{
            List<Person> people = repository.getAllByRole(type.equalsIgnoreCase("A") ? UserRole.ADMIN : UserRole.CLIENT);
            responseList = MapperUtils.mapList(people, PeopleResponse.class);
        }
        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{clientsId}")
    public ResponseEntity delete(@PathVariable("clientsId") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
