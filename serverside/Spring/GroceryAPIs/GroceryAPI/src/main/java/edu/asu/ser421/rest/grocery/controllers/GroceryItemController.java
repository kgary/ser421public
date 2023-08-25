package edu.asu.ser421.rest.grocery.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.asu.ser421.rest.grocery.model.GroceryItem.GroceryType;
import edu.asu.ser421.rest.grocery.services.GroceryServices;
import edu.asu.ser421.rest.grocery.model.GroceryItem;

@RestController
@RequestMapping("/api/groceries")
public class GroceryItemController {
	private GroceryServices __groceryService = null;
	
	public GroceryItemController() {
		// get the service implementation we will use
		__groceryService = GroceryServices.getGroceryService();  // we should check if it is null
	}

	@GetMapping("/{id}")
	public GroceryItem getGroceryItem(@PathVariable("id") String id) {
		return __groceryService.findOne(id);
	}
	@PostMapping
	public GroceryItem createGroceryItem() {
		return null;
	}
	@PutMapping
	public GroceryItem updateGroceryItem() {
		return null;
	}
	@DeleteMapping("/{id}")
	public void deleteGroceryItem(@PathVariable("id") String id) {
		boolean rval = __groceryService.delete(id);
		// if true we return a 204, else we return a 404 XXX
	}
	
	@GetMapping
	public List<GroceryItem> getGroceryItemsByType(@RequestParam(name = "category", required = false) GroceryType type) {
		if (type == null) {
			return __groceryService.findAll();
		} else {
			return __groceryService.findByCategory(type);
		}
	}
}
