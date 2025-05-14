package com.example.apirest.repositories;

import com.example.apirest.entities.Discount;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends BaseRepository<Discount, Integer> {
}