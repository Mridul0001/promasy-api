package com.promasy.api.cbl;
/*
* Entry point for CBL related requests. It is different from the core promasy API.
* Created by Mridul*/

import com.promasy.api.constants.GlobalConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CblController {
    @Autowired CblService cblService;

    @GetMapping("/getproducts")
    public ResponseEntity getProjects(){
        try{
            return new ResponseEntity<List<CblModel>>(cblService.getAllProjects(),HttpStatus.OK);
        }catch (Exception e){
            //Adding general exception for now. Might need to configure in future
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/placeorder")
    public ResponseEntity placeOrder(@RequestBody OrderModel orderModel){
        try{
            return new ResponseEntity<OrderModel>(cblService.placeOrder(orderModel),HttpStatus.OK);
        }catch (Exception e){
            //Adding general exception for now. Might need to configure in future
            return new ResponseEntity(GlobalConstants.COMMON_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
