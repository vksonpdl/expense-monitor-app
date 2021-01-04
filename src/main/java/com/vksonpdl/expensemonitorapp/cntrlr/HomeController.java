package com.vksonpdl.expensemonitorapp.cntrlr;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vksonpdl.expensemonitorapp.repo.UserRepo;
import com.vksonpdl.expensemonitorapp.response.ResponsePayload;


@RestController
public class HomeController {
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping() 
	public ResponseEntity<ResponsePayload> getHome() {
		
		ResponsePayload responsePayload =new ResponsePayload();
		responsePayload.setStatus("SUCCESS");
		responsePayload.setMessage("UI TBD");
		responsePayload.setResponseDate(new Date());
		
		Map<String, Long> tableSize = new HashMap<>();
		tableSize.put("userRepo", userRepo.count());
		responsePayload.setTableSize(tableSize);
		
		return new ResponseEntity<>(responsePayload,HttpStatus.OK);
	}

}
