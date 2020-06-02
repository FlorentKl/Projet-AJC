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

import projetSpringBoot.model.recette.Plat;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.PlatService;

@RestController
@RequestMapping("/rest/plat")
@CrossOrigin(origins = "*")
public class PlatRestController {
    @Autowired
    PlatService platService;

    /*
     * Get Mapping
     */

    @JsonView(Views.RecetteView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Plat>> findAllPlat() {
        return new ResponseEntity<List<Plat>>(platService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all" })
    public ResponseEntity<List<Plat>> findAllRecetteWithAll() {
        return new ResponseEntity<List<Plat>>(platService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/{id}")
    public ResponseEntity<Plat> findById(@PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        return opt.map(plat -> {
            return new ResponseEntity<Plat>(plat, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Plat>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping(value = { "/all/{id}" })
    public ResponseEntity<Plat> findByIdWithAll(@PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        return opt.map(plat -> {
            return new ResponseEntity<>(plat, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/nom/{nom}")
    public ResponseEntity<List<Plat>> findByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteView.class)
    @GetMapping("/notnom/{nom}")
    public ResponseEntity<List<Plat>> findByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/nom/{nom}")
    public ResponseEntity<List<Plat>> findAllByNomLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomContaining(nom), HttpStatus.OK);
    }

    @JsonView(Views.RecetteWithAll.class)
    @GetMapping("/all/notnom/{nom}")
    public ResponseEntity<List<Plat>> findAllByNomNotLike(@PathVariable("nom") String nom) {
        return new ResponseEntity<>(platService.findByNomNotContaining(nom), HttpStatus.OK);
    }

    // Check si nom recette existe déjà
    @GetMapping("/check/{string}")
    public ResponseEntity<Boolean> checkNom(@PathVariable("string") String string) {
        Optional<Plat> opt = platService.findByNom(string);
        if (opt.isPresent()) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /*
     * Post Mapping
     */

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> addPlat(@RequestBody Plat plat, BindingResult br, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        platService.insert(plat);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/plat/{id}").buildAndExpand(plat.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /*
     * Delete Mapping
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);
        return opt.map(plat -> {
            platService.deleteById(plat.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    /*
     * Put Mapping
     */

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Plat plat, @PathVariable("id") Integer id) {
        Optional<Plat> opt = platService.findById(id);

        return opt.map(temp -> {
            platService.update(plat);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}