create table if not exists public.dictionary
(
    uuid    uuid not null primary key,
    name    varchar(255),
    pattern varchar(255)
);

alter table public.dictionary
    owner to postgres;

create table if not exists public.word
(
    uuid            uuid not null primary key,
    dictionary_uuid uuid
        constraint fkhsi3gaxutfby1v7vsch8fenxa references public.dictionary,
    translation     varchar(255),
    word            varchar(255)
);

alter table public.word
    owner to postgres;


insert into public.dictionary(uuid, name, pattern)
values (gen_random_uuid(),
        'словарь 4 латинских букв',
        '^[a-zA-Z]{4}$'),
       (gen_random_uuid(),
        'словарь 5 цифр',
        '^[0-9]{5}$');