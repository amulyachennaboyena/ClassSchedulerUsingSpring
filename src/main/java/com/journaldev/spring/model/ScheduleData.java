package com.journaldev.spring.model;

import java.util.Date;

public class ScheduleData {
	private Date sheduleDate;	
	private String topics;
	
	
	public Date getSheduleDate() {
		return sheduleDate;
	}
	public void setSheduleDate(Date sheduleDate) {
		this.sheduleDate = sheduleDate;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	
	
	
	
	@Override
	public String toString() {
		
		
		
		return "sheduleDate=" + sheduleDate + ", topics=" + topics +"\n\n\n";
	}
	
	
	




}