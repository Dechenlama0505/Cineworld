package com.cineworld.model;

/**
 * userModel.java
 *
 * This class represents the user entity for the Cineworld application.
 * It serves as a data transfer object (DTO) used to encapsulate user information
 * between the presentation layer and the database/service layers.
 *
 * Attributes include:
 * - Basic identity and contact information (first name, last name, email, phone number)
 * - Account details (username, password, role, profile image)
 * - Multiple constructors to support different use cases (login, registration, profile view)
 *
 * Author: Dechen Lama
 */

public class userModel {
		private int userId;
		private String firstName;
		private String lastName;
		private String email;
		private String role;
		private String phoneNumber;
		private String username;
		private String password;
		private String image; 

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
		
		public userModel(int userId, String firstName, String lastName, String email, String role, String phoneNumber, String username, String image) {
			this.userId = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.role = role;
			this.phoneNumber = phoneNumber;
			this.username = username;
			this.image = image;
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



		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
		
}