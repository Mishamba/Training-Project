create database browser ;

use browser ;

create table users (
	username varchar(30) not null unique,
    pswd varchar(255) not null,
    `role` varchar(10) not null,
    primary key(username),
);

insert into users(username, pswd, `role`) values
('mishamba', '$2y$12$uHSPuuw3VbNgE2qYCHL1rOGZAR9wBZJanVKor1MFxXiZdazm9Zmsi', 'admin'),
('igor', '$2y$12$pkznSZtNDKmBEDsNVXTYO.LvBufts94NYjtx9RoaMv2yDjwKvWboS', 'user'),
('slava', '$2y$12$OXJkM3E7/wKPEwkalAbkK.ENidvg9jKcAjpnd20X4w/2CNBZG5gAi', 'user');