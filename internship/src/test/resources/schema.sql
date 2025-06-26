create table if not exists risk_type
(
    id serial primary key,
    title character varying(50),
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
