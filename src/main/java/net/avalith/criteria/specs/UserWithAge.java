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
public class UserWithAge implements Specification<User> {

    private final Optional<Long> age;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        return age.map((age) -> cb.equal(root.get("age"), age))
                .orElse(cb.isTrue(cb.literal(true)));
    }
}
