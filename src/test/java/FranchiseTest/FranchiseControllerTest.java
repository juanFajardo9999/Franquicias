package FranchiseTest;


import com.franquicias.Franquicias.controller.FranchiseController;
import com.franquicias.Franquicias.entity.Franchise;
import com.franquicias.Franquicias.service.FranchiseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FranchiseControllerTest {

    @Mock
    private FranchiseService franchiseService;

    @InjectMocks
    private FranchiseController franchiseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFranchises() {
        // Setup mock service
        Franchise franchise1 = new Franchise();
        franchise1.setId(1L);
        franchise1.setName("Franchise 1");

        Franchise franchise2 = new Franchise();
        franchise2.setId(2L);
        franchise2.setName("Franchise 2");

        when(franchiseService.findAll()).thenReturn(Arrays.asList(franchise1, franchise2));

        // Call controller method
        ResponseEntity<List<Franchise>> response = franchiseController.getAllFranchises();

        // Verify results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(franchiseService, times(1)).findAll();
    }

    @Test
    void testCreateFranchise_Success() {
        Franchise franchise = new Franchise();
        franchise.setName("New Franchise");

        when(franchiseService.save(any(Franchise.class))).thenReturn(franchise);

        ResponseEntity<Franchise> response = franchiseController.createFranchise(franchise);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(franchise, response.getBody());
        verify(franchiseService, times(1)).save(franchise);
    }

    @Test
    void testCreateFranchise_Failure() {
        when(franchiseService.save(any(Franchise.class))).thenThrow(RuntimeException.class);

        ResponseEntity<Franchise> response = franchiseController.createFranchise(new Franchise());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(franchiseService, times(1)).save(any(Franchise.class));
    }

    @Test
    void testDeleteFranchise_Success() {
        doNothing().when(franchiseService).delete(1L);

        ResponseEntity<Void> response = franchiseController.deleteFranchise(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(franchiseService, times(1)).delete(1L);
    }

    @Test
    void testDeleteFranchise_Failure() {
        doThrow(new RuntimeException()).when(franchiseService).delete(1L);

        ResponseEntity<Void> response = franchiseController.deleteFranchise(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(franchiseService, times(1)).delete(1L);
    }

    @Test
    void testUpdateFranchiseName_Success() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("Updated Franchise");

        when(franchiseService.updateName(1L, "Updated Franchise")).thenReturn(franchise);

        ResponseEntity<Franchise> response = franchiseController.updateFranchiseName(1L, "Updated Franchise");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(franchise, response.getBody());
        verify(franchiseService, times(1)).updateName(1L, "Updated Franchise");
    }

    @Test
    void testUpdateFranchiseName_Failure() {
        when(franchiseService.updateName(1L, "Updated Franchise")).thenThrow(RuntimeException.class);

        ResponseEntity<Franchise> response = franchiseController.updateFranchiseName(1L, "Updated Franchise");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(franchiseService, times(1)).updateName(1L, "Updated Franchise");
    }
}
