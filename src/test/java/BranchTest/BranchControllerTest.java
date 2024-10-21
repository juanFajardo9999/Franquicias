package BranchTest;

import com.franquicias.Franquicias.controller.BranchController;
import com.franquicias.Franquicias.entity.Branch;
import com.franquicias.Franquicias.entity.Product;
import com.franquicias.Franquicias.service.BranchService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BranchControllerTest {

    @Mock
    private BranchService branchService;

    @InjectMocks
    private BranchController branchController;

    public BranchControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBranchToFranchise() {
        Branch branch = new Branch();
        when(branchService.addBranchToFranchise(1L, branch)).thenReturn(branch);

        ResponseEntity<Branch> response = branchController.addBranchToFranchise(1L, branch);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(branch, response.getBody());
    }

    @Test
    void testDeleteBranch() {
        doNothing().when(branchService).deleteBranch(1L);

        ResponseEntity<Void> response = branchController.deleteBranch(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(branchService).deleteBranch(1L);
    }

    @Test
    void testUpdateBranchName() {
        Branch branch = new Branch();
        when(branchService.updateBranchName(1L, "New Name")).thenReturn(branch);

        ResponseEntity<Branch> response = branchController.updateBranchName(1L, "New Name");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(branch, response.getBody());
    }

    @Test
    void testGetMaxStockProductInBranch() {
        Product product = new Product();
        when(branchService.getMaxStockProductInBranch(1L)).thenReturn(product);

        ResponseEntity<Product> response = branchController.getMaxStockProductInBranch(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }
}
