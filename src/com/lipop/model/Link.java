package com.lipop.model;

/*Create Table

CREATE TABLE `t_link` (
  `linkId` int(11) NOT NULL AUTO_INCREMENT,
  `linkName` varchar(20) DEFAULT NULL,
  `linkUrl` varchar(40) DEFAULT NULL,
  `linkEmail` varchar(40) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`linkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8*/

public class Link {
	private int linkId;
	private String linkName;
	private String linkUrl;
	private String linkEmail;
	private int orderNum;
	
	
	public int getLinkId() {
		return linkId;
	}
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkEmail() {
		return linkEmail;
	}
	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Link(String linkName, String linkUrl, String linkEmail, int orderNum) {
		super();
		this.linkName = linkName;
		this.linkUrl = linkUrl;
		this.linkEmail = linkEmail;
		this.orderNum = orderNum;
	}
	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
