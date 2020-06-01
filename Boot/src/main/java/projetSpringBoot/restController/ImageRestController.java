package projetSpringBoot.restController;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import projetSpringBoot.model.imageModel.ImageModel;
import projetSpringBoot.service.ImageService;

@RestController
@RequestMapping("/rest/image")
@CrossOrigin(origins = "*")
public class ImageRestController {
    @Autowired
    ImageService imageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<ImageModel> postImage(@RequestParam("image") MultipartFile file) {
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            ImageModel im = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            im = imageService.insert(im);
            return new ResponseEntity<>(im, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ImageModel> putImage(@PathVariable("image") MultipartFile file,
            @PathVariable("id") Integer id) {

        try {
            ImageModel newIm = new ImageModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            Optional<ImageModel> optIm = imageService.findById(id);
            if (optIm.isPresent()) {
                ImageModel baseIm = optIm.get();
                baseIm.setImage(newIm.getImage());
                baseIm.setName(newIm.getName());
                baseIm.setType(newIm.getType());
                baseIm = imageService.update(baseIm);
                return new ResponseEntity<>(baseIm, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable("id") Integer id) {
        Optional<ImageModel> optIm = imageService.findById(id);

        if (optIm.isPresent()) {
            imageService.deleteById(optIm.get().getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}