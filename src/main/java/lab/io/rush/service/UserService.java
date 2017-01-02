package lab.io.rush.service;

import lab.io.rush.pojo.User;
import lab.io.rush.utils.TicketResult;

public interface UserService {
/**
 * 向user表插入用户信息
 * 
 * @param user
 * @return 保存用户信息
 * @throws Exception 
 */
	TicketResult insert(String account,String pwd,String eamil) throws Exception;

	User selectById(int id);
	
	
	
}
