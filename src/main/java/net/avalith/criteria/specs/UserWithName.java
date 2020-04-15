package net.avalith.criteria.specs;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.avalith.criteria.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@AllArgsConstructor
public class UserWithName implements Specification<User> {

    private final Optional<String> name;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        return name.map((name) -> cb.equal(root.get("name"), name))
                .orElse(cb.isTrue(cb.literal(true)));
    }
}
