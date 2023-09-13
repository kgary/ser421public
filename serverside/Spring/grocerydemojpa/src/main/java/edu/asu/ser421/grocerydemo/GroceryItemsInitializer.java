package edu.asu.ser421.grocerydemo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.asu.ser421.grocerydemo.model.GroceryItem;
import edu.asu.ser421.grocerydemo.model.GroceryItem.GroceryType;
import edu.asu.ser421.grocerydemo.repository.GroceryItemRepository;

@Configuration
public class GroceryItemsInitializer {
	
	@Bean
    CommandLineRunner groceryItemsCommandLineRunner(GroceryItemRepository groceryItemRepository) {
        return args -> {
            // grocery items initialization
        	List<GroceryItem> groceryItems = Arrays.asList(
    				new GroceryItem("MLK", "milk", GroceryType.DAIRY, 3.99f, false),
    				new GroceryItem("SWC", "swiss cheese", GroceryType.DAIRY, 4.49f, false),
    				new GroceryItem("YOG", "yogurt", GroceryType.DAIRY, 0.99f, false),
    				new GroceryItem("WHB", "wheat bread", GroceryType.BREADS, 2.79f, false),
    				new GroceryItem("GRB", "garlic bread", GroceryType.BREADS, 1.99f, false),
    				new GroceryItem("APL", "apples", GroceryType.PRODUCE, 0.69f, false),
    				new GroceryItem("BRC", "broccoli", GroceryType.PRODUCE, 1.19f, false),
    				new GroceryItem("PAS", "pastrami", GroceryType.DELI, 8.99f, false),
    				new GroceryItem("HAM", "ham", GroceryType.PRODUCE, 5.69f, false)
    		);
        	
        	groceryItemRepository.saveAll(groceryItems);
        };
    }

}
