package Api.NoAbras.Controller;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Services.CService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CController.class)
@ExtendWith(MockitoExtension.class)
class CControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CService cService;

    @Test
    void ReadStoriesShouldReturnAllStories() throws Exception {

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
    void ReadStoryIdShouldReturnStoryById() throws Exception {

        CModel avistamientoOvni = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        when(cService.readStoryId(1L)).thenReturn(avistamientoOvni);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/model/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        CModel result = objectMapper.readValue(content, CModel.class);
        assertEquals(result, avistamientoOvni);
    }
}

