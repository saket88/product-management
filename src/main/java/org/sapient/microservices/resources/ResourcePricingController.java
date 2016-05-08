package org.sapient.microservices.resources;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourcePricingController {


	@Autowired
	protected ResourcePricingService pricingService;

	@Autowired
	protected ResourceProductService productService;
	
	protected Logger logger = Logger.getLogger(ResourcePricingController.class
			.getName());

	public ResourcePricingController(ResourcePricingService pricingService) {
		this.pricingService = pricingService;
	} 

	//TODO:To add a check using product service whether that product exists of not
	@RequestMapping(value = "/productPrice/code/{code}", method = RequestMethod.GET)
	public @ResponseBody Map<String,BigDecimal>  byCode(@PathVariable("code") String code) {
		Map<String,BigDecimal> result = new HashMap<String,BigDecimal>();
		BigDecimal price = pricingService.findByCode(code);
		logger.info(" Price "+ price);
		result.put("Price", price);
		return result;
		
	}
}
