-- Database: items_system

-- DROP DATABASE items_system;

CREATE DATABASE items_system
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;
	   
	   
	   
-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id integer NOT NULL DEFAULT nextval('role_id_seq'::regclass),
  name character varying(2000),
  role_rights character varying(2000),
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role
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
  discription character varying(2000),
  category_id integer,
  resposible_user_id integer,
  attach_file boolean,
  state_of_item boolean,
  CONSTRAINT items_pkey PRIMARY KEY (id),
  CONSTRAINT items_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES public.item_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_resposible_user_id_fkey FOREIGN KEY (resposible_user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT items_sender_user_id_fkey FOREIGN KEY (sender_user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.items
  OWNER TO postgres;

  
  INSERT INTO public.items(
            id, sender_user_id, discription, category_id, resposible_user_id, 
            attach_file, state_of_item)
    VALUES (7, 'mouse dont mooving', 1, 2, false, false);

