package Api.NoAbras.Repository;

import Api.NoAbras.Model.CModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class IRepositoryTest {
    @Mock
    IRepository repository;

    @Test
    public void shouldSaveStory() {

        CModel storyOVNI = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(repository.save(storyOVNI)).thenReturn(storyOVNI);

        CModel storySave = repository.save(storyOVNI);

        assertEquals("Avistamiento OVNI", storySave.getName());
        verify(repository, times(1)).save(storyOVNI);
    }
}