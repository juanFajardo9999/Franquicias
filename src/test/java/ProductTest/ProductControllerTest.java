package ProductTest;


import com.franquicias.Franquicias.controller.ProductController;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductToBranch() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productService.addProductToBranch(anyLong(), any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.addProductToBranch(1L, product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Product", response.getBody().getName());

        verify(productService, times(1)).addProductToBranch(anyLong(), any(Product.class));
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(anyLong());

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(productService, times(1)).deleteProduct(anyLong());
    }

    @Test
    void testUpdateProductStock() {
        Product product = new Product();
        product.setId(1L);
        product.setStock(100);

        when(productService.updateProductStock(anyLong(), anyInt())).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProductStock(1L, 100);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(100, response.getBody().getStock());

        verify(productService, times(1)).updateProductStock(anyLong(), anyInt());
    }

    @Test
    void testUpdateProductName() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Updated Name");

        when(productService.updateProductName(anyLong(), anyString())).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProductName(1L, "Updated Name");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Name", response.getBody().getName());

        verify(productService, times(1)).updateProductName(anyLong(), anyString());
    }
}
