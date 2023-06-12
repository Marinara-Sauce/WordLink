create table if not exists puzzles
(
    p_id     int auto_increment
    primary key,
    start    varchar(128) not null,
    target   varchar(128) not null,
    solution varchar(128) not null,
    date     date         not null,
    constraint puzzles_start_target_uindex
    unique (start, target)
    );

create table if not exists solves
(
    s_id      int auto_increment
    primary key,
    p_id      int      not null,
    timestamp datetime not null,
    num_steps int      null,
    constraint solves_puzzles_p_id_fk
    foreign key (p_id) references puzzles (p_id)
    );

