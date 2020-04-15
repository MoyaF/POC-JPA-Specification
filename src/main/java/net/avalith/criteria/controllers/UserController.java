package net.avalith.criteria.controllers;

import net.avalith.criteria.models.User;
import net.avalith.criteria.repositories.UserRepository;
import net.avalith.criteria.specs.UserWithAge;
import net.avalith.criteria.specs.UserWithName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public Page<User> find(
            @RequestParam(value = "name", required = false) Optional<String> name,
            @RequestParam(value = "age", required = false) Optional<Long> age,
            Pageable pageable){

        Specification<User> userSpecification = Specification.where(new UserWithAge(age)
                .and(new UserWithName(name)));

        return userRepository.findAll(userSpecification,pageable);
    }

    @PostMapping("")
    public User create(
            @RequestBody User user){

        return userRepository.save(user);
    }

}
