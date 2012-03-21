package com.qburst.qbwebservice.models;

public class QBRequestBean extends QBBaseBean {
		private String email;
		private String password;
		
		private int categoryId;
			
		public int getCatId() {
			return categoryId;
		}
		public void setCatId(int catId) {
			this.categoryId = catId;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}