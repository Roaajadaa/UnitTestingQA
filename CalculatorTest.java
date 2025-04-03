package main.najah.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Disabled;
import java.util.concurrent.TimeUnit;

import main.najah.code.Calculator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Calculator Tests")
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {
	private static Calculator calc;

	@BeforeAll
	static void setUp() 
	{ 
	   calc = new Calculator();
	   System.out.println("Object initialized successfully");
	}
	
	
	@Test
	@Timeout(value = 30 , unit = TimeUnit.MILLISECONDS)
	void testDivide1()
	{
	int res = calc.divide(3 , 3);
	assertEquals(1 , res);
	}
	
	// Try float 
	// make the Divide method return double not Int. (to fix it)
	@Disabled
	void testDivide2()
	{
	int res = calc.divide(3 , 5);
	assertEquals(0.6, res);
	}
	
	//Expected Error
	@Test
	void testDivide3() 
	{
		assertThrows(ArithmeticException.class, ()-> calc.divide(5 , 0));
	}

    @ParameterizedTest
    @ValueSource(ints = {100, 3000, 100 , 50})
    @DisplayName("Test Sum Function with only one Number")
    void testSum1(int number) 
    {
        assertEquals(number, calc.add(number)); 
    }
    
	@Test
	void testSum2() 
	{
	    assertEquals(0, calc.add());
	}
	
	@Test
	@Order(3)
	 void testFactorial1() 
	{
	    assertEquals(1, calc.factorial(0));
	}
	
	//Expected Error 
	@Test
	@Order(2)
	 void testFactorial2() 
	{
		 assertThrows(IllegalArgumentException.class, ()-> calc.factorial(-1));

	}
	
	@Test
	@Order(1)
	 void testFactorial3() 
	{
	    assertEquals(6, calc.factorial(3));

	}

}
