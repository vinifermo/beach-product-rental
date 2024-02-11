package com.salsatechnology.factory;

import com.salsatechnology.dto.ProductOrderDTO;
import com.salsatechnology.model.ProductType;

public class ProductFactory {
    public static ProductStrategy getProductFactory(ProductOrderDTO productOrderDTO) {
        ProductType productType = productOrderDTO.getProductType();

        switch (productType) {
            case SURFBOARD:
                return new SurfboardStrategy();
            case BEACH_CHAIR:
                return new BeachChairStrategy();
            case SUNSHADE:
                return new SunshadeStrategy();
            case SAND_BOARD:
                return new SandBoardStrategy();
            case BEACH_TABLE:
                return new BeachTableStrategy();
            default:
                return null;
        }
    }
}
