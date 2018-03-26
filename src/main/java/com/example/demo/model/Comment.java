package com.example.demo.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "comment")
public class Comment
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	private int id;

	@JsonView(View.Base.class)
	@Column(name = "text")
	@NotEmpty(message = "Please enter your comment")
	private String text;

	//type 1: question, type 2: answer
	@Column(name = "type")
	private int type;

	@JsonView(View.Base.class)
	@Column(name = "created", updatable = false, nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@JsonView(View.Base.class)
	@Column(name = "modified")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	//@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonView(View.Base.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonView(View.CommentComplete.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "comment_question", joinColumns = { @JoinColumn(name = "comment_id") }, inverseJoinColumns = { @JoinColumn(name = "question_id") })
	private Question question;

	@JsonView(View.CommentComplete.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "comment_answer", joinColumns = { @JoinColumn(name = "comment_id") }, inverseJoinColumns = { @JoinColumn(name = "answer_id") })
	private Answer answer;

	public Comment()
	{
	}

	public Comment(String text, User user, Question question)
	{
		this.setType(1);
		this.setText(text);
		this.setUser(user);
		this.setQuestion(question);
	}

	public Comment(String text, User user, Answer answer)
	{
		super();
		this.setType(2);
		this.setText(text);
		this.setUser(user);
		this.setAnswer(answer);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getUser(), this.getCreatedDate());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Comment))
			return false;

		final Comment comment = (Comment) object;
		return Objects.equals(this.getCreatedDate(),
				comment.getCreatedDate()) && Objects.equals(this.getUser(), comment.getUser());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	/*
	public Timestamp getCreatedTimestamp()
	{
		return createdTimestamp;
	}
	
	public void setCreatedTimestamp(Timestamp createdTimestamp)
	{
		this.createdTimestamp = createdTimestamp;
	}
	
	public Timestamp getModifiedTimestamp()
	{
		return modifiedTimestamp;
	}
	
	public void setModifiedTimestamp(Timestamp modifiedTimestamp)
	{
		this.modifiedTimestamp = modifiedTimestamp;
	}
	*/

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
		question.addComment(this);
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public void setAnswer(Answer answer)
	{
		this.answer = answer;
		answer.addComment(this);
	}
}