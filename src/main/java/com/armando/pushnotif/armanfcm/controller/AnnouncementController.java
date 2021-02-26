package com.armando.pushnotif.armanfcm.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armando.pushnotif.armanfcm.dto.NotificationRequestDto;
import com.armando.pushnotif.armanfcm.dto.SubscriptionRequestDto;
import com.armando.pushnotif.armanfcm.services.FirebaseCloudMessagingServices;



@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
	   @Autowired
	   private FirebaseCloudMessagingServices fcm;
	   
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
	  
	
}
	  


