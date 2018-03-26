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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "question")
public class Question
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private int id;

	@JsonView(View.Base.class)
	@Column(name = "text")
	@NotEmpty(message = "Please enter your question")
	private String text;

	@JsonView(View.Base.class)
	@Column(name = "created", updatable = false, nullable = false)
	@CreationTimestamp
	private Date createdDate;

	@JsonView(View.Base.class)
	@Column(name = "modified")
	@UpdateTimestamp
	private Date modifiedDate;

	@JsonView(View.QuestionExtended.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonView(View.QuestionExtended.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
	private Set<QuestionTag> questionTags = new HashSet<QuestionTag>();

	@JsonView(View.QuestionComplete.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
	private Set<Answer> answers = new HashSet<Answer>();

	@OrderBy("createdDate")
	@JsonView(View.QuestionComplete.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
	private Set<Comment> comments = new HashSet<Comment>();

	public Question()
	{
	}

	public Question(String text, User user)
	{
		this.setText(text);
		this.setUser(user);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getUser(), this.getCreatedDate());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Question))
			return false;

		final Question question = (Question) object;
		return Objects.equals(this.getCreatedDate(),
				question.getCreatedDate()) && Objects.equals(this.getUser(), question.getUser());
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
		user.addQuestion(this);
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

	public Set<QuestionTag> getQuestionTag()
	{
		return questionTags;
	}

	public void setQuestionTag(Set<QuestionTag> questionTags)
	{
		this.questionTags = questionTags;
	}

	public void addQuestionTag(QuestionTag questionTag)
	{
		this.questionTags.add(questionTag);
	}
}
