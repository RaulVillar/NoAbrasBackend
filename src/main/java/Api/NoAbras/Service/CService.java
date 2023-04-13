package Api.NoAbras.Service;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CService {
    @Autowired

    private IRepository iRepository;

    public void createStory(CModel model) {
        iRepository.save(model);

    }

    public List<CModel> readStory() {
        List<CModel> models = new ArrayList<CModel>(iRepository.findAll());
        return models;

    }

    public Optional<CModel> readStoryId(Long id) {
        Optional<CModel> model = iRepository.findById(id);
        return model;
    }

    public void updateStory(CModel model, Long id) {
        model.setId(id);
        iRepository.save(model);

    }

    public void deleteStory(Long id) {
        iRepository.deleteById(id);


    }

}

