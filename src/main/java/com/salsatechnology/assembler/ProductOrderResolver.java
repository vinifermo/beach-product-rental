package com.salsatechnology.assembler;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.factory.ProductStrategy;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class ProductOrderResolver {

    private final Long totalValue;
    private final Long userAmount;
    private final Long productValue;

    public ProductOrderResolver(ProductOrderDTO productOrderDTO, ProductStrategy product) {
        BigDecimal calculatedProductValue = calculateProductValue(product.getProductValue());
        this.productValue = calculatedProductValue.longValue();

        BigDecimal calculatedTotalValue = calculateTotalValue(productOrderDTO.getTimeHour(), calculatedProductValue);
        this.totalValue = calculatedTotalValue.longValue();

        BigDecimal calculatedUserAmount = calculateUserAmount(productOrderDTO.getTimeHour(), calculatedProductValue, product.getUserValue());
        this.userAmount = calculatedUserAmount.longValue();
    }

    private BigDecimal calculateProductValue(Long productValue) {
        return new BigDecimal(productValue).multiply(BigDecimal.valueOf(100));
    }

    private BigDecimal calculateTotalValue(Integer timeHour, BigDecimal productValue) {
        return productValue.multiply(new BigDecimal(timeHour));
    }

    private BigDecimal calculateUserAmount(Integer timeHour, BigDecimal productValue, BigDecimal userValue) {
        BigDecimal totalValue = calculateTotalValue(timeHour, productValue);
        BigDecimal userValuePercentage = userValue.divide(BigDecimal.valueOf(100));
        return totalValue.multiply(userValuePercentage).setScale(0, RoundingMode.HALF_UP);
    }
}

