package com.douzone.mysite.vo;

public class AdminVo {
	private Long no;
	private String title;
	private String message;
	private String profile;
	private String description;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "AdminVo [no=" + no + ", title=" + title + ", message=" + message + ", profile=" + profile
				+ ", description=" + description + "]";
	}
	
	
	
}
