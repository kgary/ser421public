package edu.asu.ser421.grocerydemo.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.asu.ser421.grocerydemo.dto.GroceryFormList;
import edu.asu.ser421.grocerydemo.dto.PayInfo;
import edu.asu.ser421.grocerydemo.services.PayInfoServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("checkout")
public class CheckoutController {
	
	private final PayInfoServices payInfoService;
	public CheckoutController(PayInfoServices payInfoService) {
		this.payInfoService = payInfoService;
	}
	
	@GetMapping
	public String showCheckoutForm(Model model, HttpSession session) {
		GroceryFormList gfl = new GroceryFormList();
		if (session != null) {
			if (session.getAttribute("selecteditems") != null) {
				gfl = (GroceryFormList) session.getAttribute("selecteditems");
				System.out.println("GFL found in session with items: " + gfl.getGroceryIds().size());
			}
		}
		model.addAttribute("selecteditems", gfl);   // similar to Step 3, but now we need to display it
		model.addAttribute("payinfo", new PayInfo());
		return "checkout";
	}
	
	@PostMapping
	public String processPayment(@RequestParam String action, @Valid @ModelAttribute("payinfo") PayInfo payInfo, BindingResult bindingResult, Model model, HttpSession session) {
		if("cancel".equals(action)) {
			return ("redirect:/groceries");
		}else if("pay".equals(action)) {
			// @Valid says it needs to be validated according to rules on the bean
			// @ModelAttribute says to use the th:object='${payinfo}" from Thymeleaf template, or if we leave it off then template must say th:object=${payInfo} to match classname PayInfo

			// First validate PayInfo is complete - the convenience of Spring!
			if (bindingResult.hasErrors()) {
				System.out.println("VALIDATION ERRORS");

				GroceryFormList gfl = new GroceryFormList();
				if (session != null) {
					if (session.getAttribute("selecteditems") != null) {
						gfl = (GroceryFormList) session.getAttribute("selecteditems");
						System.out.println("GFL found in session with items: " + gfl.getGroceryIds().size());
					}
				}
				model.addAttribute("selecteditems", gfl);   // similar to Step 3, but now we need to display it
				model.addAttribute("payinfo", payInfo);
				return "checkout";
			} else { 
				System.out.println("VALIDATION NO ERRORS");
				payInfoService.savePaymentInfo(payInfo);
			}
			// 2. then simulate payment processing (usually this invokes a 3rd party service)
			// 3. if we did not validate then or payment processing fails we could inform and ask for repayment
			// 4. if we succeed we could print out success, or redirect to landing page, or whatever we want
			// 5. in any case, if we are terminating the workflow we need to terminate the session!
		
			// Here we will presume success, terminate the workflow (Step 5)
			session.invalidate();  // invalidates the session (duh)
			
			// now you can redirect to a static results page to indicate success (Step 4)
			return ("redirect:/success.html");
		}
		
		return ("redirect:/checkout.html");
		
	}
	
}
