package com.jup.bookorder.bookorder.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
@Entity
@Table(name = "User")
public class User {
	@Id
    @Column(name = "username", nullable = false)
    private String username;
	
//	@Column(name = "title", nullable = false)
//	@NotBlank(message="Title should not be blank or null")
//	@NotFound
//	private String title;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

}
