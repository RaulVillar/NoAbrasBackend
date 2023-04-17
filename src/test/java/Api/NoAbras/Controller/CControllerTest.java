package Api.NoAbras.Controller;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Services.CService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CController.class)
@ExtendWith(MockitoExtension.class)
class CControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CService cService;

    @Test
    void shouldReturnAllStories() throws Exception {

        ArrayList<CModel> stories = new ArrayList<>();
        CModel avistamientoOvni = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        stories.add(avistamientoOvni);

        when(cService.readStory()).thenReturn(stories);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/model")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertTrue(response.getContentAsString().contains("Avistamiento OVNI"));

    }

    @Test
    void shouldReadStoryById() throws Exception {

        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(cService.readStoryId(story.getId())).thenReturn(story);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/model/{id}", story.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        CModel returnedStory = new ObjectMapper().readValue(content, CModel.class);
        assertEquals(story.getId(), returnedStory.getId());
        assertEquals(story.getName(), returnedStory.getName());
        assertEquals(story.getTheme(), returnedStory.getTheme());
        assertEquals(story.getDescription(), returnedStory.getDescription());
        assertEquals(story.getLocation(), returnedStory.getLocation());
        assertEquals(story.getUrlImg(), returnedStory.getUrlImg());
        verify(cService, times(1)).readStoryId(story.getId());
    }


    @Test
    void shouldCreateStory() throws Exception {

        CModel newStory = new CModel(1L, "Nueva historia", "Comedia", "Descripción", "Oviedo", "https://gyazo.com/123456");
        String jsonRequest = new ObjectMapper().writeValueAsString(newStory);

        when(cService.createStory(newStory)).thenReturn(newStory);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        verify(cService, times(1)).createStory(newStory);
    }

    @Test
    void shouldUpdateStoryById() throws Exception {

        CModel existingStory = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(cService.readStoryId(1L)).thenReturn(existingStory);

        CModel updatedStory = new CModel(1L, "Nueva historia", "Comedia", "Descripción actualizada", "Oviedo", "https://gyazo.com/123456");
        String jsonRequest = new ObjectMapper().writeValueAsString(updatedStory);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/model/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        verify(cService, times(1)).readStoryId(1L);
        verify(cService, times(1)).updateStory(eq(updatedStory), eq(1L));
    }

    @Test
    void shouldDeleteStory() throws Exception {

        Long storyId = 1L;
        doNothing().when(cService).deleteStory(storyId);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/model/{id}", storyId))
                .andExpect(status().isOk())
                .andReturn();

        verify(cService, times(1)).deleteStory(storyId);
    }

}

