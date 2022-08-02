package com.makeawish.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 6, message = "{username.length.invalid.msg}")
	private String username;

	@Pattern(regexp = "((?=.*[A-Z]).{6,10})", message = "{password.regex.error.msg}")
	private String password;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@NotNull(message = "{activity.empty.error.msg}")
	private String activity;

	@NotEmpty(message = "{firstname.empty.msg}")
	private String firstName;
	private String lastName;

	private Date dateOfBirth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", activity=" + activity + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, "dateOfBirth",
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}

}
