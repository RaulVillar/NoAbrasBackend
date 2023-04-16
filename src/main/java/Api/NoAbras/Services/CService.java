package Api.NoAbras.Services;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CService {
    @Autowired

    static IRepository iRepository;

    public void createStory(CModel model) {
        iRepository.save(model);

    }

    public List<CModel> readStory() {
        List<CModel> models = new ArrayList<CModel>(iRepository.findAll());
        return models;

    }

    public CModel readStoryId(Long id) {
        return iRepository.findById(id).orElse(null);
    }

    public void updateStory(CModel model, Long id) {
        model.setId(id);
        iRepository.save(model);

    }

    public void deleteStory(Long id) {
        iRepository.deleteById(id);

    }

}

