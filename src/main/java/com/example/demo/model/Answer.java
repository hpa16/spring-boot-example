package com.example.demo.model;

import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "answer", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "question_id" }) })
public class Answer
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private int id;

	// 1 : accepted , 0 : not accepted
	@JsonView(View.Base.class)
	@Column(name = "accepted")
	private int accepted;

	@JsonView(View.Base.class)
	@Column(name = "text")
	@NotEmpty(message = "Please enter your answer")
	private String text;

	@JsonView(View.Base.class)
	@Column(name = "created", updatable = false, nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@JsonView(View.Base.class)
	@Column(name = "modified")
	@UpdateTimestamp
	private Date modifiedDate;

	@JsonView(View.Base.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	@JsonView(View.AnswerComplete.class)
	@OrderBy("createdDate")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "answer", orphanRemoval = true)
	private Set<Comment> comments = new HashSet<Comment>();

	@JsonView(View.AnswerComplete.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "answer", orphanRemoval = true)
	private Set<Vote> votes = new HashSet<Vote>();

	public Answer()
	{
	}

	public Answer(String text, User user, Question question)
	{
		this.setAccepted(0);
		this.setText(text);
		this.setUser(user);
		this.setQuestion(question);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getUser(), this.getCreatedDate());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Answer))
			return false;

		final Answer answer = (Answer) object;
		return Objects.equals(this.getCreatedDate(),
				answer.getCreatedDate()) && Objects.equals(this.getUser(), answer.getUser());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getAccepted()
	{
		return accepted;
	}

	public void setAccepted(int accepted)
	{
		this.accepted = accepted;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
		user.addAnswer(this);
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
		question.addAnswer(this);
	}

	public Set<Comment> getComments()
	{
		return comments;
	}

	public void setComments(Set<Comment> comments)
	{
		this.comments = comments;
	}

	public void addComment(Comment comment)
	{
		this.comments.add(comment);
	}

	public Set<Vote> getVotes()
	{
		return votes;
	}

	public void setVotes(Set<Vote> votes)
	{
		this.votes = votes;
	}

	public void addVote(Vote vote)
	{
		this.votes.add(vote);
	}
}