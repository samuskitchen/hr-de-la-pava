CREATE SEQUENCE "seller_id_seq" INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647;

CREATE TABLE "seller"(
    id bigint NOT NULL DEFAULT nextval('seller_id_seq'::regclass),
    name character varying(250) NOT NULL,
    email character varying(100) NOT NULL,
    commission_percentage numeric(6,3) NOT NULL,
    avatar character varying(500) NOT NULL,
    current_commissions numeric(16,2) NOT NULL,
    CONSTRAINT seller_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE "sales_id_seq" INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647;

CREATE TABLE "sales"(
    id bigint NOT NULL DEFAULT nextval('sales_id_seq'::regclass),
    date timestamp without time zone NOT NULL,
    id_seller bigint NOT NULL,
    product integer NOT NULL,
    rode numeric(16,2) NOT NULL,
    CONSTRAINT sales_pkey PRIMARY KEY (id),
    CONSTRAINT fk_seller FOREIGN KEY (id_seller)
        REFERENCES "seller" (id) MATCH SIMPLE
        ON DELETE CASCADE
);