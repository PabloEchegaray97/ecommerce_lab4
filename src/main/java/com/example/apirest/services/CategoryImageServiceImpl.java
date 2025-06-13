package com.example.apirest.services;

import com.example.apirest.dto.CategoryImageDTO;
import com.example.apirest.dto.CreateCategoryImageDTO;
import com.example.apirest.entities.CategoryImage;
import com.example.apirest.entities.Product;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.CategoryImageRepository;
import com.example.apirest.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryImageServiceImpl extends BaseServiceImpl<CategoryImage, Integer> implements CategoryImageService {

    @Autowired
    private CategoryImageRepository categoryImageRepository;

    @Autowired
    private ProductRepository productRepository;

    public CategoryImageServiceImpl(BaseRepository<CategoryImage, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryImageDTO> findAllActiveOrderByPosition() throws Exception {
        try {
            List<CategoryImage> images = categoryImageRepository.findAllActiveOrderByPosition();
            return images.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error al obtener las imágenes de categoría activas", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryImageDTO> findAllOrderedNotDeleted() throws Exception {
        try {
            List<CategoryImage> images = categoryImageRepository.findAllOrderedNotDeleted();
            return images.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error al obtener todas las imágenes de categoría", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryImageDTO> findByProductId(Integer productId) throws Exception {
        try {
            List<CategoryImage> images = categoryImageRepository.findByProductId(productId);
            return images.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error al obtener las imágenes de categoría por producto", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryImageDTO> findAllByProductId(Integer productId) throws Exception {
        try {
            List<CategoryImage> images = categoryImageRepository.findAllByProductId(productId);
            return images.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error al obtener todas las imágenes de categoría por producto", e);
        }
    }

    @Override
    @Transactional
    public CategoryImageDTO createCategoryImage(CreateCategoryImageDTO dto) throws Exception {
        try {
            CategoryImage categoryImage = new CategoryImage();
            categoryImage.setImageUrl(dto.getImageUrl());
            categoryImage.setProductName(dto.getProductName());
            categoryImage.setPosition(dto.getPosition());
            categoryImage.setIsActive(true);

            // Si se proporciona productId, buscar y asignar el producto
            if (dto.getProductId() != null) {
                Optional<Product> product = productRepository.findById(dto.getProductId());
                if (product.isPresent()) {
                    categoryImage.setProduct(product.get());
                    categoryImage.setProductId(dto.getProductId());
                }
            }

            CategoryImage savedImage = categoryImageRepository.save(categoryImage);
            return convertToDTO(savedImage);
        } catch (Exception e) {
            throw new Exception("Error al crear la imagen de categoría", e);
        }
    }

    @Override
    @Transactional
    public CategoryImageDTO updatePosition(Integer id, Integer newPosition) throws Exception {
        try {
            Optional<CategoryImage> optionalImage = categoryImageRepository.findById(id);
            if (optionalImage.isEmpty()) {
                throw new Exception("Imagen de categoría no encontrada con ID: " + id);
            }

            CategoryImage categoryImage = optionalImage.get();
            categoryImage.setPosition(newPosition);
            categoryImage.setUpdatedAt(LocalDateTime.now());

            CategoryImage updatedImage = categoryImageRepository.save(categoryImage);
            return convertToDTO(updatedImage);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la posición de la imagen de categoría", e);
        }
    }

    private CategoryImageDTO convertToDTO(CategoryImage categoryImage) {
        CategoryImageDTO dto = new CategoryImageDTO();
        dto.setId(categoryImage.getId());
        dto.setImageUrl(categoryImage.getImageUrl());
        dto.setProductName(categoryImage.getProductName());
        dto.setProductId(categoryImage.getProductId());
        dto.setPosition(categoryImage.getPosition());
        dto.setIsActive(categoryImage.getIsActive());
        dto.setCreatedAt(categoryImage.getCreatedAt());
        dto.setUpdatedAt(categoryImage.getUpdatedAt());
        dto.setDeletedAt(categoryImage.getDeletedAt());

        // Si hay producto asociado, obtener el nombre completo
        if (categoryImage.getProduct() != null) {
            dto.setProductFullName(categoryImage.getProduct().getName());
        }

        return dto;
    }
} 