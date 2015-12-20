package com.blog.greeting;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class GuestbookController {
	private final GuestbookDao guestbookDao;

	@Autowired
	public GuestbookController(final GuestbookDao guestbookDao) {
		this.guestbookDao = guestbookDao;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String read(Model model) {
		System.out.println("I am in get method");
//		model.addAttribute("greetings", greetings);
		return "index";
	}
	
	@RequestMapping(value = "/construction", method = RequestMethod.GET)
	public String construction(Model model) {
		return "construction";
	}

	
}