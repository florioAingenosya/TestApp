INSERT INTO user (user_id, email, user_name, password_hash, provider, created_by, created_date) VALUES
 ( 1, 'admin@mail.com', 'admin', '$2a$04$ItDBvcn41QncTbDL6Q2eNOHVQf5US/bdwQj/aczc2AGoGoEaX.ogi', 'local', 'system', now());

 INSERT INTO user (user_id, email, user_name, password_hash, provider, created_by, created_date) VALUES
 ( 2, 'user1@mail.com', 'user1','$2a$04$Qao0KqDim7VubPHV9x.oj.awaz0ArpPNlU7l0q18ZdsBXJn82dKPG', 'local', 'system', now());

INSERT INTO authority (name) VALUES
 ('ROLE_ADMIN'), ('ROLE_USER' );

INSERT INTO user_authority (user_id,authority_name) VALUES
 ( 1, 'ROLE_ADMIN'), (1, 'ROLE_USER'), (2, 'ROLE_USER');

INSERT INTO post (title, body, user_id, created_by, created_date, last_modified_by, last_modified_date) VALUES
 ( 'Test Vehicule 1'
 , '<p><img src="https://www.automobile-propre.com/wp-content/uploads/2018/04/nissan-imx-730x487.jpg" alt="undefined" style="float: none; height: 400px; width: 700px;"></p>'
 , '1', 'Florio ANDRIANARISON', now()-1, 'Florio ANDRIANARISON', now()-1);

INSERT INTO post (title, body, user_id, created_by, created_date, last_modified_by, last_modified_date) VALUES
 ( 'Test Vehicule 2'
 , '<p></p><img src="https://sf2.auto-moto.com/wp-content/uploads/sites/9/2020/03/home-renault-kadjar-750x410.jpg" alt="undefined" style="float: none; height: 400px; width: 700px;">'
 , '1', 'Florio ANDRIANARISON', now()-2, 'Florio ANDRIANARISON', now()-2);
