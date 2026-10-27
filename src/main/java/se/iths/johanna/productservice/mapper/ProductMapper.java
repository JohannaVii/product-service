package se.iths.johanna.productservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.entity.Product;

/**
 * Mappar produktdata mellan DTO och entity
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    /**
     * Mappar ProductRequestDto till Product
     *
     * @param dto innehåller produktinformationen som ska mappas
     * @return en Product entity
     */
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequestDto dto);

    /**
     * Mappar Product till ProductResponseDto
     *
     * @param entity innehåller produkten som ska mappas
     * @return en ProductResponseDto
     */
    ProductResponseDto toResponse(Product entity);
}
