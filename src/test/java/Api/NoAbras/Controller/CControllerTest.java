package Api.NoAbras.Controller;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Service.CService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CController.class)
class CControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CService storiesService;

    @Test
    void test_read_stories_return_all_stories() throws Exception {

        ArrayList<CModel> stories = new ArrayList<CModel>();
        CModel avistamientoOvni = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");
        stories.add(avistamientoOvni);

        when. (storiesService.readStory()).thenReturn(stories);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/stories")
                .contentType(MediaType.APPLICATION_JSON));
    .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }
}