package org.springboot.productmanagement.service;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springboot.productmanagement.dto.ProductDto;
import org.springboot.productmanagement.model.Product;
import org.springboot.productmanagement.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private ModelMapper modelMapper;

    private ProductRepo productRepo;

    public ProductService(ModelMapper modelMapper, ProductRepo productRepo) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
    }

    public List<ProductDto>getAllProducts() {
    List<Product> products = productRepo.findAll();
    return products.stream()
            .map(product -> modelMapper.map(product, ProductDto.class))
            .toList();
    }

    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    public ProductDto getProductById(int productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        return modelMapper.map(product, ProductDto.class);
    }

    public ProductDto updateProduct(int productId, ProductDto productDto) {
        Product existingProduct = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        existingProduct.setProductId(productDto.getProductId());
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setForSale(productDto.getForSale());

        productRepo.save(existingProduct);
        return modelMapper.map(existingProduct, ProductDto.class);
    }

    public String deleteProduct(int productId) {
        if (!productRepo.existsById(productId)) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
        productRepo.deleteById(productId);
        return "Product deleted";
    }
}
