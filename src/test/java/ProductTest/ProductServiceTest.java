package ProductTest;

import com.franquicias.Franquicias.entity.Branch;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.repository.BranchRepository;
import com.franquicias.Franquicias.repository.ProductRepository;
import com.franquicias.Franquicias.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductToBranch() {
        Branch branch = new Branch();
        branch.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("New Product");

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.addProductToBranch(1L, product);

        assertNotNull(result);
        assertEquals("New Product", result.getName());
        assertEquals(branch, result.getBranch());

        verify(branchRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateProductStock() {
        Product product = new Product();
        product.setId(1L);
        product.setStock(50);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProductStock(1L, 100);

        assertNotNull(result);
        assertEquals(100, result.getStock());

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProductName() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Old Name");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProductName(1L, "New Name");

        assertNotNull(result);
        assertEquals("New Name", result.getName());

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);
    }
}

