-- Database: items_system

-- DROP DATABASE items_system;

CREATE DATABASE items_system
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

-- Table: public.attach_file

-- DROP TABLE public.attach_file;

CREATE TABLE public.attach_file
(
  id integer NOT NULL DEFAULT nextval('attach_file_id_seq'::regclass),
  file_existence character varying(200),
  CONSTRAINT attach_file_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.attach_file
  OWNER TO postgres;

  
-- Table: public.item_category

-- DROP TABLE public.item_category;

CREATE TABLE public.item_category
(
  id integer NOT NULL DEFAULT nextval('item_category_id_seq'::regclass),
  type_of_item character varying(2000),
  CONSTRAINT item_category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.item_category
  OWNER TO postgres;

  
  -- Table: public.item_comment

-- DROP TABLE public.item_comment;

CREATE TABLE public.item_comment
(
  id integer NOT NULL DEFAULT nextval('item_comment_id_seq'::regclass),
  comment character varying(2000),
  CONSTRAINT item_comment_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.item_comment
  OWNER TO postgres;

  
  -- Table: public.state_of_item

-- DROP TABLE public.state_of_item;

CREATE TABLE public.state_of_item
(
  id integer NOT NULL DEFAULT nextval('state_of_item_id_seq'::regclass),
  state character varying(200),
  CONSTRAINT state_of_item_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.state_of_item
  OWNER TO postgres;

  
  -- Table: public.role_rights

-- DROP TABLE public.role_rights;

CREATE TABLE public.role_rights
(
  id integer NOT NULL DEFAULT nextval('role_rights_int_seq'::regclass),
  rights character varying(2000),
  CONSTRAINT role_rights_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role_rights
  OWNER TO postgres;

  
  -- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id integer NOT NULL DEFAULT nextval('role_id_seq'::regclass),
  name character varying(2000),
  role_rights_id integer,
  CONSTRAINT role_pkey PRIMARY KEY (id),
  CONSTRAINT role_role_rights_id_fkey FOREIGN KEY (role_rights_id)
      REFERENCES public.role_rights (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role
  OWNER TO postgres;

  
  -- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
  id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  name character varying(2000),
  user_role_id integer,
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_user_role_id_fkey FOREIGN KEY (user_role_id)
      REFERENCES public.role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO postgres;

  
  -- Table: public.items

-- DROP TABLE public.items;

CREATE TABLE public.items
(
  id integer NOT NULL DEFAULT nextval('items_id_seq'::regclass),
  sender_user_id integer,
  item_comment_id character varying(2000),
  category_id integer,
  resposible_user_id integer,
  attach_file_id integer,
  state_of_item_id integer,
  CONSTRAINT items_pkey PRIMARY KEY (id),
  CONSTRAINT items_attach_file_id_fkey FOREIGN KEY (attach_file_id)
      REFERENCES public.attach_file (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES public.item_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_resposible_user_id_fkey FOREIGN KEY (resposible_user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_sender_user_id_fkey FOREIGN KEY (sender_user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_state_of_item_id_fkey FOREIGN KEY (state_of_item_id)
      REFERENCES public.state_of_item (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.items
  OWNER TO postgres;

  
  INSERT INTO public.items(
            id, sender_user_id, category_id, resposible_user_id, attach_file_id, 
            state_of_item_id, item_comment_id)
    VALUES (6, 1, 3, 1, 
            2, 1);
