package edu.asu.ser421.grocerydemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.ser421.grocerydemo.model.GroceryItem;
import edu.asu.ser421.grocerydemo.model.GroceryItem.GroceryType;
import edu.asu.ser421.grocerydemo.services.GroceryItemService;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("addgroceryitem")
public class AddGroceryItemController {
	
	private final GroceryItemService groceryItemService;
	public AddGroceryItemController(GroceryItemService groceryItemService) {
		this.groceryItemService = groceryItemService;
	}
	
	@GetMapping
	public String showAddGroceryForm(Model model) {
		
		model.addAttribute("groceryitem", new GroceryItem());
		model.addAttribute("groceryTypes", GroceryType.values());
		return "addgroceryitem";
	}
	
	@PostMapping
	public String addGroceryItem(@RequestParam String action, @ModelAttribute GroceryItem gItem, Model model, RedirectAttributes redirectAttributes) {
		if("goback".equals(action)) {
			return ("redirect:/groceries");
		}else if("additem".equals(action)) {
			if (gItem == null) {
				System.out.println("POST: grocery item is null");
			}  else {
				 groceryItemService.saveGroceryItem(gItem);
			}
			
			redirectAttributes.addFlashAttribute("successMessage", "Grocery item added successfully!");
			return "redirect:/addgroceryitem";
		}
		
		return "redirect:/addgroceryitem";
		
	}
}
