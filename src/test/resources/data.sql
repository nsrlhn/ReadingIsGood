INSERT INTO CUSTOMER (first_name, middle_name, family_name) VALUES ('Isim', '', 'Soyisim');

INSERT INTO BOOK (name, available_amount, price) VALUES ('Ince Mehmet', 1000, 10.5);
INSERT INTO BOOK (name, available_amount, price) VALUES ('Nutuk', 1000, 10.5);
INSERT INTO BOOK (name, available_amount, price) VALUES ('Dava', 1000, 10.5);

INSERT INTO ORDERS (date, status, customer_id) VALUES ('2023-01-25', 0, 1);
INSERT INTO ORDERS (date, status, customer_id) VALUES ('2024-01-25', 0, 1);

INSERT INTO ORDER_ITEM (amount, unit_price, book_id, order_id) VALUES (3, 5.99, 1, 1);
INSERT INTO ORDER_ITEM (amount, unit_price, book_id, order_id) VALUES (3, 9.99, 1, 1);
