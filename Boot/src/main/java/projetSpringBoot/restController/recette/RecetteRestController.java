package projetSpringBoot.restController.recette;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projetSpringBoot.model.recette.Recette;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.RecetteService;

@RestController
@RequestMapping("/rest/recette")
@CrossOrigin(origins = "*")
public class RecetteRestController {
    @Autowired
    RecetteService recetteService;

    @JsonView(Views.Common.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Recette>> findAllRecette() {
        return new ResponseEntity<List<Recette>>(recetteService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.Common.class)
    @GetMapping("/{id}")
    public ResponseEntity<Recette> findById(@PathVariable("id") Integer id) {
        Optional<Recette> opt = recetteService.findById(id);
        return opt.map(recette -> {
            return new ResponseEntity<Recette>(recette, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Recette>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Recette> opt = recetteService.findById(id);
        return opt.map(recette -> {
            recetteService.deleteById(recette.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

}