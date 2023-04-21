package Api.NoAbras.Controller;

import Api.NoAbras.Model.CModel;
import Api.NoAbras.Security.service.CService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/model")


public class CController {
    @Autowired
    CService cService;

    @GetMapping("")
    public List<CModel> readStory() {
        return cService.readStory();

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CModel readStoryId(@PathVariable("id") Long id) {
        return cService.readStoryId(id);

    }

    @PostMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createStory(@RequestBody CModel model) {
        cService.createStory(model);

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStory(@RequestBody CModel model, @PathVariable("id") Long id) {
        cService.updateStory(model, id);

    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStory(@PathVariable("id") Long id) {
        cService.deleteStory(id);


    }



}


