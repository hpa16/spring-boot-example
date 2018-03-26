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
@Table(name = "user_tag", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "tag_id" }) })
public class UserTag
{
	@JsonView(View.Base.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_tag_id")
	private int id;

	@JsonView(View.UserTagUser.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonView(View.UserTagTag.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tag_id", nullable = false)
	private Tag tag;

	public UserTag()
	{
	}

	public UserTag(User user, Tag tag)
	{
		this.setUser(user);
		this.setTag(tag);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.getUser(), this.getTag());
	}

	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof UserTag))
			return false;

		final UserTag userTag = (UserTag) object;
		return Objects.equals(this.getUser(), userTag.getUser()) && Objects.equals(this.getTag(), userTag.getTag());
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
		tag.addUserTag(this);
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
		user.addUserTags(this);
	}

}
