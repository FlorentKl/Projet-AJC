package projetSpringBoot.restController.recette;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projetSpringBoot.model.recette.EtapeRecette;
import projetSpringBoot.service.EtapeRecetteService;

@RestController
@RequestMapping("/rest/etape")
@CrossOrigin(origins = "*")
public class EtapeRestController {
    @Autowired
    EtapeRecetteService etapeService;

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> addEtapes(@RequestBody EtapeRecette[] etapes, BindingResult br,
            UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (EtapeRecette etape : etapes) {
            etapeService.insert(etape);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}