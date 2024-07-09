ALTER TABLE order_history ALTER COLUMN id TYPE INT;

CREATE TABLE IF NOT EXISTS drink_status
(
    id               UUID PRIMARY KEY,
    drink_id         UUID,
    FOREIGN KEY (drink_id) REFERENCES drink(id),
    can_create       BOOLEAN,
    reason_if_cant   VARCHAR(200)
    );