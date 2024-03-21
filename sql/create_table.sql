-- T3H.`user` definition

CREATE TABLE `user`
(
    `user_id`   int(11) NOT NULL AUTO_INCREMENT,
    `email`     varchar(100) NOT NULL,
    `user_name` varchar(100) NOT NULL,
    `password`  varchar(200) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user_unique` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

INSERT INTO T3H.`user` (email, user_name, password)
VALUES ('a@gmail', 'a123a', '$2a$05$qhjpKfVh/gWIqiGXdfjPruAKrwZhbVtCcKbNmnG2FYQBBOwy6H6W.'),
       ('B@gmail', 'b123b', '$2a$05$qhjpKfVh/gWIqiGXdfjPruAKrwZhbVtCcKbNmnG2FYQBBOwy6H6W.');

-- T3H.`role` definition

CREATE TABLE `role`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

INSERT INTO T3H.`role` (name)
VALUES ('ADMIN'),
       ('CUSTOMER'),
       ('SUPER_ADMIN'),
       ('USER');

-- T3H.user_role definition

CREATE TABLE `user_role`
(
    `user_id` int(11) NOT NULL,
    `role`    varchar(20) NOT NULL,
    PRIMARY KEY (`user_id`, `role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO T3H.user_role (user_id, `role`)
VALUES (1, 'ADMIN'),
       (2, 'CUSTOMER');


