create table grade (
    id  serial not null,
    date date,
    score int4 not null,
    grades_id int4, primary key (id)
);

create table class (
    id  serial not null,
    name varchar(255),
    specialization varchar(255),
    year int4 not null,
    primary key (id)
);

create table student (
    id  serial not null,
    name varchar(255),
    number varchar(255),
    students_id int4,
    primary key (id)
);

alter table grade add constraint FK1xyanf1fm52sq6bjr2e9j1s4m
    foreign key (grades_id) references student;
alter table student add constraint FKqgvj0nr3ibmqr91mi2w40o143
    foreign key (students_id) references class;
