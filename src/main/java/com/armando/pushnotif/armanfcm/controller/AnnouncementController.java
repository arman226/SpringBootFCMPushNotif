package com.armando.pushnotif.armanfcm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.armando.pushnotif.armanfcm.dto.UserDto;
import java.util.List;
import com.armando.pushnotif.armanfcm.dto.NotificationRequestDto;
import com.armando.pushnotif.armanfcm.dto.SubscriptionRequestDto;
import com.armando.pushnotif.armanfcm.repositories.IUserRepository;
import com.armando.pushnotif.armanfcm.services.FirebaseCloudMessagingServices;



@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
	   @Autowired
	   private FirebaseCloudMessagingServices fcm;
	   
	   @Autowired
	   private IUserRepository usr;
	   
	   @PostMapping("/subscribe")
	    public void subscribeToTopic(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
	        fcm.subscribeToTopic(subscriptionRequestDto);
	    }
	   
	    @PostMapping("/unsubscribe")
	    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
	        fcm.unsubscribeFromTopic(subscriptionRequestDto);
	    }

	    @PostMapping("/token")
	    public String sendPnsToDevice(@RequestBody NotificationRequestDto notificationRequestDto) {
	        return fcm.sendPnsToDevice(notificationRequestDto);
	    }
	  

	    @PostMapping("/topic")
	    public String sendPnsToTopic(@RequestBody NotificationRequestDto notificationRequestDto) {
	        return fcm.sendPnsToTopic(notificationRequestDto);
	    }
	  
	    
	    @GetMapping("/user")
	    public List<UserDto> getUser() {
	        return usr.getUsers();
	    }
	    
	    
	    @PostMapping("/addUser")
	    public Integer addUser(String name) {
	    	return usr.addUser(name);
	    }
	    
	    
	    @PostMapping("/sendAnnouncementToUser")
	    public String sendAnnouncementToUser(@RequestParam Integer id,@RequestParam String title,@RequestParam String body) {
	    	NotificationRequestDto req= new NotificationRequestDto();
	    	req.setTitle(title);
	    	req.setBody(body);
	    	req.setTarget(usr.getUserToken(id));
			return fcm.sendPnsToDevice(req);
	    	
	    }
	
}
	  


