package com.lipop.model;

import java.util.Date;

/*Create Table

CREATE TABLE `t_news` (
  `newId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL,
  `content` text,
  `publishDate` datetime DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  `isHead` tinyint(4) DEFAULT NULL,
  `isImage` tinyint(4) DEFAULT NULL,
  `imageName` varchar(20) DEFAULT NULL,
  `isHot` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`newId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
*/
public class News {
	private int newId=-1;
	private String title;
	private String content;
	private Date publishDate;
	private String author;
	private int typeId;
	private String typeName;
	private int click;
	private int isHead;
	private int isImage;
	private String imageName;
	private int isHot;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNewId() {
		return newId;
	}
	public void setNewId(int newId) {
		this.newId = newId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getIsHead() {
		return isHead;
	}
	public void setIsHead(int isHead) {
		this.isHead = isHead;
	}
	public int getIsImage() {
		return isImage;
	}
	public void setIsImage(int isImage) {
		this.isImage = isImage;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	
	
	
}
