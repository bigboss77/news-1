package com.lipop.model;

import java.util.Date;

/*
 * 
Create Table

CREATE TABLE `t_comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `newId` int(11) DEFAULT NULL,
  `userIp` varchar(20) DEFAULT NULL,
  `commentDate` datetime DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

 */
public class Comment {
	
	private String newsName;
	private int commentId;
	private int newId=-1;
	private String userIp;
	private Date commentDate;
	private String content;
	
	
	public String getNewsName() {
		return newsName;
	}
	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}
	public Comment(int newId, String userIp, String content) {
		super();
		this.newId = newId;
		this.userIp = userIp;
		this.content = content;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getNewId() {
		return newId;
	}
	public void setNewId(int newId) {
		this.newId = newId;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public Date getDatetime() {
		return commentDate;
	}
	public void setCommentDate(Date datetime) {
		this.commentDate = datetime;
	}
	
	
}
