package projetSpringBoot.restController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import projetSpringBoot.model.Commentaire;
import projetSpringBoot.model.CommentaireKey;
import projetSpringBoot.service.CommentaireService;

@RestController
@RequestMapping("/rest/commentaire")
@CrossOrigin(origins = "*")
public class CommentaireRestController {
    @Autowired
    CommentaireService commentaireService;

    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Commentaire>> findAll() {

        return new ResponseEntity<List<Commentaire>>(commentaireService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Commentaire> findById(@PathVariable("id") CommentaireKey id) {
        Optional<Commentaire> opt = commentaireService.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> postCommentaire(@RequestBody Commentaire arc, BindingResult br,
            UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        arc = commentaireService.insert(arc);
        URI uri = uCB.path("/rest/commentaire/{id}").buildAndExpand(arc.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable CommentaireKey id) {
        Optional<Commentaire> opt = commentaireService.findById(id);
        if (opt.isPresent()) {
            commentaireService.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Commentaire arc, @PathVariable("id") CommentaireKey id) {
        Optional<Commentaire> opt = commentaireService.findById(id);
        if (opt.isPresent()) {
            Commentaire commEnBase = opt.get();

            commEnBase = commentaireService.insert(commEnBase);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}