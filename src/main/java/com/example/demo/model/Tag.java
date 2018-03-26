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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "tag")
public class Tag
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tag_id")
	private int id;

	@JsonView(View.Base.class)
	@Column(name = "text", unique = true)
	@NotEmpty(message = "Please enter the tag")
	private String text;

	@JsonView(View.TagQuestions.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tag", orphanRemoval = true)
	private Set<QuestionTag> questionTags = new HashSet<QuestionTag>();

	@JsonView(View.TagUsers.class)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tag", orphanRemoval = true)
	private Set<UserTag> userTags = new HashSet<UserTag>();

	public Tag()
	{
	}

	public Tag(String text)
	{
		this.setText(text);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getText());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Tag))
			return false;

		final Tag tag = (Tag) object;
		return Objects.equals(this.getText(), tag.getText());
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

	public Set<QuestionTag> getQuestionTags()
	{
		return questionTags;
	}

	public void setQuestionTags(Set<QuestionTag> questionTags)
	{
		this.questionTags = questionTags;
	}

	public void addQuestionTag(QuestionTag questionTag)
	{
		this.questionTags.add(questionTag);
	}

	public Set<UserTag> getUserTags()
	{
		return userTags;
	}

	public void setUserTags(Set<UserTag> userTags)
	{
		this.userTags = userTags;
	}

	public void addUserTag(UserTag userTag)
	{
		this.userTags.add(userTag);
	}

}
