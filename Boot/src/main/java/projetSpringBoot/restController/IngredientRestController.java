package projetSpringBoot.restController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/ingredient")
@CrossOrigin(origins = "*")
public class IngredientRestController {

}