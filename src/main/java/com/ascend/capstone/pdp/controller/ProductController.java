package com.ascend.capstone.pdp.controller;

import com.ascend.capstone.pdp.entity.Product;
import com.ascend.capstone.pdp.entity.Wishlist;
import com.ascend.capstone.pdp.repo.SearchRepository;
import com.ascend.capstone.pdp.service.ProductService;
import com.ascend.capstone.pdp.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private WishlistService wishlistservice;
    @Autowired
    SearchRepository srepo;

    @GetMapping("/all")
    @CrossOrigin
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/getByID/{id}")
    @CrossOrigin
    public ResponseEntity<Object> get (@PathVariable int id)
    {
        try {
            productService.getProductById(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, productService.getProductById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Object> insert(@RequestBody Product product){
        try {
            productService.addProduct(product);
            return ResponseHandler.generateResponse("Successfully inserted data!", HttpStatus.OK, productService.addProduct(product));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Product product ){
        try {
            productService.updateProduct(id,product);
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, productService.updateProduct(id,product));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id ){
        try {
            productService.deleteProduct(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, productService.deleteProduct(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/getByText/{text}")
    @CrossOrigin
    public List<Product> search(@PathVariable String text){
        return srepo.findByText(text);
    }

    @PostMapping("/addwishlist")
    //public Wishlist insert_wishlist(@RequestBody Wishlist wishlist){
    //   return wishlistservice.addlist(wishlist);

    // }
    public ResponseEntity<Object> insert_wishlist(@RequestBody Wishlist wishlist){
        try {
            wishlistservice.addlist(wishlist);
            return ResponseHandler.generateResponse("Added to Wishlist!", HttpStatus.OK, wishlistservice.addlist(wishlist));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    @DeleteMapping("/deletewishlist")
    //public Wishlist delete_wishlist(@PathVariable int id){

    //  return  wishlistservice.deleteProduct(id);
    //}


    public ResponseEntity<Object> delete_wishlist(@RequestBody Wishlist wishlist){
        try {
            //wishlistservice.deleteProduct(wishlist);
            return ResponseHandler.generateResponse("Deleted from Wishlist!", HttpStatus.OK, wishlistservice.deleteProduct(wishlist));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }


    @GetMapping("/wishlistall/{user}")
    public List<Wishlist> getWishlist(@PathVariable String user) {
        return  wishlistservice.getlist(user);


    }
}
