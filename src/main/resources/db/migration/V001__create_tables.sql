CREATE TABLE IF NOT EXISTS drink
(
    id               UUID PRIMARY KEY,
    name             VARCHAR(50),
    water_gr         INT,
    milk_gr          INT,
    coffee_bean_gr   INT
);

CREATE TABLE IF NOT EXISTS order_history
(
    id               UUID PRIMARY KEY,
    drink_id         UUID,
    FOREIGN KEY (drink_id) REFERENCES drink(id),
    timestamp        TIMESTAMP
);

CREATE TABLE IF NOT EXISTS coffee_machine_state
(
    id                  UUID PRIMARY KEY,
    factory_number      VARCHAR(50),
    no_water            BOOLEAN,
    no_milk             BOOLEAN,
    no_beans            BOOLEAN,
    cleaning_required   BOOLEAN,
    tray_full           BOOLEAN,
    repair_required     BOOLEAN
);

CREATE TABLE IF NOT EXISTS usage_counters
(
    id                      UUID PRIMARY KEY,
    coffmach_fact_numb_id   UUID,
    FOREIGN KEY (coffmach_fact_numb_id) REFERENCES coffee_machine_state(id),
    water_available_gr      INT,
    milk_available_gr       INT,
    beans_available_gr      INT,
    drink_made_counter      INT,
    liquid_in_tray_gr       INT
);