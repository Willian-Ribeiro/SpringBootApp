CREATE TYPE DELIVERY_STATUS AS ENUM ('DISPATCHED', 'DELIVERED');

CREATE TABLE delivery.TB_PACKAGE(
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(100)
);

CREATE TABLE public.TB_PRODUCT(
    ID BIGINT PRIMARY KEY, -- AUTO_INCREMENT
    NAME VARCHAR(100),
    QUANTITY_STOCK INTEGER,
    DESCRIPTION VARCHAR(255),
    DELIVERY_STATUS VARCHAR(20),
    PACKAGE_ID BIGINT
);

ALTER TABLE public.TB_PRODUCT
ADD CONSTRAINT fk_products_packages
FOREIGN KEY (PACKAGE_ID)
REFERENCES delivery.TB_PACKAGE(ID);

CREATE TABLE public.TB_LABEL(
    ID BIGINT PRIMARY KEY,
    NAME VARCHAR(100)
);

CREATE TABLE public.TB_PRODUCTS_LABELS(
    PRODUCT_ID BIGINT,
    LABEL_ID BIGINT
);

ALTER TABLE public.TB_PRODUCTS_LABELS
ADD CONSTRAINT fk_products_labels
FOREIGN KEY (PRODUCT_ID)
REFERENCES public.TB_PRODUCT(ID);

ALTER TABLE public.TB_PRODUCTS_LABELS
ADD CONSTRAINT fk_labels_products
FOREIGN KEY (LABEL_ID)
REFERENCES public.TB_LABEL(ID);

CREATE SEQUENCE seq_tb_product_id
AS BIGINT
START WITH 1
INCREMENT BY 1
OWNED BY public.TB_PRODUCT.ID;

CREATE SEQUENCE delivery.seq_tb_package_id
AS BIGINT
START WITH 1
INCREMENT BY 1
OWNED BY delivery.TB_PACKAGE.ID;

CREATE SEQUENCE seq_tb_label_id
AS BIGINT
START WITH 1
INCREMENT BY 1
OWNED BY public.TB_LABEL.ID;