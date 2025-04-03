package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import main.najah.code.RecipeBook;
import main.najah.code.Recipe;
import org.junit.jupiter.api.BeforeEach;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RecipeBookTest {
    
    private RecipeBook recipeBook;
    private Recipe recipe;

    @BeforeEach
    void setUp() throws RecipeException  {
        recipeBook = new RecipeBook(); 
        recipe = new Recipe();
        recipe.setPrice("10");
        recipe.setAmtCoffee("2");
        recipe.setAmtMilk("1");
        recipe.setAmtSugar("1");
        recipe.setAmtChocolate("0");
      
    }
    
    @Test
    void testAddRecipe() {
        boolean result = recipeBook.addRecipe(recipe);
        assertTrue(result, "Recipe should be added successfully");
    }
    
    @Test
    @DisplayName("test Add Duplicate Recipe into Recipe Book")
    void testAddRecipe2() {
        recipeBook.addRecipe(recipe); 
        boolean result = recipeBook.addRecipe(recipe); 
        assertFalse(result, "Duplicate recipe should not be added");
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0 ,1}) // Testing valid indices
    @Timeout(value = 100 , unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test Editing Recipe at Different Indices")
    
    void testEditRecipe(int index) throws RecipeException {
        for (int i = 0; i < 4; i++) {  
            recipe = new Recipe();
            recipe.setName("Recipe " + i);
            recipe.setPrice("10");
            recipe.setAmtCoffee("2");
            recipe.setAmtMilk("1");
            recipe.setAmtSugar("1");
            recipe.setAmtChocolate("0");

            boolean added = recipeBook.addRecipe(recipe);
            assertTrue(added, "Recipe " + i + " should be added successfully");
        }
        //to replace the existing one 
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Updated Recipe " + index);
        newRecipe.setPrice("15");
        newRecipe.setAmtCoffee("3");
        newRecipe.setAmtMilk("2");
        newRecipe.setAmtSugar("2");
        newRecipe.setAmtChocolate("1");

        String oldRecipeName = recipeBook.editRecipe(index, newRecipe);

        assertNotNull(oldRecipeName, "Old recipe name should be returned for index " + index);
 
        assertEquals("", recipeBook.getRecipes()[index].getName(), 
        	    "Recipe at index " + index + " should have an empty name.");

        
    }

}