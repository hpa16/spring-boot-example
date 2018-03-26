INSERT INTO user (email, password, role, username) VALUES ("emailaddress1@random.com", "password", "USER", "name1");
INSERT INTO user (email, password, role, username) VALUES ("emailaddress2@random.com", "password", "USER", "name2");
INSERT INTO user (email, password, role, username) VALUES ("emailaddress3@random.com", "password", "USER", "name3");
INSERT INTO user (email, password, role, username) VALUES ("doesnotexist@anonymous.com", "password", "USER", "Anonymous");
INSERT INTO question (created, modified, text, user_id) VALUES ("2017-07-28", "2017-07-28", "This is Question 1", 1);
INSERT INTO question (created, modified, text, user_id) VALUES ("2017-07-28", "2017-07-28", "This is Question 2", 2);
INSERT INTO question (created, modified, text, user_id) VALUES ("2017-07-28", "2017-07-28", "This is Question 3", 1);
INSERT INTO answer (created, modified, question_id, text, user_id, accepted) VALUES ("2017-07-28", "2017-07-28", 1, "This is Answer 1", 2, 0);
INSERT INTO answer (created, modified, question_id, text, user_id, accepted) VALUES ("2017-07-28", "2017-07-28", 1, "This is Answer 2", 3, 0);
INSERT INTO comment (created, modified, text, type, user_id) VALUES ("2017-07-28", "2017-07-28", "This is Comment 1", 1, 1);
INSERT INTO comment_answer (answer_id, comment_id) VALUES (2, 1);
INSERT INTO comment (created, modified, text, type, user_id) VALUES ("2017-07-28", "2017-07-28", "This is Comment 2", 1, 3);
INSERT INTO comment_answer (answer_id, comment_id) VALUES (1, 2);
INSERT INTO tag (text) VALUES ("tag1");
INSERT INTO tag (text) VALUES ("tag2");
INSERT INTO tag (text) VALUES ("tag3");
INSERT INTO user_tag (user_id, tag_id) VALUES (1,1);
INSERT INTO user_tag (user_id, tag_id) VALUES (1,2);
INSERT INTO user_tag (user_id, tag_id) VALUES (1,3);
INSERT INTO user_tag (user_id, tag_id) VALUES (2,1);
INSERT INTO user_tag (user_id, tag_id) VALUES (2,2);
INSERT INTO user_tag (user_id, tag_id) VALUES (3,1);
INSERT INTO question_tag (question_id, tag_id) VALUES (1,1);
INSERT INTO question_tag (question_id, tag_id) VALUES (2,1);
INSERT INTO question_tag (question_id, tag_id) VALUES (2,2);
INSERT INTO question_tag (question_id, tag_id) VALUES (3,3);
INSERT INTO vote(type, user_id, answer_id) VALUES (1, 3, 1);
INSERT INTO vote(type, user_id, answer_id) VALUES (2, 1, 1);