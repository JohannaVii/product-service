package se.iths.johanna.productservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import se.iths.johanna.productservice.dto.ProductRequestDto;
import se.iths.johanna.productservice.dto.ProductResponseDto;
import se.iths.johanna.productservice.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequestDto dto);

    ProductResponseDto toResponse(Product entity);
}
