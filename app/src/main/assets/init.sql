CREATE TABLE levels
(
    _id INTEGER PRIMARY KEY,
    num_level INTEGER NOT NULL,
    control INTEGER NOT NULL,
    posX INTEGER NOT NULL,
    posY INTEGER NOT NULL
);

INSERT INTO levels(num_level, control, posX, posY) VALUES (1, 0, 2, 3);

