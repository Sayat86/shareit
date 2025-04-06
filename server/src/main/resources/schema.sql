create table users
(
    id    serial primary key,
    name  varchar(255) not null,
    email varchar(255) not null
);

create table item_requests
(
    id serial primary key,
    description varchar(255) not null,
    requestor_id int references users (id) not null
);

create table items
(
    id          serial primary key,
    name        varchar(255)              not null,
    description varchar(1000)             not null,
    available   boolean                   not null,
    owner_id    int references users (id) not null,
    item_request_id int references item_requests (id)
);

create table bookings
(
    id         serial primary key,
    start_date timestamp without time zone not null,
    end_date   timestamp without time zone not null,
    item_id    int references items (id)   not null,
    booker_id  int references users (id)   not null,
    status     int                         not null
);

create table comments
(
    id        serial primary key,
    text      varchar(1000)               not null,
    item_id   int references items (id)   not null,
    author_id int references users (id)   not null,
    created   timestamp without time zone not null
);