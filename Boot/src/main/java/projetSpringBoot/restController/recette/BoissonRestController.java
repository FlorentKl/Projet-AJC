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

import projetSpringBoot.model.recette.Boisson;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.BoissonService;

@RestController
@RequestMapping("/rest/boisson")
@CrossOrigin(origins = "*")
public class BoissonRestController {
    @Autowired
    BoissonService boissonService;

    /*
     * Get Mapping
     */

    @JsonView(Views.RecetteView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Boisson>> findAllBoisson() {
        return new ResponseEntity<List<Boisson>>(boissonService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Boisson>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Boisson>>(boissonService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/{id}")
    public ResponseEntity<Boisson> findById(@PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);
        return opt.map(boisson -> {
            return new ResponseEntity<Boisson>(boisson, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Boisson>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/{id}" })
    public ResponseEntity<Boisson> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);
        return opt.map(boisson -> {
            System.out.println(boisson.getTags());
            return new ResponseEntity<>(boisson, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Boisson>> findByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/notnom/{nom}")
    public ResponseEntity<List<Boisson>> findByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/nom/{nom}")
    public ResponseEntity<List<Boisson>> findAllByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/notnom/{nom}")
    public ResponseEntity<List<Boisson>> findAllByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(boissonService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    /*
     * Post Mapping
     */

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> addBoisson(@RequestBody Boisson boisson, BindingResult br, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        boissonService.insert(boisson);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/boisson/{id}").buildAndExpand(boisson.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /*
     * Delete Mapping
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);
        return opt.map(boisson -> {
            boissonService.deleteById(boisson.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    /*
     * Put Mapping
     */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Boisson boisson, @PathVariable("id") Integer id) {
        Optional<Boisson> opt = boissonService.findById(id);

        return opt.map(temp -> {
            boissonService.update(boisson);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}