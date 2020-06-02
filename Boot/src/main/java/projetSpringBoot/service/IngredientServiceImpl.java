package projetSpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projetSpringBoot.model.Ingredients.Ingredient;
import projetSpringBoot.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public Ingredient insert(Ingredient ingredient) {
        String nomIngredient = ingredient.getNom();
        if (nomIngredient == null || nomIngredient.isEmpty()) {
            return ingredient;
        }
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        Optional<Ingredient> opt = ingredientRepository.findById(ingredient.getId());
        if (opt.isPresent()) {
            Ingredient ingrEnBase = opt.get();
            if (ingredient.getNom() != null) {
                ingrEnBase.setNom(ingredient.getNom());
            }
            if (ingredient.getPicture() != null) {
                ingrEnBase.setPicture(ingredient.getPicture());
            }
            return ingredientRepository.save(ingrEnBase);
        }

        throw new IllegalArgumentException("Cet ingredient n'existe pas.");
    }

    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    public void deleteById(Integer id) {
        ingredientRepository.deleteById(id);

    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> findById(Integer id) {
        return ingredientRepository.findById(id);
    }

    public List<Ingredient> findByNom(String nom) {
        return ingredientRepository.findByNom(nom);
    }

    public List<Ingredient> findByNomLike(String nom) {
        return ingredientRepository.findByNomLike(nom);
    }

    public List<Ingredient> findByNomNotLike(String nom) {
        return ingredientRepository.findByNomNotLike(nom);
    }
}
