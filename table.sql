CREATE TABLE menu(
    menu_no INT(8) PRIMARY KEY NOT NULL,
    menu_name VARCHAR(30) not null,
    price INT(8) not null,
    baseMoney INT(8) not null,
    orderCount INT(8) not null
);

CREATE TABLE storage(
    Ing_no INT(8) PRIMARY KEY NOT NULL,
    Ing_name VARCHAR(30) not null,
    Ing_count INT(8) not null,
    Ing_order INT(8) not null
);

CREATE TABLE cust(
    table_no INT(8) not null,
    menu_num INT(8) not null,
    order_count INT(8) not null,
    price INT(8) not null
);
