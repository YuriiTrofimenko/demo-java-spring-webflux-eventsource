package org.tyaa.demo.java.springboot.webflux.model;

// import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
public class User {
    
        private static int lastId = 0;
	@NotBlank(message = "userName is mandatory")
	String userName;
	// @BsonId
        @Id
	int userId;
	@NotBlank(message = "phone is mandatory")
	String phone;

        public User() {
            this.userId = ++lastId;
        }
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userId=" + userId + ", phone=" + phone + "]";
	}
}
