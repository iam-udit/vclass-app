
##########################    MIGRATION FOR VCLASS APPLICATION    ###########################

# Create the Virtual Class Room DB
CREATE DATABASE IF NOT EXISTS vclass;

# Use the Virtual Class Room DB
USE vclass;

# Create user table (Admin, Student, Instructor)
CREATE TABLE IF NOT EXISTS users (
	id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	age TINYINT UNSIGNED NOT NULL CHECK (age > 18),
	username VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

# Insert root admin details
INSERT INTO users(name, age, username, password) 
VALUES("Mr. Admin", 21, "admin@vclass.com", "Admin@123");

# Insert root staff details
INSERT INTO users(name, age, username, password) 
VALUES("Mr. Staff", 21, "staff@vclass.com", "Staff@123");

# Insert root student details
INSERT INTO users(name, age, username, password) 
VALUES("Mr. Student", 21, "student@vclass.com", "Student@123");


# Create role table 
CREATE TABLE IF NOT EXISTS roles (
	id TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(255) UNIQUE NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

# Insert all the role for V-Class app
INSERT INTO roles(name) VALUES("Admin");
INSERT INTO roles(name) VALUES("Staff");
INSERT INTO roles(name) VALUES("Student");

# Create user-role table
CREATE TABLE IF NOT EXISTS role_user (
	user_id BIGINT UNSIGNED UNIQUE NOT NULL,
	role_id TINYINT UNSIGNED NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

# Insert the role of admin
INSERT INTO role_user(user_id, role_id) VALUES(1, 1);
INSERT INTO role_user(user_id, role_id) VALUES(2, 2);
INSERT INTO role_user(user_id, role_id) VALUES(3, 3);

# Create address table
CREATE TABLE IF NOT EXISTS addresses (
	id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	user_id BIGINT UNSIGNED UNIQUE NOT NULL,
	city VARCHAR(255) NOT NULL,
	state VARCHAR(255) NOT NULL,
	country VARCHAR(255) NOT NULL,
	pin VARCHAR(25) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

# Insert address of the admin
INSERT INTO addresses(user_id, city, state, country, pin) 
VALUES (1, "Bhubaneswar", "Odisha", "India", "756124");
INSERT INTO addresses(user_id, city, state, country, pin) 
VALUES (2, "Mumbai", "Madhya Pradesh", "India", "434588");
INSERT INTO addresses(user_id, city, state, country, pin) 
VALUES (3, "Bengaluru", "Karnataka", "India", "684922");

# Create news table
CREATE TABLE IF NOT EXISTS news (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(255) UNIQUE NOT NULL,
	description TEXT NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

# Create the lessions table
CREATE TABLE IF NOT EXISTS lessions (
	id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) UNIQUE NOT NULL,
    user_id BIGINT UNSIGNED NOT NULL,
    slug VARCHAR(255) UNIQUE NOT NULL,
	description TEXT NOT NULL,
    video VARCHAR(255) NOT NULL,
    is_published BOOLEAN NOT NULL,
    is_approved BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);