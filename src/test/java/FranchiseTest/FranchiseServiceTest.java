package FranchiseTest;

import com.franquicias.Franquicias.entity.Franchise;
import com.franquicias.Franquicias.repository.FranchiseRepository;
import com.franquicias.Franquicias.service.FranchiseService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseService franchiseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Setup mock repository
        Franchise franchise1 = new Franchise();
        franchise1.setId(1L);
        franchise1.setName("Franchise 1");

        Franchise franchise2 = new Franchise();
        franchise2.setId(2L);
        franchise2.setName("Franchise 2");

        when(franchiseRepository.findAll()).thenReturn(Arrays.asList(franchise1, franchise2));

        // Call service method
        List<Franchise> result = franchiseService.findAll();

        // Verify results
        assertEquals(2, result.size());
        verify(franchiseRepository, times(1)).findAll();
    }

    @Test
    void testSave() {
        Franchise franchise = new Franchise();
        franchise.setName("New Franchise");

        when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

        Franchise savedFranchise = franchiseService.save(franchise);

        assertEquals("New Franchise", savedFranchise.getName());
        verify(franchiseRepository, times(1)).save(franchise);
    }

    @Test
    void testDelete() {
        doNothing().when(franchiseRepository).deleteById(1L);
        franchiseService.delete(1L);
        verify(franchiseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateName() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("Old Name");

        // Simula la búsqueda de la franquicia existente
        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));

        // Simula la guardada de la franquicia actualizada
        Franchise updatedFranchise = new Franchise();
        updatedFranchise.setId(1L);
        updatedFranchise.setName("New Name");
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(updatedFranchise);

        // Llama al método a probar
        Franchise result = franchiseService.updateName(1L, "New Name");

        // Verifica que el nombre ha sido actualizado correctamente
        assertNotNull(result);
        assertEquals("New Name", result.getName());

        // Verifica que se llamó al repositorio correctamente
        verify(franchiseRepository, times(1)).findById(1L);
        verify(franchiseRepository, times(1)).save(franchise);
    }


    @Test
    void testUpdateName_FranchiseNotFound() {
        when(franchiseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            franchiseService.updateName(1L, "New Name");
        });

        verify(franchiseRepository, times(1)).findById(1L);
    }
}
