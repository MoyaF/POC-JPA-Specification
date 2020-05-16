package net.avalith.criteria.controllers;

import lombok.RequiredArgsConstructor;
import net.avalith.criteria.entities.PageResponse;
import net.avalith.criteria.models.User;
import net.avalith.criteria.services.UserService;
import net.avalith.criteria.specs.UserSpec;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public PageResponse<User> findAll(
            UserSpec campaignSpec,
            Pageable pageable) {

        return userService.findAll(pageable, campaignSpec);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id")Long id){

        return userService.findById(id);
    }

    @PostMapping("")
    public User save(@Valid @RequestBody User user) {

        return userService.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id")Long id,
                       @RequestBody @Valid User newUser){

        return userService.update(newUser,id);
    }

    @PatchMapping("/{id}")
    public User partialUpdate(@PathVariable("id")Long id,
                              @RequestBody Map<String,Object> difference) {

        return userService.partialUpdate(difference,id);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id")Long id) {

        return userService.delete(id);
    }
}
