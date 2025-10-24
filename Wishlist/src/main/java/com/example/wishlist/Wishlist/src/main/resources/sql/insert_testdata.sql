USE wishlistdb;

INSERT INTO user (username, email)
VALUES ('Christoffer', 'christoffer@example.com'),
       ('Alan', 'Alan@example.com');

INSERT INTO wishlist (title, user_id)
VALUES ('Christoffers ønskeliste', 1),
       ('Alans ønskeliste', 2);

INSERT INTO wish (description, link, price, wishlist_id)
VALUES ('Nye hovedtelefoner', 'https://example.com/headphones', 899.95, 1),
       ('Java bog', 'https://example.com/java-book', 299.00, 1),
       ('Striktrøje', 'https://example.com/sweater', 499.00, 2);
