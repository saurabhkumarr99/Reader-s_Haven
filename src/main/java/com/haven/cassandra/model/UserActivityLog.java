package com.haven.cassandra.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class UserActivityLog {

	@PrimaryKey
	private int id;
	
	private int customer_id;
	private String activityType;
	private String activityDetails;

	public UserActivityLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserActivityLog(int id, int customer_id, String activityType, String activityDetails) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.activityType = activityType;
		this.activityDetails = activityDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityDetails() {
		return activityDetails;
	}

	public void setActivityDetails(String activityDetails) {
		this.activityDetails = activityDetails;
	}

	@Override
	public String toString() {
		return "UserActivityLog [id=" + id + ", customer_id=" + customer_id + ", activityType=" + activityType
				+ ", activityDetails=" + activityDetails + "]";
	}

}
