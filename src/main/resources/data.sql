INSERT INTO heroes(id, names, forces, gems)
VALUES (1, 'Arthemon', 7, 'diamond'),
       (2, 'Medeo', 8, 'uncut'),
       (3, 'Xena', 5, 'ruby');

INSERT INTO vehicles(id, names, speeds, values, hero_id )
VALUES (1, 'Horse', 7, 11, 1),
       (2, 'Dragon', 10, 20, 2),
       (3, 'Carriage', 12, 32, 3);

INSERT INTO weapons(id, names, knockbacks, usetimes, values)
VALUES (1, 'Sword', 3, 8, 3),
       (2, 'Repeater', 8, 6, 5),
       (3, 'Sentry Summon weapon', 12, 10, 11);

INSERT INTO weapon_hero(weapon_id, hero_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 1);