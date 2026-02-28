CREATE TABLE order_type (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE order_veriety (
    id SERIAL PRIMARY KEY,
    veriety VARCHAR(50) NOT NULL
);

CREATE TABLE currency (
    id SERIAL PRIMARY KEY,
    currency_full VARCHAR(50),
    currency_short VARCHAR(10)
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    order_veriety_id INT REFERENCES order_veriety(id),
    order_type_id INT REFERENCES order_type(id),
    currency_id INT REFERENCES currency(id),
    tiker VARCHAR(10),
    count INT,
    type VARCHAR(50),
    number INT,
    data TIMESTAMP,
    duration VARCHAR(50)
);