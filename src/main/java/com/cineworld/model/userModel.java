package com.cineworld.model;

public class userModel {
		private int userId;
		private String firstName;
		private String lastName;
		private String email;
		private String role;
		private String phoneNumber;
		private String username;
		private String password;

		public userModel() {
		}

		public userModel(String firstName) {
			this.firstName = firstName;
		}

		public userModel(String username,String password) {
			this.username = username; 
			this.password = password;
		}
		
		public userModel(int userId, String firstName, String lastName, String email, String role, String phoneNumber, String username) {
			super();
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			this.phoneNumber = phoneNumber;
			this.username = username;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String
		getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
}


