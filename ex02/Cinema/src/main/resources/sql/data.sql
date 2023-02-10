INSERT INTO users (firstName, lastName, phoneNumber, email, password)
VALUES ('Alexandr', 'Pushkin', '88005353535', 'winter@yandex.ru', '$2a$10$C8doa07v8LMG5FaEBkmWRekibKatudakwH2wVwgWZkzeVjeE.y536');
INSERT INTO users (firstName, lastName, phoneNumber, email, password)
VALUES ('Mihail', 'Lermontov', '88005353532', 'luna@ya.ru', '$2a$10$C8doa07v8LMG5FaEBkmWRekibKatudakwH2wVwgWZkzeVjeE.y536');
INSERT INTO users (firstName, lastName, phoneNumber, email, password)
VALUES ('Fedor', 'Dostoevskiy', '88005353536', 'prestuplenie@mail.com', '$2a$10$C8doa07v8LMG5FaEBkmWRekibKatudakwH2wVwgWZkzeVjeE.y536');
INSERT INTO users (firstName, lastName, phoneNumber, email, password)
VALUES ('Mihail', 'Bulgakov', '88005353636', 'margarita@gmail.com', '$2a$10$C8doa07v8LMG5FaEBkmWRekibKatudakwH2wVwgWZkzeVjeE.y536');
INSERT INTO users (firstName, lastName, phoneNumber, email, password)
VALUES ('Sergey', 'Esenin', '88005353530', 'senya@yandex.ru', '$2a$10$C8doa07v8LMG5FaEBkmWRekibKatudakwH2wVwgWZkzeVjeE.y536');

INSERT INTO data (user_id, date, time, ip)
VALUES (1, 'December 31, 2021', '23:59', '127.0.0.1');
INSERT INTO data (user_id, date, time, ip)
VALUES (2, 'December 10, 2020', '05:00', '127.0.0.1');
INSERT INTO data (user_id, date, time, ip)
VALUES (2, 'December 9, 2020', '04:00', '127.0.0.1');
INSERT INTO data (user_id, date, time, ip)
VALUES (2, 'December 8, 2020', '03:00', '127.0.0.1');
INSERT INTO data (user_id, date, time, ip)
VALUES (2, 'December 5, 2020', '02:00', '127.0.0.1');

INSERT INTO images (user_id, filename, size, mime, uniqueName)
VALUES (1, 'photo.png', '2MB', 'image/png', '1.png');
INSERT INTO images (user_id, filename, size, mime, uniqueName)
VALUES (2, 'avatar.jpg', '196KB', 'image/jpg', '2.jpg');
INSERT INTO images (user_id, filename, size, mime, uniqueName)
VALUES (2, 'image.png', '1MB', 'image/png', '3.png');
INSERT INTO images (user_id, filename, size, mime, uniqueName)
VALUES (3, 'my_holidays.jpg', '196KB', 'image/jpg', '4.jpg');
INSERT INTO images (user_id, filename, size, mime, uniqueName)
VALUES (4, 'dubai.png', '1MB', 'image/png', '5.png');