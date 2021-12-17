package com.promasy.api.users;

import com.promasy.api.constants.GlobalConstants;
import com.promasy.api.exceptions.InvalidCredentialsException;
import com.promasy.api.exceptions.UserAlreadyExistsException;
import com.promasy.api.exceptions.UserNotFoundException;
import com.promasy.api.helpers.SequenceService;
import com.promasy.api.repos.UserRepository;
import com.promasy.api.users.request.AddNewUserModel;
import com.promasy.api.users.request.LoginModel;
import com.promasy.api.users.request.SignupModel;
import com.promasy.api.users.response.AddAdminModel;
import com.promasy.api.users.response.AddUserResponseModel;
import com.promasy.api.users.response.LoginResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{
    @Autowired SequenceService sequenceService;
    @Autowired UserRepository userRepository;
    @Override
    public AddAdminModel addAdmin(UserModel userModel) {
        int id = sequenceService.getSequence(GlobalConstants.USERS);
        userModel.setUserId(id);
        userModel.setAdminId(id);
        userModel.setAdmin(true);
        userModel.setPassword(getEncryptedPass(userModel.getPassword()));
        userModel.setProjects(new ArrayList<>());
        userRepository.save(userModel);
        AddAdminModel addAdminModel=new AddAdminModel();
        addAdminModel.setUserId(id);
        return addAdminModel;
    }

    @Override
    public String getEncryptedPass(String password){
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no=new BigInteger(1,messageDigest);
            String hash = no.toString(16);
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LoginResponseModel login(LoginModel loginModel) throws UserNotFoundException, InvalidCredentialsException {
        UserModel userModel = userRepository.findUserByEmail(loginModel.getEmail());
        LoginResponseModel loginResponseModel = new LoginResponseModel();
        if(userModel!=null){
            if(!userModel.getPassword().equals(loginModel.getPassword())) throw new InvalidCredentialsException(GlobalConstants.INVALID_CREDENTIALS_MESSAGE);
            else{
                loginResponseModel.setAdmin(userModel.isAdmin());
                loginResponseModel.setUserId(userModel.getUserId());
                loginResponseModel.setUser(userModel.getName());
            }
        }else throw new UserNotFoundException(GlobalConstants.USER_NOT_FOUND_MESSAGE);

        return loginResponseModel;
    }

    @Override
    public AddUserResponseModel addUser(AddNewUserModel addNewUserModel) throws UserAlreadyExistsException {
        AddUserResponseModel addUserResponseModel=new AddUserResponseModel();
        if(userRepository.findUserByEmail(addNewUserModel.getEmail())!=null) throw new UserAlreadyExistsException(GlobalConstants.USER_ALREADY_EXISTS_MESSAGE);
        else{
            UserModel userModel=new UserModel();
            int id = sequenceService.getSequence(GlobalConstants.USERS);
            userModel.setUserId(id);
            userModel.setAdminId(addNewUserModel.getAdminId());
            userModel.setAdmin(false);
            userModel.setEmail(addNewUserModel.getEmail());
            userModel.setName("");
            userModel.setPassword("");
            userRepository.save(userModel);
            addUserResponseModel.setUserId(id);
            addUserResponseModel.setAdminId(addNewUserModel.getAdminId());
        }

        return addUserResponseModel;
    }

    @Override
    public LoginResponseModel signup(SignupModel signupModel) throws Exception{
        UserModel userModel = userRepository.findUserById(signupModel.getUserId());
        if(userModel!=null && userModel.getPassword().equals("")){
            userModel.setName(signupModel.getName());
            userModel.setPassword(signupModel.getPassword());
            userModel.setProjects(new ArrayList<>());
            userRepository.save(userModel);
            LoginResponseModel loginResponseModel = new LoginResponseModel();
            loginResponseModel.setUser(userModel.getName());
            loginResponseModel.setAdmin(userModel.isAdmin());
            loginResponseModel.setUserId(userModel.getUserId());
            return loginResponseModel;
        }

        throw new Exception(GlobalConstants.COMMON_ERROR_MESSAGE);
    }

    @Override
    public List<Projects> findAssociatedProjects(int userId) {
        UserModel userModel = userRepository.findAssociatedProjects(userId);
        return userModel.getProjects();
    }

    @Override
    public List<Users> getAllUsers(int userId, String name){
        List<Users> users = new ArrayList<>();
        List<UserModel> list=userRepository.findAll(userId);
        for(UserModel u: list){
            Users users1 = new Users();
            users1.setUserId(u.getUserId());
            users1.setName(u.getName());
            users.add(users1);
        }
        if(users.size()==0){
            Users user = new Users();
            user.setName(name);
            user.setUserId(userId);
            users.add(user);
        }
        return users;
    }
}
