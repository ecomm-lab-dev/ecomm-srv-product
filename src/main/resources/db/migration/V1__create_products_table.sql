CREATE TABLE products (
    id             UNIQUEIDENTIFIER NOT NULL,
    name           NVARCHAR(255)    NOT NULL,
    description    NVARCHAR(1000)   NULL,
    sku            NVARCHAR(100)    NOT NULL,
    price          DECIMAL(19, 2)   NOT NULL,
    categoria      NVARCHAR(30)     NOT NULL,
    inclusion_date DATE             NOT NULL,
    is_active      BIT              NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id),
    CONSTRAINT uk_products_sku UNIQUE (sku),
    CONSTRAINT ck_products_price CHECK (price >= 0)
);
