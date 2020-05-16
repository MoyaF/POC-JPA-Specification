package net.avalith.criteria.specs;

import net.avalith.criteria.models.User;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And(value = {
        @Spec(path = "firstName", params = "first_name", spec = Equal.class),
        @Spec(path = "lastName", params = "last_name", spec = Equal.class),
        @Spec(path = "age", spec = Equal.class)
})
public interface UserSpec extends Specification<User> {
}
