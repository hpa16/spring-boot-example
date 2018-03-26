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
@Table(name = "question_tag", uniqueConstraints = { @UniqueConstraint(columnNames = { "question_id", "tag_id" }) })
public class QuestionTag
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_tag_id")
	private int id;

	@JsonView(View.QuestionTagQuestion.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	@JsonView(View.QuestionTagTag.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tag_id", nullable = false)
	private Tag tag;

	public QuestionTag()
	{
	}

	public QuestionTag(Question question, Tag tag)
	{
		this.setQuestion(question);
		this.setTag(tag);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getQuestion(), this.getTag());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof QuestionTag))
			return false;

		final QuestionTag questionTag = (QuestionTag) object;
		return Objects.equals(this.getQuestion(), questionTag.getQuestion()) && Objects.equals(this.getTag(),
				questionTag.getTag());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Tag getTag()
	{
		return tag;
	}

	public void setTag(Tag tag)
	{
		this.tag = tag;
		tag.addQuestionTag(this);
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
		question.addQuestionTag(this);
	}
}
