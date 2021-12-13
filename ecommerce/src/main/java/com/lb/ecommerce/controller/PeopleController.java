package com.lb.ecommerce.controller;

import com.lb.ecommerce.entity.People;
import com.lb.ecommerce.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/clients")
public class PeopleController {


    @Autowired
    private PeopleRepository repository;

    @PostMapping
    public ResponseEntity<People> save(@RequestBody People people) {
        People pS = repository.save(people);
        return ResponseEntity.ok(pS);
    }

    @GetMapping
    public ResponseEntity<List<People>> get(@RequestParam(required = false) String type) {
        if (type == null ||type.isEmpty() ){
            return ResponseEntity.ok(repository.findAll());
        }
//        else{
//            return ResponseEntity.ok(repository.getByType(type.charAt(0)));
//        }
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{clientsId}")
    public ResponseEntity delete(@PathVariable("clientsId") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
