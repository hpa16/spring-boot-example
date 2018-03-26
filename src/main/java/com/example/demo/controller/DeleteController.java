package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.service.AnswerService;
import com.example.demo.service.CommentService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.UserService;
import com.example.demo.service.VoteService;

@RestController
public class DeleteController
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

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable("userId") int user_id)
	{
		User user = userService.findUser(user_id);
		
		List<Comment> comments = commentService.findCommentsByUser(user);
		comments.forEach(comment -> commentService.deleteComment(comment.getId()));

		List<Vote> votes = voteService.findVotesByUser(user);
		votes.forEach(vote -> voteService.deleteVote(vote.getId()));

		userService.deleteUser(user_id);
		return ("User " + user_id + " Account Deleted");
	}

	@RequestMapping(value = "/question/{questionId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteQuestion(@PathVariable("questionId") int question_id)
	{
		questionService.deleteQuestion(question_id);
		return ("Question " + question_id + " Deleted");
	}

	@RequestMapping(value = "/answer/{answerId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteAnswer(@PathVariable("answerId") int answer_id)
	{
		answerService.deleteAnswer(answer_id);
		return ("Answer " + answer_id + " Deleted");
	}

	@RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteComment(@PathVariable("commentId") int comment_id)
	{
		commentService.deleteComment(comment_id);
		return ("Comment " + comment_id + " Deleted");
	}

	@RequestMapping(value = "/vote/{voteId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteVote(@PathVariable("voteId") int vote_id)
	{
		voteService.deleteVote(vote_id);
		return ("Vote " + vote_id + " Deleted");
	}
}