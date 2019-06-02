DELETE FROM menu_item;
DELETE FROM dish;
DELETE FROM users;
DELETE FROM restaurant;
DELETE FROM user_roles;
DELETE FROM vote_log;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurant (name)
VALUES ('Sweet bobaleh'),
       ('ITAKA'),
       ('Hunter Village');
-- 100002
INSERT INTO dish (name)
VALUES ('Soup'),
       ('French fries'),
       ('Hamburger'),
       ('Tea'),
       ('Coffee');
-- 100007
INSERT INTO menu_item (date, restaurant_id, dish_id, price)
VALUES (now(), 100000, 100003, 5.1),
       (now(), 100000, 100004, 10.2),
       (now(), 100000, 100005, 15.3),
       (now(), 100000, 100006, 2),
       (now(), 100000, 100007, 3),
       (now(), 100001, 100003, 5.4),
       (now(), 100001, 100004, 10.7),
       (now(), 100002, 100004, 10.2),
       (now(), 100002, 100005, 15.7),
       (now() + INTERVAL '1' day, 100000, 100005, 15.3),
       (now() + INTERVAL '1' day, 100000, 100006, 2),
       (now() + INTERVAL '1' day, 100000, 100007, 3),
       (now() + INTERVAL '2' day, 100000, 100005, 15.1),
       (now() + INTERVAL '2' day, 100000, 100006, 2.1),
       (now() + INTERVAL '2' day, 100000, 100007, 3.2);
-- 100022
INSERT INTO users (name, email, password)
VALUES ('Ivanov', 'ivanov@gmail.com', '{noop}1234567'),
       ('Petrov', 'petrov@gmail.com', '{noop}9876543'),
       ('Sidorov', 'sidirov@mail.ru', '{noop}12351514'),
       ('Davidov', 'davidov009@gmail.com', '{noop}1234567');
-- 100026
INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_ADMIN', 100023),
       ('ROLE_USER', 100024),
       ('ROLE_USER', 100025),
       ('ROLE_USER', 100026);

INSERT INTO vote_log(user_id, restaurant_id, date)
VALUES (100023, 100000, now()),
       (100024, 100001, now()),
       (100025, 100001, now());
-- 100029