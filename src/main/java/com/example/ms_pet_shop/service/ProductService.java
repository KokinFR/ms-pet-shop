package com.example.ms_pet_shop.service;

import com.example.ms_pet_shop.dto.ProductDTO;
import com.example.ms_pet_shop.dto.ResponseDTO;
import com.example.ms_pet_shop.entity.Product;
import com.example.ms_pet_shop.exception.ProductAlreadyExistsException;
import com.example.ms_pet_shop.exception.ProductNotFoundException;
import com.example.ms_pet_shop.mapper.ProductMapper;
import com.example.ms_pet_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ms_pet_shop.contracts.ProductConstracts.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ResponseDTO create(ProductDTO dto) {
        boolean exists = productRepository.existsByDescriptionAndMaker(dto.getDescription(), dto.getMaker());
        if (exists) {
            throw new ProductAlreadyExistsException(PRODUCT_DUPLICATE_MESSAGE);
        }
        Product product = productMapper.dtoToEntity(dto);
        product.setActive(true);
        productRepository.save(product);

        return ResponseDTO.builder()
                .message(PRODUCT_201_CREATED_MESSAGE)
                .statusHttp(PRODUCT_201_STATUS)
                .build();
    }

    public ResponseDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        PRODUCT_404_MESSAGE
                ));

        boolean exists = productRepository.existsByDescriptionAndMakerAndIdNot(
                dto.getDescription(), dto.getMaker(), id);

        if (exists) {
            throw new ProductAlreadyExistsException(PRODUCT_DUPLICATE_MESSAGE);
        }

        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setMaker(dto.getMaker());

        productRepository.save(product);

        return ResponseDTO.builder()
                .message(PRODUCT_200_UPDATED_MESSAGE)
                .statusHttp(PRODUCT_200_STATUS)
                .build();

    }

    public List<ProductDTO> findAllActive() {
        return productRepository.findByActiveTrue()
                .stream()
                .map(productMapper::entityToDto)
                .toList();

    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_404_MESSAGE));

        return productMapper.entityToDto(product);
    }

    public ResponseDTO deactivate(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_404_MESSAGE));

        product.setActive(false);
        productRepository.save(product);

        return ResponseDTO.builder()
                .message(PRODUCT_200_DEACTIVATED_MESSAGE)
                .statusHttp(PRODUCT_200_STATUS)
                .build();

    }

    public ResponseDTO delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_404_MESSAGE));

        productRepository.delete(product);

        return ResponseDTO.builder()
                .message(PRODUCT_200_DELETED_MESSAGE)
                .statusHttp(PRODUCT_200_STATUS)
                .build();

    }

}
