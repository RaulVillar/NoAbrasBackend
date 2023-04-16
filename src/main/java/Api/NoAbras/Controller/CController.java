package Api.NoAbras.Controller;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Services.CService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
@CrossOrigin("*")

public class CController {
    @Autowired
    CService cService;

    @GetMapping("")
    private List<CModel> readStory() {
        return cService.readStory();

    }

    @GetMapping("/{id}")
    private CModel readStoryId(@PathVariable("id") Long id) {
        return cService.readStoryId(id);

    }

    @PostMapping("")
    private void createStory(@RequestBody CModel model) {
        cService.createStory(model);

    }

    @PutMapping("/{id}")
    private void updateStory(@RequestBody CModel model, @PathVariable("id") Long id) {
        cService.updateStory(model, id);

    }

    @DeleteMapping("/{id}")
    private void deleteStory(@PathVariable("id") Long id) {
        cService.deleteStory(id);


    }


}


