package Api.NoAbras.Repository;

import Api.NoAbras.Model.CModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IRepositoryTest {
    @Autowired
    IRepository repository;

    @Test
    void ShouldSaveStory(){
        CModel storyOVNI = new CModel(1L, "Avistamiento OVNI", "Terror", "Avistamiento OVNI de un anciano", "Mieres", "https://gyazo.com/7726b711943e6da78abf31ec00de9aa1");

        CModel storySave = repository.save(storyOVNI);

        assertEquals("Avistamiento OVNI", storySave.getName());
    }
}