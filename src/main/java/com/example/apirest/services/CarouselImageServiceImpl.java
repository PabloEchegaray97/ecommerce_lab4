package com.example.apirest.services;

import com.example.apirest.entities.CarouselImage;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.CarouselImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarouselImageServiceImpl extends BaseServiceImpl<CarouselImage, Integer> implements CarouselImageService {
    
    @Autowired
    private CarouselImageRepository carouselImageRepository;
    
    public CarouselImageServiceImpl(BaseRepository<CarouselImage, Integer> baseRepository) {
        super(baseRepository);
    }
    
    @Override
    @Transactional
    public List<CarouselImage> findAllActiveOrderByPosition() throws Exception {
        try {
            List<CarouselImage> images = carouselImageRepository.findAllActiveOrderByPosition();
            if (images.isEmpty()) {
                throw new Exception("No se encontraron imágenes activas del carousel");
            }
            return images;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public List<CarouselImage> findByProductId(Integer productId) throws Exception {
        try {
            List<CarouselImage> images = carouselImageRepository.findByProductId(productId);
            if (images.isEmpty()) {
                throw new Exception("No se encontraron imágenes del carousel para el producto con id: " + productId);
            }
            return images;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public List<CarouselImage> findAllCatalogLinks() throws Exception {
        try {
            List<CarouselImage> images = carouselImageRepository.findAllCatalogLinks();
            if (images.isEmpty()) {
                throw new Exception("No se encontraron enlaces al catálogo en el carousel");
            }
            return images;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public CarouselImage updatePosition(Integer id, Integer newPosition) throws Exception {
        try {
            Optional<CarouselImage> imageOptional = carouselImageRepository.findById(id);
            if (!imageOptional.isPresent()) {
                throw new Exception("No se encontró la imagen del carousel con id: " + id);
            }
            CarouselImage image = imageOptional.get();
            image.setPosition(newPosition);
            return carouselImageRepository.save(image);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
} 