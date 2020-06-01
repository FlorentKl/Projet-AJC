package projetSpringBoot.restController.recette;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import projetSpringBoot.model.recette.Entree;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.EntreeService;

@RestController
@RequestMapping("/rest/entree")
@CrossOrigin(origins = "*")
public class EntreeRestController {
    @Autowired
    EntreeService entreeService;

    @JsonView(Views.Common.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Entree>> findAllEntree() {
        return new ResponseEntity<List<Entree>>(entreeService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Entree>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Entree>>(entreeService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/{id}" })
    public ResponseEntity<Entree> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Entree> opt = entreeService.findById(id);
        return opt.map(entree -> {
            return new ResponseEntity<>(entree, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.Common.class)
    @GetMapping("/{id}")
    public ResponseEntity<Entree> findById(@PathVariable("id") Integer id) {
        Optional<Entree> opt = entreeService.findById(id);
        return opt.map(entree -> {
            return new ResponseEntity<Entree>(entree, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Entree>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> addEntree(@RequestBody Entree entree, BindingResult br, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        entreeService.insert(entree);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/entree/{id}").buildAndExpand(entree.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Entree> opt = entreeService.findById(id);
        return opt.map(entree -> {
            entreeService.deleteById(entree.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Entree entree, @PathVariable("id") Integer id) {
        Optional<Entree> opt = entreeService.findById(id);

        return opt.map(temp -> {
            entreeService.update(entree);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}