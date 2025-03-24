package com.junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.junitapp.main.service.DemoService;

public class DemoServiceTest 
{
	DemoService demoService = new DemoService();

	@Test
	public void sum() {
		// checking not null
		assertNotNull(demoService);
		// use case : int values
		Assertions.assertEquals( 30,demoService.sum(10, 20) );
		// use case : float values
		Assertions.assertEquals( 22,demoService.sum(10F, 12F) );
		// use case : mixed data types
		Assertions.assertEquals( 22,demoService.sum(10, 12F) );
		// use case : negative values
		Assertions.assertEquals( -22,demoService.sum(-10, -12) );
		// use case : assert not equals
		Assertions.assertNotEquals( 22,demoService.sum(-20, 12F) );
	}

	@Test
	public void computePercent()
	{
		/*Functional use cases */
		//Use case 1: give 3 marks and check the percent
		/*List<Double> list = new ArrayList<>();
		list.add(87d);
		list.add(76.0);
		list.add(68D);
		*/
		List<Double> list = Arrays.asList(87d,76.0,68d);
		Double expected = 77.0;
		
		Assert.assertEquals( expected , demoService.computePercent(list));
		//Use case: list given is null 
				NullPointerException npe = 
						assertThrows(NullPointerException.class, ()->demoService.computePercent(null));
				
				assertEquals("list cannot be null".toLowerCase(), npe.getMessage().toLowerCase());
				
				//use case : list is empty 
				List<Double> listEmpty = Arrays.asList(); 
				RuntimeException re = 
						assertThrows(RuntimeException.class, ()-> demoService.computePercent(listEmpty));
				assertEquals("No Subjects given".toLowerCase(), re.getMessage().toLowerCase());
				
				List<Double> list2 = Arrays.asList(87d,102.0,68D); 
				re = assertThrows(RuntimeException.class, ()-> demoService.computePercent(list2));
				assertEquals("Marks of subject cannot be more than 100".toLowerCase(), re.getMessage().toLowerCase());
				
				List<Double> list3 = Arrays.asList(87d,76.0,-5D); 
				re = assertThrows(RuntimeException.class, ()-> demoService.computePercent(list3));
				assertEquals("Marks of subject cannot be negative".toLowerCase(), re.getMessage().toLowerCase());
	}
}
