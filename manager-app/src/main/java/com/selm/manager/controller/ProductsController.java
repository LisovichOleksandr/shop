package com.selm.manager.controller;

import com.selm.manager.client.BadRequestException;
import com.selm.manager.client.ProductsRestClient;
import com.selm.manager.controller.payload.NewProductPayload;
import com.selm.manager.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalogue/products")
public class ProductsController {
    private final ProductsRestClient productsRestClient;

    @GetMapping("/list")
    public String getProductsList(Model model){
        List<Product> products = this.productsRestClient.findAllProduct();
        model.addAttribute("products", products);
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage(){
        return "catalogue/products/new_product";
    }

    @PostMapping("/create")
    public String createProduct(NewProductPayload payload,
                                Model model){
            try {
                Product product = this.productsRestClient.createProduct(payload.title(), payload.details());
                return "redirect:/catalogue/products/%d".formatted(product.id());
            }   catch (BadRequestException exception) {
                model.addAttribute("payload", payload);
                model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
            }
    }
}
