package com.example.springboot.util;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {

    private final T filter;

    public GenericSpecification(T filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Class<?> filterClass = filter.getClass();
        Field[] fields = filterClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(filter);
                if (value != null) {
                    if (value instanceof String) {
                        if (!StringUtils.isEmpty(value)) {
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(field.getName())),
                                    "%" + ((String) value).toLowerCase() + "%"));
                        }
                    } else {
                        Path<?> path = root.get(field.getName());
                        if (value instanceof BigDecimal || value instanceof Number || value instanceof Boolean) {
                            predicates.add(criteriaBuilder.equal(path, value));
                        } else if (value instanceof Date) {
                            predicates.add(criteriaBuilder.equal(path, (Date) value));
                        } else if (value instanceof Number) {
                            predicates.add(criteriaBuilder.equal(path, (Number) value));
                        } else if (value instanceof LocalDate) {
                            predicates.add(criteriaBuilder.equal(path, (LocalDate) value));
                        } else {
                            predicates.add(criteriaBuilder.equal(path, value));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                // Handle exception, if needed
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

