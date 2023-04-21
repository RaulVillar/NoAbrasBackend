package Api.NoAbras.Services;

import Api.NoAbras.Model.CModel;


import Api.NoAbras.Repository.IRepository;
import Api.NoAbras.Security.service.CService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CServiceTest {

    @InjectMocks
    CService cService;

    @Mock
    private IRepository repository;

    @Test
    void shouldReturnStoryById() {
        Long id = 1L;
        CModel storyOVNI = new CModel(id, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        IRepository repository = Mockito.mock(IRepository.class);
        when(repository.findById(id)).thenReturn(Optional.of(storyOVNI));

        CService cService = new CService();
        cService.iRepository = repository;

        CModel storyById = cService.readStoryId(id);
        assertNotNull(storyById);
        assertEquals("Avistamiento OVNI", storyById.getName());
    }

    @Test
    void shouldReturnAllStories() {

        CModel storyOVNI = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        IRepository repository = Mockito.mock(IRepository.class);
        when(repository.findAll()).thenReturn(Collections.singletonList(storyOVNI));

        CService cService = new CService();
        cService.iRepository = repository;

        List<CModel> stories = cService.readStory();

        assertNotNull(stories);
        assertEquals(1, stories.size());
        assertEquals(storyOVNI.getId(), stories.get(0).getId());
        assertEquals(storyOVNI.getName(), stories.get(0).getName());
        assertEquals(storyOVNI.getTheme(), stories.get(0).getTheme());
        assertEquals(storyOVNI.getDescription(), stories.get(0).getDescription());
        assertEquals(storyOVNI.getLocation(), stories.get(0).getLocation());
        assertEquals(storyOVNI.getUrlImg(), stories.get(0).getUrlImg());
    }

    @Test
    void shouldSaveStory() {

        CModel storyOVNI = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(repository.save(storyOVNI)).thenReturn(storyOVNI);

        CModel storySave = repository.save(storyOVNI);

        assertEquals(storyOVNI, storySave);
        verify(repository, times(1)).save(storyOVNI);
    }

    @Test
    void shouldUpdateStory() {

        CModel storyOVNI = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(repository.save(storyOVNI)).thenReturn(storyOVNI);

        CModel updatedStoryOVNI = new CModel(1L, "Avistamiento OVNI", "Science Fiction", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(repository.save(updatedStoryOVNI)).thenReturn(updatedStoryOVNI);

        CModel storySave = repository.save(storyOVNI);
        CModel updatedStorySave = repository.save(updatedStoryOVNI);

        assertEquals(storyOVNI, storySave);
        assertEquals(updatedStoryOVNI, updatedStorySave);
        verify(repository, times(2)).save(any(CModel.class));
    }

    @Test
    void shouldDeleteStory() {
        Long storyId = 1L;
        CModel story = new CModel(storyId, "The story to be deleted", "Horror", "A scary story", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        doNothing().when(repository).deleteById(storyId);

        repository.deleteById(storyId);

        verify(repository, times(1)).deleteById(storyId);
    }
}
