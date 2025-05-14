package com.example.apirest.repositories;

import com.example.apirest.entities.Discount;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "discounts")
public interface DiscountRepository extends BaseRepository<Discount, Integer> {
}