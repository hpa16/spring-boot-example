package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Answer;
import com.example.demo.model.Comment;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionTag;
import com.example.demo.model.Tag;
import com.example.demo.model.User;
import com.example.demo.model.UserTag;
import com.example.demo.model.Vote;
import com.example.demo.service.AnswerService;
import com.example.demo.service.CommentService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.QuestionTagService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTagService;
import com.example.demo.service.VoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CreateController
{
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private VoteService voteService;
	@Autowired
	private TagService tagService;
	@Autowired
	private UserTagService userTagService;
	@Autowired
	private QuestionTagService questionTagService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public @ResponseBody int createUser(@RequestBody User user)
	{
		userService.createUser(user);
		//securityService.autologin()
		return user.getId();
	}

	@RequestMapping(value = "/addTag", method = RequestMethod.POST)
	public @ResponseBody int addUserTag(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		String tag_text = jobject.get("tag_text").asText();
		int user_id = jobject.get("user_id").asInt();

		User user = userService.findUser(user_id);
		Tag tag = tagService.findByText(tag_text);

		UserTag userTag = new UserTag(user, tag);
		userTagService.createUserTag(userTag);

		return userTag.getId();
	}

	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public @ResponseBody int createQuestion(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		String text = jobject.get("text").asText();
		int user_id = jobject.get("user_id").asInt();
		String tag_text = jobject.get("tag_text").asText();

		User user = userService.findUser(user_id);
		Tag tag = tagService.findByText(tag_text);

		Question question = new Question(text, user);
		questionService.createQuestion(question);

		QuestionTag questionTag = new QuestionTag(question, tag);
		questionTagService.createQuestionTag(questionTag);

		return question.getId();
	}

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public @ResponseBody int createAnswer(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		String text = jobject.get("text").asText();
		int user_id = jobject.get("user_id").asInt();
		int question_id = jobject.get("question_id").asInt();

		User user = userService.findUser(user_id);
		Question question = questionService.findQuestion(question_id);

		Answer answer = new Answer(text, user, question);
		answerService.createAnswer(answer);

		return answer.getId();
	}

	@RequestMapping(value = "/commentQ", method = RequestMethod.POST)
	public @ResponseBody int createCommentForQuestion(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		String text = jobject.get("text").asText();
		int user_id = jobject.get("user_id").asInt();
		int question_id = jobject.get("question_id").asInt();

		User user = userService.findUser(user_id);
		Question question = questionService.findQuestion(question_id);

		Comment comment = new Comment(text, user, question);
		commentService.createComment(comment);

		return comment.getId();
	}

	@RequestMapping(value = "/commentA", method = RequestMethod.POST)
	public @ResponseBody int createCommentForAnswer(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		String text = jobject.get("text").asText();
		int user_id = jobject.get("user_id").asInt();
		int answer_id = jobject.get("answer_id").asInt();

		User user = userService.findUser(user_id);
		Answer answer = answerService.findAnswer(answer_id);

		Comment comment = new Comment(text, user, answer);
		commentService.createComment(comment);

		return comment.getId();
	}

	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public @ResponseBody String createVote(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		int type = jobject.get("type").asInt();
		int user_id = jobject.get("user_id").asInt();
		int answer_id = jobject.get("answer_id").asInt();

		User user = userService.findUser(user_id);
		Answer answer = answerService.findAnswer(answer_id);

		Vote vote = new Vote(type, user, answer);
		voteService.createVote(vote);

		if (type == 2)
			return "Downvote added";
		return "Upvote added";
	}

	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public @ResponseBody int createTag(@RequestBody String stringToParse) throws JsonProcessingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jobject = mapper.readTree(stringToParse);

		String text = jobject.get("text").asText();

		Tag tag = new Tag(text);
		tagService.createTag(tag);

		return tag.getId();
	}
}