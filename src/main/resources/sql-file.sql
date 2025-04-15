create table authors(
	id varchar(100) not null,
	name varchar(255) not null,
	email varchar(100) not null,
	constraint email_unique unique (email),
	primary key(id)
) engine = InnoDB;

create table biodata(
    id varchar(100) not null,
    name varchar(255) not null,
    primary key(id)
) engine InnoDB;

create table comments(
    id varchar(100) not null,
    biodata_id varchar(100) not null,
    comment varchar(255) not null,
    primary key(id),
    foreign key fk_biodata_comments (biodata_id) references biodata (id)
) engine InnoDB