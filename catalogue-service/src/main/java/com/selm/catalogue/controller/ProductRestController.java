package com.selm.catalogue.controller;


import com.selm.catalogue.controller.payload.UpdateProductPayload;
import com.selm.catalogue.entity.Product;
import com.selm.catalogue.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products/{productId:\\d+}")
public class ProductRestController {

    private final ProductService productService;

    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product getProduct(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId).orElseThrow(() ->
                new NoSuchElementException("catalogue.errors.products.not_found"));
    }

    @GetMapping
    public Product findProduct(@ModelAttribute("product") Product product) {
        return product;
    }

    @PatchMapping
    public ResponseEntity<?> updateProduct(@PathVariable("productId") int productId,
                                           @Valid @RequestBody UpdateProductPayload payload,
                                           BindingResult bindingResult)
            throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.productService.updateProduct(productId, payload.title(), payload.details());
            return ResponseEntity.noContent()
                    .build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") int productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent()
                .build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException exception,
                                                                      Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        this.messageSource.getMessage(exception.getMessage(), new Object[0],
                                exception.getMessage(), locale)));
    }

    public ProductService getProductService() {
        return this.productService;
    }

    public MessageSource getMessageSource() {
        return this.messageSource;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductRestController)) return false;
        final ProductRestController other = (ProductRestController) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$productService = this.getProductService();
        final Object other$productService = other.getProductService();
        if (this$productService == null ? other$productService != null : !this$productService.equals(other$productService))
            return false;
        final Object this$messageSource = this.getMessageSource();
        final Object other$messageSource = other.getMessageSource();
        if (this$messageSource == null ? other$messageSource != null : !this$messageSource.equals(other$messageSource))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductRestController;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $productService = this.getProductService();
        result = result * PRIME + ($productService == null ? 43 : $productService.hashCode());
        final Object $messageSource = this.getMessageSource();
        result = result * PRIME + ($messageSource == null ? 43 : $messageSource.hashCode());
        return result;
    }

    public String toString() {
        return "ProductRestController(productService=" + this.getProductService() + ", messageSource=" + this.getMessageSource() + ")";
    }
}
