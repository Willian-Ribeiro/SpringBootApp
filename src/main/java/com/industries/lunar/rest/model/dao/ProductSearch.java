package com.industries.lunar.rest.model.dao;

import com.industries.lunar.rest.model.Product;
import com.industries.lunar.rest.model.dto.ProductRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("productSearchDaoBean")
@RequiredArgsConstructor
public class ProductSearch {
    private final EntityManager em;

    public List<Product> searchByCriteria(ProductRequestDTO requestDTO)
    {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Product> root = criteriaQuery.from(Product.class);

        if(requestDTO.getName() != null)
        {
            Predicate namePredicate = criteriaBuilder.equal(root.get("name"), requestDTO.getName());
            predicates.add(namePredicate);
        }

        if(requestDTO.getDeliveryStatus() != null)
        {
            Predicate deliveryStatusPredicate =
                    criteriaBuilder.equal(root.get("deliveryStatus").as(String.class), requestDTO.getDeliveryStatus().getDeliveryStatus());
            predicates.add(deliveryStatusPredicate);
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Product> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
