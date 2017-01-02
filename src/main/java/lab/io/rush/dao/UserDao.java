package lab.io.rush.dao;

import lab.io.rush.pojo.User;

public interface UserDao {

	/**
	 * 
	 * 
	 * @param user
	 * @return 返回用户
	 */
	User insert(User user);
	
    /**
     * 根据编号选择用户
     *
     * @param id 用户编号
     * @return 用户对象
     */
   
   User selectById(int id);

   
    /**
     * 根据电子邮箱选择用户
     *
     * @param email 电子邮箱
     * @return 用户对象
     */
  boolean selectByEmail(String email);
}
