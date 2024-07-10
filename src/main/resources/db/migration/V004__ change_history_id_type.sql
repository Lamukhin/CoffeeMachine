ALTER TABLE order_history ALTER COLUMN id TYPE INT;

CREATE TABLE IF NOT EXISTS drink_status (
    id               UUID PRIMARY KEY,
    drink_id         UUID,
    FOREIGN KEY (drink_id) REFERENCES drink(id),
    can_create       BOOLEAN,
    text_status   VARCHAR(200)
);

удалить ненужные колонки из таблцы сервиса для машины. в энтити уже потёр