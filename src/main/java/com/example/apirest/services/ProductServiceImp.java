package com.example.apirest.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apirest.entities.Product;
import com.example.apirest.repositories.BaseRepository;
import com.example.apirest.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp extends BaseServiceImpl<Product, Integer> implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImp(BaseRepository<Product, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Page<Product> search(String filtro, Pageable pageable) throws Exception {
        try {
            return productRepository.search(filtro, pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Product> findAllWithSizes() throws Exception {
        try {
            return productRepository.findAllWithSizes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Product> findByIdWithSizes(Integer id) throws Exception {
        try {
            return productRepository.findByIdWithSizes(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Product> findAllActiveFiltered() throws Exception {
        List<Product> productos = super.findAllActive();
        return productos.stream()
            .filter(p -> 
                p.getColour() != null && p.getColour().getDeletedAt() == null && Boolean.TRUE.equals(p.getColour().getIsActive()) &&
                p.getCategory() != null && p.getCategory().getDeletedAt() == null && Boolean.TRUE.equals(p.getCategory().getIsActive()) &&
                p.getBrand() != null && p.getBrand().getDeletedAt() == null && Boolean.TRUE.equals(p.getBrand().getIsActive())
                // Si tenés tipo (Type), agregalo acá:
                // && p.getType() != null && p.getType().getDeletedAt() == null && Boolean.TRUE.equals(p.getType().getIsActive())
            )
            .toList();
    }
}
