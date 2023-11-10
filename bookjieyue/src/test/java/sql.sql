drop database if exists book;
create database if not exists book;
use book;
create table book
(
    id            int auto_increment
        primary key,
    title         varchar(50) null,
    author        varchar(50) null,
    publisher     varchar(50) null,
    borrow_count  int         null,
    borrow_status int         null
);

INSERT INTO book.book (title, author, publisher, borrow_count, borrow_status) VALUES ('JavaScript高级程序设计', 'Nicholas C.Zakas', '人民邮电出版社', 116, 0);
INSERT INTO book.book (title, author, publisher, borrow_count, borrow_status) VALUES ('JavaScript权威指南', 'David Flanagan', '人民邮电出版社', 92, 0);
INSERT INTO book.book (title, author, publisher, borrow_count, borrow_status) VALUES ('CSS揭秘', 'Lea Verou', '人民邮电出版社', 86, 0);
INSERT INTO book.book (title, author, publisher, borrow_count, borrow_status) VALUES ('HTML5与CSS3基础教程', '何新', '清华大学出版社', 31, 0);
INSERT INTO book.book (title, author, publisher, borrow_count, borrow_status) VALUES ('Vue.js实战', '梁灏', '人民邮电出版社', 24, 0);
create table borrow
(
    id          int auto_increment
        primary key,
    borrow_time datetime null,
    return_time datetime null,
    bid         int      null
);

INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-03 10:26:09', '2023-11-10 19:02:14', 1);
INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-03 10:42:34', '2023-11-03 10:43:22', 5);
INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-03 10:43:18', '2023-11-03 13:48:50', 3);
INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-03 10:45:12', '2023-11-03 10:45:53', 1);
INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-03 13:48:33', '2023-11-03 13:48:43', 1);
INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-10 19:03:39', '2023-11-10 19:04:23', 2);
INSERT INTO book.borrow (borrow_time, return_time, bid) VALUES ('2023-11-10 19:04:11', '2023-11-10 19:05:14', 1);
