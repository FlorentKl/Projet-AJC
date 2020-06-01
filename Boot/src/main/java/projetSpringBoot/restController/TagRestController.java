package projetSpringBoot.restController;

import java.net.URI;
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

import projetSpringBoot.model.tag.Tag;
import projetSpringBoot.model.views.Views;
import projetSpringBoot.service.TagService;

@RestController
@RequestMapping("/rest/tag")
@CrossOrigin(origins = "*")
public class TagRestController {
    @Autowired
    TagService tagService;

    @JsonView(Views.TagView.class)
    @GetMapping(value = { "", "/" })
    public ResponseEntity<List<Tag>> findAll() {

        return new ResponseEntity<List<Tag>>(tagService.findAll(), HttpStatus.OK);
    }

    @JsonView(Views.TagView.class)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Tag> findById(@PathVariable("id") Integer id) {
        Optional<Tag> opt = tagService.findById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = { "", "/" })
    public ResponseEntity<Void> postTag(@RequestBody Tag tag, BindingResult br, UriComponentsBuilder uCB) {
        if (br.hasErrors()) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
        tag = tagService.insert(tag);
        URI uri = uCB.path("/rest/commentaire/{id}").buildAndExpand(tag.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Integer id) {
        Optional<Tag> opt = tagService.findById(id);
        if (opt.isPresent()) {
            tagService.delete(opt.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Tag arc, @PathVariable("id") Integer id) {
        Optional<Tag> opt = tagService.findById(id);
        if (opt.isPresent()) {
            Tag commEnBase = opt.get();

            commEnBase = tagService.insert(commEnBase);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}