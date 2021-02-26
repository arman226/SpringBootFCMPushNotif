package com.armando.pushnotif.armanfcm.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubscriptionRequestDto {

    public SubscriptionRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubscriptionRequestDto(String topicName, List<String> tokens) {
		super();
		this.topicName = topicName;
		this.tokens = tokens;
	}
	String topicName;
    List<String> tokens;
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public List<String> getTokens() {
		return tokens;
	}
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
}
