package lab.io.rush.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import lab.io.rush.dao.UserDao;
import lab.io.rush.datanucleus.dao.DataNucleusDao;
import lab.io.rush.pojo.User;




@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	
    private DataNucleusDao dataNucleusDao;
    

	public DataNucleusDao getDataNucleusDao() {
		return dataNucleusDao;
	}

	public void setDataNucleusDao(DataNucleusDao dataNucleusDao) {
		this.dataNucleusDao = dataNucleusDao;
	}

	public User selectByUserIP(String userip) {
		String query = "userip=='" + userip.trim() + "'";
        List<User> users = dataNucleusDao.selectByQuery(User.class, query);
        if (users != null && users.size()>0){
            return users.get(0);}
        return null;
	}

	public boolean selectByEmail(String email){
		String query="select * from user u where u.email=:email";
		List<User> users=dataNucleusDao.selectByQuery(User.class, query);
		 if (users != null && users.size()>0){
	            return true;
	            }
	        return false;
		
	}

	public User insert(User user) {
		
		return null;
	}

	public User selectById(int id) {
		
		return null;
	}

	
	

}
