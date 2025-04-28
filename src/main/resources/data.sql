INSERT INTO users (first_name, last_name, password, email, is_active, premium)
VALUES ('Superadmin', 'Superadmin', '$2a$10$prFhH9HFQNoszrVPr8Zc8.4HpeKvyiyh1iC/D4zQC3zDqdFk9Qs..', 'superadmin@example.com', true, false);

INSERT INTO users (first_name, last_name, password, email, is_active, premium)
VALUES ('a', 'a', '$2a$10$mdimNYvEy1FCruLB5HGTSORnUttxkzM0zNs6O9RyhIWQSS4YHpY1m', 'a@a.a', true, false);

INSERT INTO projects(name, user_id, is_active)
VALUES ('asd', 2, true);

INSERT INTO projects(name, user_id, is_active)
VALUES ('asdd', 2, true);

-- INSERT INTO categories(name, color, project_id)
-- VALUES ('Comida', '#ff0000', 1)