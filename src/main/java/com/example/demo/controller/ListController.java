package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionTag;
import com.example.demo.model.Tag;
import com.example.demo.model.User;
import com.example.demo.model.UserTag;
import com.example.demo.model.View;

import com.example.demo.service.QuestionService;
import com.example.demo.service.TagService;
import com.example.demo.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class ListController
{
	@Autowired
	private SessionRegistry sessionRegistry;
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TagService tagService;
	@Autowired
	private ObjectMapper mapper;

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String viewUser(@PathVariable("userId") int user_id) throws JsonProcessingException
	{
		User user = userService.findUser(user_id);
		String result = mapper.writerWithView(View.UserTags.class).writeValueAsString(user);
		return result;
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String viewQuestion(@PathVariable("questionId") int question_id) throws JsonProcessingException
	{
		Question question = questionService.findQuestion(question_id);
		String result = mapper.writerWithView(View.QuestionComplete.class).writeValueAsString(question);
		return result;
	}

	@RequestMapping(value = "/tag/{text}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String viewTag(@PathVariable("text") String tag_text) throws JsonProcessingException
	{
		Tag tag = tagService.findByText(tag_text);
		String result = mapper.writerWithView(View.TagQuestions.class).writeValueAsString(tag);
		return result;
	}

	@RequestMapping(value = "/home/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String viewQuestionsForUserTags(@PathVariable("userId") int user_id) throws IOException
	{
		User user = userService.findUser(user_id);
		Set<UserTag> ut = user.getUserTags();
		final JsonNode root = mapper.createObjectNode();
		for (UserTag userTag : ut)
		{
			Set<QuestionTag> qt = userTag.getTag().getQuestionTags();
			for (QuestionTag questionTag : qt)
			{
				Question question = questionTag.getQuestion();
				String result = mapper.writerWithView(View.QuestionExtended.class).writeValueAsString(question);
				JsonNode child = mapper.readTree(result);
				//((ObjectNode) root).set(question.getCreatedDate().toString(), child);
				((ObjectNode) root).set(question.getId() + "", child);
			}
		}
		return root.toString();
	}

	@RequestMapping(value = "/listOnline", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getUsersFromSessionRegistry() throws IOException
	{
		List<String> userDetails = sessionRegistry.getAllPrincipals().stream().filter(
				u -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString).collect(
						Collectors.toList());
		JsonNode rootNode = mapper.createObjectNode();
		for (int i = 0; i < userDetails.size(); ++i)
		{
			User user = userService.findUserByEmail(userDetails.get(i).split("Username: ")[1].split(";")[0]);
			String temp = mapper.writerWithView(View.Base.class).writeValueAsString(user);
			JsonNode userNode = mapper.readTree(temp);
			((ObjectNode) rootNode).set("User " + (i + 1), userNode);
		}
		String json = rootNode.toString();
		return json;
	}
}