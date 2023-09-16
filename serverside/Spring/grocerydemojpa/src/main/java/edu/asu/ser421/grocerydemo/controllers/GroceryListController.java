package edu.asu.ser421.grocerydemo.controllers;

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
import edu.asu.ser421.grocerydemo.services.GroceryItemService;

@Controller
@RequestMapping("groceries")
public class GroceryListController {
	
	private final GroceryItemService groceryItemService;
	
	public GroceryListController(GroceryItemService groceryItemService) {
		this.groceryItemService = groceryItemService;
	}

	// Step 2: GET to show groceries
	@GetMapping
	public String showGroceryList(Model model, HttpSession session) {
		
		List<GroceryItem> groceryItems = groceryItemService.getGroceryList();

		GroceryFormList groceryFormList = new GroceryFormList();
		
		if (session != null) {
			if (session.getAttribute("selecteditems") != null) {
				groceryFormList = (GroceryFormList) session.getAttribute("selecteditems");
				System.out.println("GFL found in session with items: " + groceryFormList.getGroceryIds().size());
				List<String> selectedItems =  groceryFormList.getGroceryIds();
				for (GroceryItem gItem: groceryItems) {
					if (selectedItems.contains(gItem.getId())) {
						gItem.setSubscribed(true);
					}else {
						gItem.setSubscribed(false);
					}					
				}
			}
		}
		
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
