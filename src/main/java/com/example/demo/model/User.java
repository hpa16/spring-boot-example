package com.example.demo.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "user")
public class User
{

	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@JsonView(View.Base.class)
	@Column(name = "username")
	@NotEmpty(message = "Please provide your username")
	private String username;

	@Column(name = "password", updatable = true)
	@Length(min = 5, message = "Your password must have at least 5 characters")
	@NotEmpty(message = "Please provide your password")
	@Transient
	private String password;

	@JsonView(View.Base.class)
	@Column(name = "email", unique = true)
	@Email(message = "Please provide a valid Email")
	@NotEmpty(message = "Please provide an email")
	private String email;

	@Column(name = "role", nullable = false)
	private String role = "USER";

	@JsonView(View.UserTags.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private Set<UserTag> userTags = new HashSet<UserTag>();

	@OrderBy("createdDate")
	@JsonView(View.UserQuestions.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Question> questions = new HashSet<Question>();

	@OrderBy("createdDate")
	@JsonView(View.UserComplete.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Answer> answers = new HashSet<Answer>();

	public User()
	{
	}

	public User(String username, String password, String email)
	{
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		//this.setRoles(role);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getEmail());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof User))
			return false;

		final User user = (User) object;
		return Objects.equals(this.getEmail(), user.getEmail());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getRole()
	{
		return role;
	}

	public void setRoles(String role)
	{
		this.role = role;
	}

	public Set<UserTag> getUserTags()
	{
		return userTags;
	}

	public void setUserTags(Set<UserTag> userTags)
	{
		this.userTags = userTags;
	}

	public void addUserTags(UserTag userTag)
	{
		this.userTags.add(userTag);
	}

	public Set<Question> getQuestions()
	{
		return questions;
	}

	public void setQuestions(Set<Question> questions)
	{
		this.questions = questions;
	}

	public void addQuestion(Question question)
	{
		this.questions.add(question);
	}

	public Set<Answer> getAnswers()
	{
		return answers;
	}

	public void setAnswers(Set<Answer> answers)
	{
		this.answers = answers;
	}

	public void addAnswer(Answer answer)
	{
		this.answers.add(answer);
	}
}