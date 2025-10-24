CREATE DATABASE IF NOT EXISTS wishlistdb;
USE wishlistdb;

CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE wishlist (
                          wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(100) NOT NULL,
                          user_id INT,
                          FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE TABLE wish (
                      wish_id INT AUTO_INCREMENT PRIMARY KEY,
                      description VARCHAR(255) NOT NULL,
                      link VARCHAR(255),
                      price DECIMAL(10,2),
                      wishlist_id INT,
                      FOREIGN KEY (wishlist_id) REFERENCES wishlist(wishlist_id)
);
