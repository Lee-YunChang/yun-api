package com.yunapi.domain.specs;

import com.yunapi.entity.User;
import com.yunapi.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@SuppressWarnings("serial")
public class UserSpecs {

    public static Specification<User> UserNameLike(final String name) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return StringUtils.isBlank(name) ? null :
                        criteriaBuilder.like(root.get("name"), '%' + name +'%');
            }
        };
    }
    public static Specification<User>emailLike(final String email) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return StringUtils.isBlank(email) ? null :
                        criteriaBuilder.like(root.get("email"), '%' + email +'%');
            }
        };
    }
}
