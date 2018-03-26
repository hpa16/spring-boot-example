package com.example.demo.model;

public class View
{
	public interface Base {}
	
	public interface UserTags extends UserTagTag {}
	public interface UserQuestions extends Base {}
	public interface UserComplete extends UserTags, UserQuestions {}
	
	public interface QuestionExtended extends QuestionTagTag {}
	public interface QuestionComplete extends QuestionExtended, AnswerComplete{}
	
	public interface AnswerComplete extends CommentExtended, VoteExtended{}
	
	public interface CommentExtended extends Base {}
	public interface CommentComplete extends CommentExtended {}
	
	public interface VoteExtended extends Base {}
	public interface VoteComplete extends VoteExtended {}
	
	public interface TagQuestions extends QuestionTagQuestion {}
	public interface TagUsers extends UserTagUser {}
	
	public interface UserTagUser extends Base {}
	public interface UserTagTag extends Base {}
	public interface UserTagComplete extends UserTagUser, UserTagTag {}
	
	public interface QuestionTagQuestion extends Base {}
	public interface QuestionTagTag extends Base {}
	public interface QuestionTagComplete extends QuestionTagQuestion, QuestionTagTag {}
}
