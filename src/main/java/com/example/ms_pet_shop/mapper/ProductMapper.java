package com.example.ms_pet_shop.mapper;

import com.example.ms_pet_shop.dto.ProductDTO;
import com.example.ms_pet_shop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO entityToDto(Product product);
    Product dtoToEntity(ProductDTO productDTO);

}
