package BranchTest;


import com.franquicias.Franquicias.entity.Branch;
import com.franquicias.Franquicias.entity.Franchise;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.repository.BranchRepository;
import com.franquicias.Franquicias.repository.FranchiseRepository;
import com.franquicias.Franquicias.repository.ProductRepository;
import com.franquicias.Franquicias.service.BranchService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private FranchiseRepository franchiseRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private BranchService branchService;

    private Branch branch;
    private Franchise franchise;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("Franchise 1");

        branch = new Branch();
        branch.setId(1L);
        branch.setName("Branch 1");
        branch.setFranchise(franchise);

        product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setStock(10);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setStock(20);
    }

    @Test
    void testAddBranchToFranchise() {
        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch savedBranch = branchService.addBranchToFranchise(1L, branch);

        assertNotNull(savedBranch);
        assertEquals(branch.getName(), savedBranch.getName());
        verify(branchRepository, times(1)).save(branch);
        verify(franchiseRepository, times(1)).findById(1L);
    }

    @Test
    void testAddBranchToFranchise_FranchiseNotFound() {
        when(franchiseRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            branchService.addBranchToFranchise(1L, branch);
        });

        assertEquals("Franchise not found", thrown.getMessage());
        verify(branchRepository, never()).save(any());
    }

    @Test
    void testDeleteBranch() {
        doNothing().when(branchRepository).deleteById(1L);
        branchService.deleteBranch(1L);
        verify(branchRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateBranchName() {
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch updatedBranch = branchService.updateBranchName(1L, "New Name");

        assertNotNull(updatedBranch);
        assertEquals("New Name", updatedBranch.getName());
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void testGetMaxStockProductInBranch() {
        branch.setProducts(Arrays.asList(product1, product2));
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        Product maxStockProduct = branchService.getMaxStockProductInBranch(1L);

        assertNotNull(maxStockProduct);
        assertEquals(product2.getName(), maxStockProduct.getName());
    }

    @Test
    void testGetMaxStockProductInBranch_NoProductsFound() {
        branch.setProducts(Arrays.asList());
        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            branchService.getMaxStockProductInBranch(1L);
        });

        assertEquals("No products found in branch", thrown.getMessage());
    }
}
