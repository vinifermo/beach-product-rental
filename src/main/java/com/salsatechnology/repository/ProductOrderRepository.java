package com.salsatechnology.repository;

import com.salsatechnology.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findProductOrderByUserNameIgnoreCase(String username);
}
