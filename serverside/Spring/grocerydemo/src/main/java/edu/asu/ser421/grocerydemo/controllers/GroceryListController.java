package edu.asu.ser421.grocerydemo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.asu.ser421.grocerydemo.dto.GroceryFormList;
import edu.asu.ser421.grocerydemo.model.GroceryItem;
import edu.asu.ser421.grocerydemo.model.GroceryItem.GroceryType;

@Controller
@RequestMapping("groceries")
public class GroceryListController {

	// Step 2: GET to show groceries
	@GetMapping
	public String showGroceryList(Model model) {
		List<GroceryItem> groceryItems = Arrays.asList(
				new GroceryItem("MLK", "milk", GroceryType.DAIRY, 3.99f),
				new GroceryItem("SWC", "swiss cheese", GroceryType.DAIRY, 4.49f),
				new GroceryItem("YOG", "yogurt", GroceryType.DAIRY, 0.99f),
				new GroceryItem("WHB", "wheat bread", GroceryType.BREADS, 2.79f),
				new GroceryItem("GRB", "garlic bread", GroceryType.BREADS, 1.99f),
				new GroceryItem("APL", "apples", GroceryType.PRODUCE, 0.69f),
				new GroceryItem("BRC", "broccoli", GroceryType.PRODUCE, 1.19f),
				new GroceryItem("PAS", "pastrami", GroceryType.DELI, 8.99f),
				new GroceryItem("HAM", "ham", GroceryType.PRODUCE, 5.69f)
		);
		for (GroceryType gType : GroceryType.values()) {
			model.addAttribute(gType.toString().toLowerCase(), filterByType(groceryItems, gType));
		}
		model.addAttribute("formlist", new GroceryFormList());
		return "groceries";
	}
	
	// filters a List of the enum types by type
	private List<GroceryItem> filterByType(
		      List<GroceryItem> items, GroceryType type) {
		    return items
		              .stream()
		              .filter(x -> x.getGroceryType().equals(type))
		              .collect(Collectors.toList());
	}
	
	@PostMapping
	public String processGroceryForm(@ModelAttribute GroceryFormList gItems, Model model, HttpSession session) {
		if (gItems.getGroceryIds() == null) {
			System.out.println("POST: grocery list null");
		}  else {
			session.setAttribute("selecteditems", gItems);  // step 4
		}
		model.addAttribute("selecteditems", gItems);
		return "processed";
	}
}
