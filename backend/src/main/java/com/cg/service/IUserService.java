package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.entity.User;
import com.cg.exception.PasswordNotMatchException;
import com.cg.exception.UserNotFoundException;

/*************************************************************************************************************
 * @author 		Sidda Reddy Partha Saradhi
 * Description	It is an interface of service layer that provides various methods for its implementation class
 * @version		1.0
 * @since		25-MAR-2021 
 **************************************************************************************************************/

@Service
public interface IUserService {


	boolean signIn(User user) throws UserNotFoundException;

	boolean signOut(User user) throws UserNotFoundException;

	User changePassword(int userid, User user) throws UserNotFoundException, PasswordNotMatchException;
	
	List<User> getAllUsers();
	
	User getUserById(int userId);
	
	User getUserByEmail(String email);
 
}
