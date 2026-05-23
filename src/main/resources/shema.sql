CREATE TABLE payments
(
    id         VARCHAR(255) PRIMARY KEY,
    payment_id VARCHAR(255),
    amount     DECIMAL,
    status     VARCHAR(50)
);