package com.my.netflix.homepage;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
     * Tiles를 사용하지 않은 일반적인 형태
     */    
    @RequestMapping("/test.mn")
    public String test() {
        return "test";
    }    
    
    /**
     * Tiles를 사용(topbar 포함)
     */
    @RequestMapping("/testHome.mn")
    public String testTemplate() {
        return "test.hm";
    }
    
    /**
     * Tiles를 사용(topbar + filter 포함)
     */    
    @RequestMapping("/testSearch.mn")
    public String testEmpty() {
        return "test.sh";
    }    
	
}
