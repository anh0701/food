package com.anh.foodsupplybe.repo;

import com.anh.foodsupplybe.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
