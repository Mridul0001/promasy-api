package com.promasy.api.users;

import com.promasy.api.exceptions.InvalidCredentialsException;
import com.promasy.api.exceptions.UserAlreadyExistsException;
import com.promasy.api.exceptions.UserNotFoundException;
import com.promasy.api.users.request.AddNewUserModel;
import com.promasy.api.users.request.LoginModel;
import com.promasy.api.users.request.SignupModel;
import com.promasy.api.users.response.AddAdminModel;
import com.promasy.api.users.response.AddUserResponseModel;
import com.promasy.api.users.response.LoginResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    AddAdminModel addAdmin(UserModel userModel);
    String getEncryptedPass(String password);
    LoginResponseModel login(LoginModel loginModel) throws UserNotFoundException, InvalidCredentialsException;
    AddUserResponseModel addUser(AddNewUserModel addNewUserModel) throws UserAlreadyExistsException;
    LoginResponseModel signup(SignupModel signupModel) throws Exception;
    List<Projects> findAssociatedProjects(int userId);
    List<Users> getAllUsers(int userId,String name);
}
