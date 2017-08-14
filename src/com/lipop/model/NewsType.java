package com.lipop.model;


/*Create Table

CREATE TABLE `t_newstype` (
  `newsTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`newsTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
*/
public class NewsType {
	private int newsTypeId;
	private String typeName;
	
	
	public int getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public NewsType(int newsTypeId, String typeName) {
		super();
		this.newsTypeId = newsTypeId;
		this.typeName = typeName;
	}
	public NewsType() {
		super();
	}
	
	
}
