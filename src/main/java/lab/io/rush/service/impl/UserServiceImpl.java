package lab.io.rush.service.impl;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.io.rush.dao.UserDao;
import lab.io.rush.pojo.User;
import lab.io.rush.service.UserService;
import lab.io.rush.utils.TicketResult;



@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public TicketResult insert(String account, String pwd, String email) throws Exception {
		if(!userDao.selectByEmail(email)){
			
		}
		return TicketResult.ok();
	}
	

	public User selectById(int id) {
		
		return userDao.selectById(id);
	}

	

	

}
