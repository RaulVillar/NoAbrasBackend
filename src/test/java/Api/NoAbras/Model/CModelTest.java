package Api.NoAbras.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CModelTest {

    @Test
    void test_get_id_of_the_story() {
        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        Long storyId = story.getId();

        assertNotNull(storyId);
        assertEquals(1L, storyId);
    }

    @Test
    void test_get_name_of_the_story() {

        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        String storyName = story.getName();

        assertNotNull(storyName);
        assertEquals("Avistamiento OVNI", storyName);
    }

    @Test
    void test_get_theme_of_the_story() {
        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        String storyTheme = story.getTheme();

        assertNotNull(storyTheme);
        assertEquals("Terror", storyTheme);
    }

    @Test
    void test_get_description_of_the_story() {
        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        String storyDescription = story.getDescription();

        assertNotNull(storyDescription);
        assertEquals("Avistamiento OVNI de un anciano", storyDescription);
    }

    @Test
    void test_get_location_of_the_story() {
        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        String storyLocation = story.getLocation();

        assertNotNull(storyLocation);
        assertEquals("Mieres", storyLocation);
    }

    @Test
    void test_get_urlImg_of_the_story() {
        CModel story = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        String storyUrlImg = story.getUrlImg();

        assertNotNull(storyUrlImg);
        assertEquals("https://gyazo.com/7726b711943e6da78abf31ec00de9aa1", storyUrlImg);
    }

}