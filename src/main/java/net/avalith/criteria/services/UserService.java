package net.avalith.criteria.services;

import lombok.RequiredArgsConstructor;
import net.avalith.criteria.entities.PageResponse;
import net.avalith.criteria.models.User;
import net.avalith.criteria.repositories.UserRepository;
import net.avalith.criteria.specs.UserSpec;
import net.avalith.criteria.utils.PatchHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.json.Json;
import javax.json.JsonMergePatch;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PatchHelper patchHelper;

    public User findById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found",id)));
    }

    public PageResponse<User> findAll(Pageable pageable, UserSpec userSpec) {
        Page<User> page = userRepository.findAll(userSpec, pageable);

        return PageResponse.<User>builder()
                .page(page.getPageable().getPageNumber())
                .size(page.getPageable().getPageSize())
                .totalPages(page.getTotalPages())
                .results(page.getContent())
                .build();
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public User update(User user, Long id) {
        user.setId(id);

        return userRepository.save(user);
    }

    public User delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);

        return user;
    }

    public User partialUpdate(Map<String, Object> differences, Long id) {
        JsonMergePatch mergePatch = Json.createMergePatch(Json.createObjectBuilder(differences).build());
        User user = findById(id);
        User userPatched = patchHelper.mergePatch(mergePatch, user, User.class);

        return userRepository.save(userPatched);
    }

}
