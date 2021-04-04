create table url
(
    id          bigserial not null constraint url_pkprimary key,
    long_url    varchar(255),
    hash        varchar(100),
    created_at  timestamp with time zone default now(),
    created_by  varchar(255)             default 'ANONYMOUS'::character varying,
    modified_at timestamp with time zone default now(),
    modified_by varchar(255)             default 'ANONYMOUS'::character varying,
    uuid        varchar(100)
);

create unique index url_id_uindex
    on url (id);
