CREATE TYPE DELIVERY_STATUS AS ENUM ('DISPATCHED', 'DELIVERED');

CREATE TABLE public.TB_PRODUCT(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100),
    QUANTITY_STOCK INTEGER,
    DESCRIPTION VARCHAR(255),
    DELIVERY_STATUS DELIVERY_STATUS,
    PACKAGE_ID BIGINT
);

CREATE TABLE public.TB_LABEL(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100)
);

CREATE TABLE public.TB_PRODUCTS_LABELS(
    PRODUCT_ID BIGINT,
    LABEL_ID BIGINT
);

CREATE TABLE delivery.TB_PACKAGE(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100)
);

CREATE SEQUENCE seq_tb_product_id
AS BIGINT
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE seq_tb_package_id
AS BIGINT
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE seq_tb_label_id
AS BIGINT
START WITH 1
INCREMENT BY 1;