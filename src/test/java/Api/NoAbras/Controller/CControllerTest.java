package Api.NoAbras.Controller;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Security.service.CService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CController.class)
@ExtendWith(MockitoExtension.class)

class CControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CController cController;
    @MockBean
    private CService cService;

    @Test
    void shouldReturnAllStories() throws Exception {

        ArrayList<CModel> stories = new ArrayList<>();
        CModel avistamientoOvni = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        stories.add(avistamientoOvni);

        when(cService.readStory()).thenReturn(stories);
        String username = "user";
        String password = "password";
        String role = "USER";
        mockMvc.perform(MockMvcRequestBuilders.get("/model")
                        .with(user(username).password(password).roles(role))) // Utiliza user() para simular un usuario autenticado
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Avistamiento OVNI")); // Verifica el valor de la respuesta esperada



    }

    @Test
    void shouldReadStoryById() throws Exception {



        String username = "user";
        String password = "password";
        String role = "ADMIN"; // Asignar un rol válido según tu configuración

        // Configurar datos de prueba
        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(cService.readStoryId(story.getId())).thenReturn(story);

        // Realizar petición GET con usuario autenticado y roles asignados
        mockMvc.perform(MockMvcRequestBuilders.get("/model")
                        .with(user(username).password(password).roles(role)))
                .andExpect(status().isOk())
                .andExpect(content().json("[  {    \"id\": 1,    \"name\": \"Avistamiento OVNI\",    \"theme\": \"Terror\",    \"description\": \"Avistamiento OVNI de un anciano\",    \"location\": \"Mieres\",    \"urlImg\": \"https://gyazo.com/7726b711943e6da78abf31ec00de9aa1\"  }]"))
                .andExpect(jsonPath("$[0].name").value("Avistamiento OVNI"));; // Verificar la respuesta esperada en formato JSON
        // Verificar que el servicio fue llamado una vez con el ID de historia correcto
        verify(cService, times(1)).readStoryId(story.getId());
    }


    @Test
    void shouldCreateStory() throws Exception {

        CModel newStory = new CModel(1L, "Nueva historia", "Comedia", "Descripción", "Oviedo", "https://gyazo.com/123456");
        String jsonRequest = new ObjectMapper().writeValueAsString(newStory);

        when(cService.createStory(any(CModel.class))).thenReturn(newStory);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());

        verify(cService, times(1)).createStory(any(CModel.class));
    }

    @Test
    void shouldUpdateStoryById() throws Exception {
        // Arrange
        Long storyId = 1L;
        CModel existingStory = new CModel(storyId, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(cService.readStoryId(storyId)).thenReturn(existingStory);

        CModel updatedStory = new CModel(storyId, "Nueva historia", "Comedia", "Descripción actualizada", "Oviedo", "https://gyazo.com/123456");
        String jsonRequest = new ObjectMapper().writeValueAsString(updatedStory);

        // Act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/model/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertNotNull(updatedStory);
        verify(cService, times(1)).readStoryId(storyId);
        verify(cService, times(1)).updateStory(eq(updatedStory), eq(storyId));
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


