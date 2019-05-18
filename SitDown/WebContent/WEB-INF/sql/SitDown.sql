DROP table Storage;
DROP table Member;
DROP table Table;

create table Storage(
	Iname char(20) not null,
	Iprice int not null default 0,
	Iseller char(20),
	Icontact char(20),
	Iquant int default 0,
	Iorder int default 0,
	primary key(Iname)
);

create table Member(
	Mnum int,
	Mname char(20),
	Mcontact char(20),
	Mmileage int,
	Mlevel char(20),
	primary key(Mnum, Mname)
);

create table Otable(
	Tnum int,
	Tmenu char(20),
	Tprice int,
	Tquant int,
	primary key(Tnum, Tmenu)
);
