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

create table categories(
    id bigint not null auto_increment,
    name varchar(255) not null,
    primary key(id)
) engine InnoDB

create table items(
    id bigint not null auto_increment,
    name varchar(255) not null,
    price bigint not null,
    category_id bigint not null,
    primary key(id),
    foreign key fk_items_categories(category_id) references categories (id)
) engine InnoDB

create table sub_categories(
    id bigint not null auto_increment,
    name varchar(255) not null,
    category_id bigint not null,
    primary key(id),
    foreign key fk_subcategories_categories(category_id) references categories (id)
) engine InnoDB