
CREATE TABLE "public"."tb_address" (
    "city" character varying(100) NOT NULL,
    "complement" character varying(100) NOT NULL,
    "country" character varying(100) NOT NULL,
    "district" character varying(100) NOT NULL,
    "number" integer NOT NULL,
    "postal_code" character varying(15) NOT NULL,
    "state" character varying(100) NOT NULL,
    "street" character varying(100) NOT NULL,
    "customer_id" bigint NOT NULL,
    CONSTRAINT "tb_address_pkey" PRIMARY KEY ("customer_id")
) WITH (oids = false);


CREATE SEQUENCE tb_customer_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."tb_customer" (
    "id" bigint DEFAULT nextval('tb_customer_id_seq') NOT NULL,
    "birth_date" date NOT NULL,
    "cpf" character varying(15) NOT NULL,
    "email" character varying(100) NOT NULL,
    "name" character varying(100) NOT NULL,
    "phone" character varying(15) NOT NULL,
    CONSTRAINT "tb_customer_pkey" PRIMARY KEY ("id")
) WITH (oids = false);


ALTER TABLE ONLY "public"."tb_address" ADD CONSTRAINT "fk_address_customer" FOREIGN KEY (customer_id) REFERENCES tb_customer(id) NOT DEFERRABLE;
