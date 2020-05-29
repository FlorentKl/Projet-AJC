package projetSpringBoot.RestController.recette;

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

import projetSpringBoot.model.recette.Dessert;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.recette.DessertService;

@RestController
@RequestMapping("/rest/dessert")
@CrossOrigin(origins = "*")
public class DessertRestController {
    @Autowired
    DessertService dessertService;

    @JsonView(Views.Common.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Dessert>> findAllDessert() {
        return new ResponseEntity<List<Dessert>>(dessertService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.Common.class)
    @GetMapping("/{id}")
    public ResponseEntity<Dessert> findById(@PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);
        return opt.map(dessert -> {
            return new ResponseEntity<Dessert>(dessert, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Dessert>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> addDessert(@RequestBody Dessert dessert, BindingResult br, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dessertService.insert(dessert);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uCB.path("/rest/dessert/{id}").buildAndExpand(dessert.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);
        return opt.map(dessert -> {
            dessertService.deleteById(dessert.getId());
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Dessert dessert, @PathVariable("id") Integer id) {
        Optional<Dessert> opt = dessertService.findById(id);

        return opt.map(temp -> {
            dessertService.update(dessert);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}