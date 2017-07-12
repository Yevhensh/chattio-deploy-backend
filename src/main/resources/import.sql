
INSERT INTO users (id_user, active, email, full_name, password) VALUES (1, true, 'yevhen@gmail.com', 'Yevhen Shabala', '$2a$10$OBMIi1jhDlfD7pJj7GP.6Oy2qgk8I0PCTeoc/8YhWKiYBxK4e3b2K');
INSERT INTO users (id_user, active, email, full_name, password) VALUES (2, true, 'testuser@gmail.com', 'Some Another', '$2a$10$OBMIi1jhDlfD7pJj7GP.6Oy2qgk8I0PCTeoc/8YhWKiYBxK4e3b2K');

INSERT INTO user_roles (user, role) VALUES (1, 0);
INSERT INTO user_roles (user, role) VALUES (2, 1);
INSERT INTO user_roles (user, role) VALUES (3, 1);

INSERT INTO dialogs (id_dialog) VALUES (1)
INSERT INTO dialogs (id_dialog) VALUES (2)
INSERT INTO dialogs (id_dialog) VALUES (3)

INSERT INTO dialogs_users VALUES (1, 1)
INSERT INTO dialogs_users VALUES (1, 2)

INSERT INTO messages (id_message, value, id_dialog, id_user, date_time) VALUES (1, 'hello', 1, 1, '2017-07-11 12:27:05')
INSERT INTO messages (id_message, value, id_dialog, id_user, date_time) VALUES (2, 'hey', 1, 2, '2017-07-11 12:50:05')