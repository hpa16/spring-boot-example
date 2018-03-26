package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "vote", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "answer_id" }) })
public class Vote
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vote_id")
	private int id;

	//type 1: upvote, type 2: downvote
	@JsonView(View.Base.class)
	@Column(name = "type", nullable = false)
	private int type;

	@JsonView(View.Base.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonView(View.VoteComplete.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "answer_id", nullable = false)
	private Answer answer;

	public Vote()
	{
	}

	public Vote(int type, User user, Answer answer)
	{
		super();
		this.setType(type);
		this.setUser(user);
		this.setAnswer(answer);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getUser(), this.getAnswer());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Vote))
			return false;

		final Vote vote = (Vote) object;
		return Objects.equals(this.getUser(), vote.getUser()) && Objects.equals(this.getAnswer(),
				vote.getAnswer());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public void setAnswer(Answer answer)
	{
		answer.addVote(this);
		this.answer = answer;
	}
}