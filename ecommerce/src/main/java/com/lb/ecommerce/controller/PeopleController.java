package com.lb.ecommerce.controller;

import com.lb.ecommerce.data_models.PeopleResponse;
import com.lb.ecommerce.entity.Person;
import com.lb.ecommerce.repository.PeopleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/clients")
public class PeopleController {

    @Autowired
    private PeopleRepository repository;

    private ModelMapper modelMapper;

    public PeopleController() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }


    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person pS = repository.save(person);
        return ResponseEntity.ok(pS);
    }

    @GetMapping
    public ResponseEntity<List<PeopleResponse>> get(@RequestParam(required = false) String type) {
        List<Person> people = repository.findAll();
        if (type == null ||type.isEmpty() ){
            return ResponseEntity.ok(modelMapper.map(people, new TypeToken<List<PeopleResponse>>(){}.getType()));
        }
//        else{
//            return ResponseEntity.ok(repository.getByType(type.charAt(0)));
//        }
        return ResponseEntity.ok(modelMapper.map(people, new TypeToken<List<PeopleResponse>>(){}.getType()));
    }

    @DeleteMapping("/{clientsId}")
    public ResponseEntity delete(@PathVariable("clientsId") long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
