create table user(
                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                     username VARCHAR(255),
                     password VARCHAR(255),
                     email VARCHAR(255),
                     createdAt DATETIME,
                     modifiedAt DATETIME
);

create table schedule(
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(255),
                         title VARCHAR(255),
                         contents VARCHAR(255),
                         createdAt DATETIME,
                         modifiedAt DATETIME,
                         user_id BIGINT,
                         FOREIGN KEY (user_id) references user(id)
);

create table comment(
                        id BIGINT primary key auto_increment,
                        contents VARCHAR(255),
                        user_id BIGINT,
                        schedule_id BIGINT,
                        createdAt DATETIME,
                        updatedAt DATETIME,
                        FOREIGN KEY (schedule_id) references schedule(id)
);
