package org.springboot.productmanagement.controller;

import org.springboot.productmanagement.dto.ProductDto;
import org.springboot.productmanagement.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "api/v1/")
@RestController
public class ProductController {

    private ProductService productService;

public ProductController (ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/addProduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);}

    @GetMapping("/getProductById/{productId}")
    public ProductDto getProductById(@PathVariable int productId){
        return productService.getProductById(productId);}

    @PutMapping("/updateProduct/{productId}")
    public ProductDto updateProduct(@PathVariable int productId, @RequestBody ProductDto productDto){
        return productService.updateProduct(productId, productDto);}

    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable int productId){
        return productService.deleteProduct(productId);}


}
