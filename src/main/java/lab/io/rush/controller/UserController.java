package lab.io.rush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lab.io.rush.service.UserService;
import lab.io.rush.utils.TicketResult;


@Controller
public class UserController {
 
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public TicketResult login(String account,String pwd,String email) throws Exception{
		TicketResult result=userService.insert(account, pwd, email);
		return result;
		
	}
}
