package com.yunapi.domain.search;

import com.yunapi.domain.specs.UserSpecs;
import com.yunapi.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter @Setter
public class UserSearch {

    private String name;
    private String email;

    public Specification<User> toSpecification() {
        return  Specification.where(UserSpecs.UserNameLike(name))
                .and(UserSpecs.emailLike(email));
    }
}
