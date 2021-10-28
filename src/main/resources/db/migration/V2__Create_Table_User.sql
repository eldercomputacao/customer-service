CREATE SEQUENCE tb_user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."tb_user" (
    "id" bigint DEFAULT nextval('tb_user_id_seq') NOT NULL,
    "authorities" character varying(255),
    "name" character varying(255),
    "password" character varying(255),
    "username" character varying(255),
    CONSTRAINT "tb_user_pkey" PRIMARY KEY ("id")
) WITH (oids = false);