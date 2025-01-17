CREATE TABLE employee (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    rating VARCHAR(1)
);

CREATE TABLE rating (
    category VARCHAR(1) PRIMARY KEY,
    standard_percentage INT
);