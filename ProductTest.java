package main.najah.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;
import main.najah.code.Product;

public class ProductTest {
	
	@BeforeAll
	static void setUp() 
	{ 
	   System.out.println("Run Test Cases");
	}

	@Timeout(value = 100 , unit = TimeUnit.MILLISECONDS)
	@ParameterizedTest
    @ValueSource(ints = {30, 40, 50 , 50}) //discount
	void testApplyDiscount(int dis) {
		Product product = new Product("Phone", 8000);
		product.applyDiscount(dis);
		
		double expectedPrice = 8000 * (1 - dis / 100.0);

		assertEquals(expectedPrice  , product.getFinalPrice());

	}
	
	@Test 
    @DisplayName("Test ApplyDiscoun Function with Negtive Discount number")

	void testApplyDiscount2() 
	{
		Product product = new Product("Laptop", 6000);
		assertThrows(IllegalArgumentException.class, ()-> product.applyDiscount(-1));
		
	}
	

}
