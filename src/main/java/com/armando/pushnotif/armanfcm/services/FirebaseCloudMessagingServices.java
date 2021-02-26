package com.armando.pushnotif.armanfcm.services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.armando.pushnotif.armanfcm.dto.NotificationRequestDto;
import com.armando.pushnotif.armanfcm.dto.SubscriptionRequestDto;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;


//feAe3GItRJSIZpDZ5Hr890:APA91bG1DGeSIpZ5CrUsT3-1uahR1YqQfUETII34xEZMUGvIY_EY86GwLjzMYvCPyTH9TMHZubFqkwkK-B0F35n-Di-Xipr38hyJ2CNlPKlZI_5cqG9pwHXgR_dqvu-bnJARhda0ajuM

@Slf4j
@Service
public class FirebaseCloudMessagingServices {
	 @Value("${app.firebase-config}")
	    private String firebaseConfig;

	    private FirebaseApp firebaseApp;

	    @PostConstruct
	    private void initialize() {
	        try {
	            FirebaseOptions options = new FirebaseOptions.Builder()
	                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

	            if (FirebaseApp.getApps().isEmpty()) {
	                this.firebaseApp = FirebaseApp.initializeApp(options);
	            } else {
	                this.firebaseApp = FirebaseApp.getInstance();
	            }
	        } catch (IOException e) {
//	            log.error("Create FirebaseApp Error", e);
	        }
	    }

	    public void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto) {
	        try {
	            FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequestDto.getTokens(),
	                    subscriptionRequestDto.getTopicName());
	        } catch (FirebaseMessagingException e) {
//	            log.error("Firebase subscribe to topic fail", e);
	        }
	    }

	    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
	        try {
	            FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequestDto.getTokens(),
	                    subscriptionRequestDto.getTopicName());
	        } catch (FirebaseMessagingException e) {
//	            log.error("Firebase unsubscribe from topic fail", e);
	        }
	    }

	    public String sendPnsToDevice(NotificationRequestDto notificationRequestDto) {
	        Message message = Message.builder()
	                .setToken(notificationRequestDto.getTarget())
	                .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
	                .putData("content", notificationRequestDto.getTitle())
	                .putData("body", notificationRequestDto.getBody())
	                .build();

	        String response = null;
	        try {
	            response = FirebaseMessaging.getInstance().send(message);
	        } catch (FirebaseMessagingException e) {
//	            log.error("Fail to send firebase notification", e);
	        }

	        return response;
	    }

	    public String sendPnsToTopic(NotificationRequestDto notificationRequestDto) {
	        Message message = Message.builder()
	                .setTopic(notificationRequestDto.getTarget())
	                .setNotification(new Notification(notificationRequestDto.getTitle(), notificationRequestDto.getBody()))
	                .putData("content", notificationRequestDto.getTitle())
	                .putData("body", notificationRequestDto.getBody())
	                .build();

	        String response = null;
	        try {
	            FirebaseMessaging.getInstance().send(message);
	        } catch (FirebaseMessagingException e) {
//	            log.error("Fail to send firebase notification", e);
	        }

	        return response;
	    }
	}


