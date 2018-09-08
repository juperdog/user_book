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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long id;

    @Column(name = "username", nullable = false)
    private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

}
