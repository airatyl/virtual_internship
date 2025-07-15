create table if not exists risk_type
(
    id serial primary key,
    title character varying(100),
    description character varying(100)

);
create table if not exists country
(
    id serial primary key,
    title character varying(50),
    dayPremium float
);
create table if not exists ageCoefficient
(
    id serial primary key,
    fromAge int,
    toAge int,
    coefficient  float
);
create table if not exists insuranceLimitCoefficient
(
    id serial primary key,
    insuranceLimitFrom int,
    insuranceLimitTo int,
    coefficient float
);

insert into risk_type (title)  values  ('Медицинские расходы'),
                                       ('Отмена поездки'),
                                       ('Утеря или кража багажа'),
                                       ('Ответственность перед третьими лицами'),
                                       ('Эвакуация и репатриация'),
                                       ('Активный отдых');
insert into country (title, dayPremium) VALUES ('Россия', 500),
                                               ('Латвия',1000),
                                               ('Литва',1200),
                                               ('Франция',1400),
                                               ('Китай',1600);

insert into ageCoefficient (fromAge, toAge, coefficient) VALUES (0,10,0.5),
                                                                (11,17,0.8),
                                                                (18,25,1.2),
                                                                (26,50,1.3),
                                                                (51,80,1.5);

insert into insuranceLimitCoefficient
    (insuranceLimitFrom, insuranceLimitTo, coefficient) VALUES (0,100000,1),
                                                               (100001,150000,1.5),
                                                               (150001,200000,2);




