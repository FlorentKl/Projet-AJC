package test.ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import formationJpa.config.Config;
import formationJpa.entity.Ingredients.Ingredient;
import formationJpa.service.IngredientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class })
public class TestIngredientService {
    @Autowired
    private IngredientService ingredientService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Transactional
    @Rollback(true)
    public void insertIngredient() {
        Ingredient i = new Ingredient("test Ingredient");
        ingredientService.insert(i);
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    @Rollback(true)
    public void insertIngredientNomEmpty() {
        Ingredient i = new Ingredient();
        ingredientService.insert(i);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void majIngredient() {
        Ingredient i = new Ingredient("i");

        ingredientService.insert(i);
        assertNotEquals("nouveau", i.getNom());

        i.setNom("nouveau");
        ingredientService.update(i);
        assertEquals("nouveau", i.getNom());
    }

    @Test(expected = NoSuchElementException.class)
    @Transactional
    @Rollback(true)
    public void findByIdInexistant() {
        assertNull(ingredientService.findById(-1).get());
    }
}
