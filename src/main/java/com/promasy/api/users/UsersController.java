package com.promasy.api.users;

import com.promasy.api.constants.GlobalConstants;
import com.promasy.api.exceptions.InvalidCredentialsException;
import com.promasy.api.exceptions.UserAlreadyExistsException;
import com.promasy.api.exceptions.UserNotFoundException;
import com.promasy.api.users.request.AddNewUserModel;
import com.promasy.api.users.request.LoginModel;
import com.promasy.api.users.request.SignupModel;
import com.promasy.api.users.response.AddAdminModel;
import com.promasy.api.users.response.AddUserResponseModel;
import com.promasy.api.users.response.LoginResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class UsersController {
    @Autowired UsersService usersService;
    @PostMapping("/addadmin")
    public ResponseEntity<AddAdminModel> addAdmin(@RequestBody UserModel userModel){
        try{
            return new ResponseEntity<AddAdminModel>(usersService.addAdmin(userModel), HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginModel loginModel){
        try{
            return new ResponseEntity<LoginResponseModel>(usersService.login(loginModel),HttpStatus.OK);
        }catch (InvalidCredentialsException e){
            e.printStackTrace();
            return new ResponseEntity<>(GlobalConstants.INVALID_CREDENTIALS_MESSAGE,HttpStatus.BAD_REQUEST);
        }catch (UserNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(GlobalConstants.USER_NOT_FOUND_MESSAGE,HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/adduser")
    public ResponseEntity createUser(@RequestBody AddNewUserModel addNewUserModel){
        try{
            return new ResponseEntity<AddUserResponseModel>(usersService.addUser(addNewUserModel),HttpStatus.OK);
        }catch (UserAlreadyExistsException e){
            e.printStackTrace();
            return new ResponseEntity<>(GlobalConstants.USER_ALREADY_EXISTS_MESSAGE,HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupModel signupModel){
        try{
            return new ResponseEntity<LoginResponseModel>(usersService.signup(signupModel),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/associatedprojects/{userId}")
    public ResponseEntity getAssociatedProjects(@PathVariable(name = "userId") int userId){
        try{
            return new ResponseEntity<List<Projects>>(usersService.findAssociatedProjects(userId),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getusers/{userId}/{name}")
    public ResponseEntity getUsers(@PathVariable(name = "userId") int userId, @PathVariable(name = "name") String name){
        try{
            return new ResponseEntity<List<Users>>(usersService.getAllUsers(userId,name),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
