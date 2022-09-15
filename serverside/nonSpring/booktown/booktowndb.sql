--
-- PostgreSQL database dump
--

SET client_encoding = 'SQL_ASCII';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: DATABASE booktown; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON DATABASE booktown IS 'The Book Town Database.';


--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


SET search_path = public, pg_catalog;

--
-- Name: isbn_to_title(text); Type: FUNCTION; Schema: public; Owner: cst533
--

CREATE FUNCTION isbn_to_title(text) RETURNS text
    AS $_$SELECT title FROM books
                                 JOIN editions AS e (isbn, id)
                                 USING (id)
                                 WHERE isbn = $1$_$
    LANGUAGE sql;


--
-- Name: title(integer); Type: FUNCTION; Schema: public; Owner: cst533
--

CREATE FUNCTION title(integer) RETURNS text
    AS $_$SELECT title from books where id = $1$_$
    LANGUAGE sql;


--
-- Name: sum(text); Type: AGGREGATE; Schema: public; Owner: cst533
--

CREATE AGGREGATE sum (
    BASETYPE = text,
    SFUNC = textcat,
    STYPE = text,
    INITCOND = ''
);


SET default_tablespace = '';

SET default_with_oids = true;

--
-- Name: alternate_stock; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE alternate_stock (
    isbn text,
    cost numeric(5,2),
    retail numeric(5,2),
    stock integer
);


--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: cst533
--

CREATE SEQUENCE author_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    MINVALUE 30000
    CACHE 1;


--
-- Name: author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cst533
--

SELECT pg_catalog.setval('author_id_seq', 30006, true);


--
-- Name: author_ids; Type: SEQUENCE; Schema: public; Owner: cst533
--

CREATE SEQUENCE author_ids
    INCREMENT BY 1
    MAXVALUE 2147483647
    MINVALUE 0
    CACHE 1;


--
-- Name: author_ids; Type: SEQUENCE SET; Schema: public; Owner: cst533
--

SELECT pg_catalog.setval('author_ids', 25044, true);


--
-- Name: authors; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE authors (
    id integer DEFAULT nextval('author_id_seq'::text) NOT NULL,
    last_name text,
    first_name text
);


--
-- Name: book_backup; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE book_backup (
    id integer,
    title text,
    author_id integer,
    subject_id integer
);


--
-- Name: book_ids; Type: SEQUENCE; Schema: public; Owner: cst533
--

CREATE SEQUENCE book_ids
    INCREMENT BY 1
    MAXVALUE 2147483647
    MINVALUE 0
    CACHE 1;


--
-- Name: book_ids; Type: SEQUENCE SET; Schema: public; Owner: cst533
--

SELECT pg_catalog.setval('book_ids', 41478, true);


--
-- Name: book_queue; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE book_queue (
    title text NOT NULL,
    author_id integer,
    subject_id integer,
    approved boolean
);


--
-- Name: books; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE books (
    id integer NOT NULL,
    title text NOT NULL,
    author_id integer,
    subject_id integer
);


--
-- Name: customers; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE customers (
    id integer NOT NULL,
    last_name text,
    first_name text
);


--
-- Name: daily_inventory; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE daily_inventory (
    isbn text,
    is_stocked boolean
);


--
-- Name: distinguished_authors; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE distinguished_authors (
    award text
)
INHERITS (authors);


--
-- Name: editions; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE editions (
    isbn text NOT NULL,
    book_id integer,
    edition integer,
    publisher_id integer,
    publication date,
    "type" character(1),
    CONSTRAINT integrity CHECK (((book_id IS NOT NULL) AND (edition IS NOT NULL)))
);


--
-- Name: employees; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE employees (
    id integer NOT NULL,
    last_name text NOT NULL,
    first_name text,
    CONSTRAINT employees_id CHECK ((id > 100))
);


--
-- Name: favorite_authors; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE favorite_authors (
    employee_id integer,
    authors_and_titles text[]
);


--
-- Name: favorite_books; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE favorite_books (
    employee_id integer,
    books text[]
);


--
-- Name: money_example; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE money_example (
    money_cash money,
    numeric_cash numeric(6,2)
);


--
-- Name: my_list; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE my_list (
    todos text
);


--
-- Name: numeric_values; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE numeric_values (
    num numeric(30,6)
);


--
-- Name: publishers; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE publishers (
    id integer NOT NULL,
    name text,
    address text
);


--
-- Name: shipments; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE shipments (
    id integer DEFAULT nextval('"shipments_ship_id_seq"'::text) NOT NULL,
    customer_id integer,
    isbn text,
    ship_date timestamp with time zone
);


--
-- Name: recent_shipments; Type: VIEW; Schema: public; Owner: cst533
--

CREATE VIEW recent_shipments AS
    SELECT count(*) AS num_shipped, max(shipments.ship_date) AS max, b.title FROM ((shipments JOIN editions USING (isbn)) NATURAL JOIN books b(book_id)) GROUP BY b.title ORDER BY count(*) DESC;


--
-- Name: schedules; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE schedules (
    employee_id integer NOT NULL,
    schedule text
);


--
-- Name: shipments_ship_id_seq; Type: SEQUENCE; Schema: public; Owner: cst533
--

CREATE SEQUENCE shipments_ship_id_seq
    INCREMENT BY 1
    MAXVALUE 2147483647
    MINVALUE 0
    CACHE 1;


--
-- Name: shipments_ship_id_seq; Type: SEQUENCE SET; Schema: public; Owner: cst533
--

SELECT pg_catalog.setval('shipments_ship_id_seq', 1011, true);


--
-- Name: states; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE states (
    id integer NOT NULL,
    name text,
    abbreviation character(2)
);


--
-- Name: stock; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE stock (
    isbn text NOT NULL,
    cost numeric(5,2),
    retail numeric(5,2),
    stock integer
);


--
-- Name: stock_backup; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE stock_backup (
    isbn text,
    cost numeric(5,2),
    retail numeric(5,2),
    stock integer
);


--
-- Name: stock_view; Type: VIEW; Schema: public; Owner: cst533
--

CREATE VIEW stock_view AS
    SELECT stock.isbn, stock.retail, stock.stock FROM stock;


--
-- Name: subject_ids; Type: SEQUENCE; Schema: public; Owner: cst533
--

CREATE SEQUENCE subject_ids
    INCREMENT BY 1
    MAXVALUE 2147483647
    MINVALUE 0
    CACHE 1;


--
-- Name: subject_ids; Type: SEQUENCE SET; Schema: public; Owner: cst533
--

SELECT pg_catalog.setval('subject_ids', 15, true);


--
-- Name: subjects; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE subjects (
    id integer NOT NULL,
    subject text,
    "location" text
);


--
-- Name: text_sorting; Type: TABLE; Schema: public; Owner: cst533; Tablespace: 
--

CREATE TABLE text_sorting (
    letter character(1)
);


--
-- Data for Name: alternate_stock; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO alternate_stock VALUES ('0385121679', 29.00, 36.95, 65);
INSERT INTO alternate_stock VALUES ('039480001X', 30.00, 32.95, 31);
INSERT INTO alternate_stock VALUES ('0394900014', 23.00, 23.95, 0);
INSERT INTO alternate_stock VALUES ('044100590X', 36.00, 45.95, 89);
INSERT INTO alternate_stock VALUES ('0441172717', 17.00, 21.95, 77);
INSERT INTO alternate_stock VALUES ('0451160916', 24.00, 28.95, 22);
INSERT INTO alternate_stock VALUES ('0451198492', 36.00, 46.95, 0);
INSERT INTO alternate_stock VALUES ('0451457994', 17.00, 22.95, 0);
INSERT INTO alternate_stock VALUES ('0590445065', 23.00, 23.95, 10);
INSERT INTO alternate_stock VALUES ('0679803335', 20.00, 24.95, 18);
INSERT INTO alternate_stock VALUES ('0694003611', 25.00, 28.95, 50);
INSERT INTO alternate_stock VALUES ('0760720002', 18.00, 23.95, 28);
INSERT INTO alternate_stock VALUES ('0823015505', 26.00, 28.95, 16);
INSERT INTO alternate_stock VALUES ('0929605942', 19.00, 21.95, 25);
INSERT INTO alternate_stock VALUES ('1885418035', 23.00, 24.95, 77);
INSERT INTO alternate_stock VALUES ('0394800753', 16.00, 16.95, 4);


--
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO authors VALUES (1111, 'Denham', 'Ariel');
INSERT INTO authors VALUES (1212, 'Worsley', 'John');
INSERT INTO authors VALUES (15990, 'Bourgeois', 'Paulette');
INSERT INTO authors VALUES (25041, 'Bianco', 'Margery Williams');
INSERT INTO authors VALUES (16, 'Alcott', 'Louisa May');
INSERT INTO authors VALUES (4156, 'King', 'Stephen');
INSERT INTO authors VALUES (1866, 'Herbert', 'Frank');
INSERT INTO authors VALUES (1644, 'Hogarth', 'Burne');
INSERT INTO authors VALUES (2031, 'Brown', 'Margaret Wise');
INSERT INTO authors VALUES (115, 'Poe', 'Edgar Allen');
INSERT INTO authors VALUES (7805, 'Lutz', 'Mark');
INSERT INTO authors VALUES (7806, 'Christiansen', 'Tom');
INSERT INTO authors VALUES (1533, 'Brautigan', 'Richard');
INSERT INTO authors VALUES (1717, 'Brite', 'Poppy Z.');
INSERT INTO authors VALUES (2112, 'Gorey', 'Edward');
INSERT INTO authors VALUES (2001, 'Clarke', 'Arthur C.');
INSERT INTO authors VALUES (1213, 'Brookins', 'Andrew');


--
-- Data for Name: book_backup; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO book_backup VALUES (7808, 'The Shining', 4156, 9);
INSERT INTO book_backup VALUES (4513, 'Dune', 1866, 15);
INSERT INTO book_backup VALUES (4267, '2001: A Space Odyssey', 2001, 15);
INSERT INTO book_backup VALUES (1608, 'The Cat in the Hat', 1809, 2);
INSERT INTO book_backup VALUES (1590, 'Bartholomew and the Oobleck', 1809, 2);
INSERT INTO book_backup VALUES (25908, 'Franklin in the Dark', 15990, 2);
INSERT INTO book_backup VALUES (1501, 'Goodnight Moon', 2031, 2);
INSERT INTO book_backup VALUES (190, 'Little Women', 16, 6);
INSERT INTO book_backup VALUES (1234, 'The Velveteen Rabbit', 25041, 3);
INSERT INTO book_backup VALUES (2038, 'Dynamic Anatomy', 1644, 0);
INSERT INTO book_backup VALUES (156, 'The Tell-Tale Heart', 115, 9);
INSERT INTO book_backup VALUES (41472, 'Practical PostgreSQL', 1212, 4);
INSERT INTO book_backup VALUES (41473, 'Programming Python', 7805, 4);
INSERT INTO book_backup VALUES (41477, 'Learning Python', 7805, 4);
INSERT INTO book_backup VALUES (41478, 'Perl Cookbook', 7806, 4);
INSERT INTO book_backup VALUES (7808, 'The Shining', 4156, 9);
INSERT INTO book_backup VALUES (4513, 'Dune', 1866, 15);
INSERT INTO book_backup VALUES (4267, '2001: A Space Odyssey', 2001, 15);
INSERT INTO book_backup VALUES (1608, 'The Cat in the Hat', 1809, 2);
INSERT INTO book_backup VALUES (1590, 'Bartholomew and the Oobleck', 1809, 2);
INSERT INTO book_backup VALUES (25908, 'Franklin in the Dark', 15990, 2);
INSERT INTO book_backup VALUES (1501, 'Goodnight Moon', 2031, 2);
INSERT INTO book_backup VALUES (190, 'Little Women', 16, 6);
INSERT INTO book_backup VALUES (1234, 'The Velveteen Rabbit', 25041, 3);
INSERT INTO book_backup VALUES (2038, 'Dynamic Anatomy', 1644, 0);
INSERT INTO book_backup VALUES (156, 'The Tell-Tale Heart', 115, 9);
INSERT INTO book_backup VALUES (41473, 'Programming Python', 7805, 4);
INSERT INTO book_backup VALUES (41477, 'Learning Python', 7805, 4);
INSERT INTO book_backup VALUES (41478, 'Perl Cookbook', 7806, 4);
INSERT INTO book_backup VALUES (41472, 'Practical PostgreSQL', 1212, 4);


--
-- Data for Name: book_queue; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO book_queue VALUES ('Learning Python', 7805, 4, true);
INSERT INTO book_queue VALUES ('Perl Cookbook', 7806, 4, true);


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO books VALUES (7808, 'The Shining', 4156, 9);
INSERT INTO books VALUES (4513, 'Dune', 1866, 15);
INSERT INTO books VALUES (4267, '2001: A Space Odyssey', 2001, 15);
INSERT INTO books VALUES (1608, 'The Cat in the Hat', 1809, 2);
INSERT INTO books VALUES (1590, 'Bartholomew and the Oobleck', 1809, 2);
INSERT INTO books VALUES (25908, 'Franklin in the Dark', 15990, 2);
INSERT INTO books VALUES (1501, 'Goodnight Moon', 2031, 2);
INSERT INTO books VALUES (190, 'Little Women', 16, 6);
INSERT INTO books VALUES (1234, 'The Velveteen Rabbit', 25041, 3);
INSERT INTO books VALUES (2038, 'Dynamic Anatomy', 1644, 0);
INSERT INTO books VALUES (156, 'The Tell-Tale Heart', 115, 9);
INSERT INTO books VALUES (41473, 'Programming Python', 7805, 4);
INSERT INTO books VALUES (41477, 'Learning Python', 7805, 4);
INSERT INTO books VALUES (41478, 'Perl Cookbook', 7806, 4);
INSERT INTO books VALUES (41472, 'Practical PostgreSQL', 1212, 4);


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO customers VALUES (107, 'Jackson', 'Annie');
INSERT INTO customers VALUES (112, 'Gould', 'Ed');
INSERT INTO customers VALUES (142, 'Allen', 'Chad');
INSERT INTO customers VALUES (146, 'Williams', 'James');
INSERT INTO customers VALUES (172, 'Brown', 'Richard');
INSERT INTO customers VALUES (185, 'Morrill', 'Eric');
INSERT INTO customers VALUES (221, 'King', 'Jenny');
INSERT INTO customers VALUES (270, 'Bollman', 'Julie');
INSERT INTO customers VALUES (388, 'Morrill', 'Royce');
INSERT INTO customers VALUES (409, 'Holloway', 'Christine');
INSERT INTO customers VALUES (430, 'Black', 'Jean');
INSERT INTO customers VALUES (476, 'Clark', 'James');
INSERT INTO customers VALUES (480, 'Thomas', 'Rich');
INSERT INTO customers VALUES (488, 'Young', 'Trevor');
INSERT INTO customers VALUES (574, 'Bennett', 'Laura');
INSERT INTO customers VALUES (652, 'Anderson', 'Jonathan');
INSERT INTO customers VALUES (655, 'Olson', 'Dave');
INSERT INTO customers VALUES (671, 'Brown', 'Chuck');
INSERT INTO customers VALUES (723, 'Eisele', 'Don');
INSERT INTO customers VALUES (724, 'Holloway', 'Adam');
INSERT INTO customers VALUES (738, 'Gould', 'Shirley');
INSERT INTO customers VALUES (830, 'Robertson', 'Royce');
INSERT INTO customers VALUES (853, 'Black', 'Wendy');
INSERT INTO customers VALUES (860, 'Owens', 'Tim');
INSERT INTO customers VALUES (880, 'Robinson', 'Tammy');
INSERT INTO customers VALUES (898, 'Gerdes', 'Kate');
INSERT INTO customers VALUES (964, 'Gould', 'Ramon');
INSERT INTO customers VALUES (1045, 'Owens', 'Jean');
INSERT INTO customers VALUES (1125, 'Bollman', 'Owen');
INSERT INTO customers VALUES (1149, 'Becker', 'Owen');
INSERT INTO customers VALUES (1123, 'Corner', 'Kathy');


--
-- Data for Name: daily_inventory; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO daily_inventory VALUES ('039480001X', true);
INSERT INTO daily_inventory VALUES ('044100590X', true);
INSERT INTO daily_inventory VALUES ('0451198492', false);
INSERT INTO daily_inventory VALUES ('0394900014', false);
INSERT INTO daily_inventory VALUES ('0441172717', true);
INSERT INTO daily_inventory VALUES ('0451160916', false);
INSERT INTO daily_inventory VALUES ('0385121679', NULL);


--
-- Data for Name: distinguished_authors; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO distinguished_authors VALUES (25043, 'Simon', 'Neil', 'Pulitzer Prize');
INSERT INTO distinguished_authors VALUES (1809, 'Geisel', 'Theodor Seuss', 'Pulitzer Prize');


--
-- Data for Name: editions; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO editions VALUES ('039480001X', 1608, 1, 59, '1957-03-01', 'h');
INSERT INTO editions VALUES ('0451160916', 7808, 1, 75, '1981-08-01', 'p');
INSERT INTO editions VALUES ('0394800753', 1590, 1, 59, '1949-03-01', 'p');
INSERT INTO editions VALUES ('0590445065', 25908, 1, 150, '1987-03-01', 'p');
INSERT INTO editions VALUES ('0694003611', 1501, 1, 65, '1947-03-04', 'p');
INSERT INTO editions VALUES ('0679803335', 1234, 1, 102, '1922-01-01', 'p');
INSERT INTO editions VALUES ('0760720002', 190, 1, 91, '1868-01-01', 'p');
INSERT INTO editions VALUES ('0394900014', 1608, 1, 59, '1957-01-01', 'p');
INSERT INTO editions VALUES ('0385121679', 7808, 2, 75, '1993-10-01', 'h');
INSERT INTO editions VALUES ('1885418035', 156, 1, 163, '1995-03-28', 'p');
INSERT INTO editions VALUES ('0929605942', 156, 2, 171, '1998-12-01', 'p');
INSERT INTO editions VALUES ('0441172717', 4513, 2, 99, '1998-09-01', 'p');
INSERT INTO editions VALUES ('044100590X', 4513, 3, 99, '1999-10-01', 'h');
INSERT INTO editions VALUES ('0451457994', 4267, 3, 101, '2000-09-12', 'p');
INSERT INTO editions VALUES ('0451198492', 4267, 3, 101, '1999-10-01', 'h');
INSERT INTO editions VALUES ('0823015505', 2038, 1, 62, '1958-01-01', 'p');
INSERT INTO editions VALUES ('0596000855', 41473, 2, 113, '2001-03-01', 'p');


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO employees VALUES (101, 'Appel', 'Vincent');
INSERT INTO employees VALUES (102, 'Holloway', 'Michael');
INSERT INTO employees VALUES (105, 'Connoly', 'Sarah');
INSERT INTO employees VALUES (104, 'Noble', 'Ben');
INSERT INTO employees VALUES (103, 'Joble', 'David');
INSERT INTO employees VALUES (106, 'Hall', 'Timothy');
INSERT INTO employees VALUES (1008, 'Williams', NULL);


--
-- Data for Name: favorite_authors; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO favorite_authors VALUES (102, '{{"J.R.R. Tolkien","The Silmarillion"},{"Charles Dickens","Great Expectations"},{"Ariel Denham","Attic Lives"}}');


--
-- Data for Name: favorite_books; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO favorite_books VALUES (102, '{"The Hitchhiker''s Guide to the Galaxy","The Restauraunt at the End of the Universe"}');
INSERT INTO favorite_books VALUES (103, '{"There and Back Again: A Hobbit''s Holiday","Kittens Squared"}');


--
-- Data for Name: money_example; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO money_example VALUES ('$12.24', 12.24);


--
-- Data for Name: my_list; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO my_list VALUES ('Pick up laundry.');
INSERT INTO my_list VALUES ('Send out bills.');
INSERT INTO my_list VALUES ('Wrap up Grand Unifying Theory for publication.');


--
-- Data for Name: numeric_values; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO numeric_values VALUES (68719476736.000000);
INSERT INTO numeric_values VALUES (68719476737.000000);
INSERT INTO numeric_values VALUES (6871947673778.000000);
INSERT INTO numeric_values VALUES (999999999999999999999999.999900);
INSERT INTO numeric_values VALUES (999999999999999999999999.999999);
INSERT INTO numeric_values VALUES (-999999999999999999999999.999999);
INSERT INTO numeric_values VALUES (-100000000000000000000000.999999);
INSERT INTO numeric_values VALUES (1.999999);
INSERT INTO numeric_values VALUES (2.000000);
INSERT INTO numeric_values VALUES (2.000000);
INSERT INTO numeric_values VALUES (999999999999999999999999.999999);
INSERT INTO numeric_values VALUES (999999999999999999999999.000000);


--
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO publishers VALUES (150, 'Kids Can Press', 'Kids Can Press, 29 Birch Ave. Toronto, ON  M4V 1E2');
INSERT INTO publishers VALUES (91, 'Henry Holt & Company, Inc.', 'Henry Holt & Company, Inc. 115 West 18th Street New York, NY 10011');
INSERT INTO publishers VALUES (113, 'O''Reilly & Associates', 'O''Reilly & Associates, Inc. 101 Morris St, Sebastopol, CA 95472');
INSERT INTO publishers VALUES (62, 'Watson-Guptill Publications', '1515 Boradway, New York, NY 10036');
INSERT INTO publishers VALUES (105, 'Noonday Press', 'Farrar Straus & Giroux Inc, 19 Union Square W, New York, NY 10003');
INSERT INTO publishers VALUES (99, 'Ace Books', 'The Berkley Publishing Group, Penguin Putnam Inc, 375 Hudson St, New York, NY 10014');
INSERT INTO publishers VALUES (101, 'Roc', 'Penguin Putnam Inc, 375 Hudson St, New York, NY 10014');
INSERT INTO publishers VALUES (163, 'Mojo Press', 'Mojo Press, PO Box 1215, Dripping Springs, TX 78720');
INSERT INTO publishers VALUES (171, 'Books of Wonder', 'Books of Wonder, 16 W. 18th St. New York, NY, 10011');
INSERT INTO publishers VALUES (102, 'Penguin', 'Penguin Putnam Inc, 375 Hudson St, New York, NY 10014');
INSERT INTO publishers VALUES (75, 'Doubleday', 'Random House, Inc, 1540 Broadway, New York, NY 10036');
INSERT INTO publishers VALUES (65, 'HarperCollins', 'HarperCollins Publishers, 10 E 53rd St, New York, NY 10022');
INSERT INTO publishers VALUES (59, 'Random House', 'Random House, Inc, 1540 Broadway, New York, NY 10036');


--
-- Data for Name: schedules; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO schedules VALUES (102, 'Mon - Fri, 9am - 5pm');


--
-- Data for Name: shipments; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO shipments VALUES (375, 142, '039480001X', '2001-08-06 09:29:21-07');
INSERT INTO shipments VALUES (323, 671, '0451160916', '2001-08-14 10:36:41-07');
INSERT INTO shipments VALUES (998, 1045, '0590445065', '2001-08-12 12:09:47-07');
INSERT INTO shipments VALUES (749, 172, '0694003611', '2001-08-11 10:52:34-07');
INSERT INTO shipments VALUES (662, 655, '0679803335', '2001-08-09 07:30:07-07');
INSERT INTO shipments VALUES (806, 1125, '0760720002', '2001-08-05 09:34:04-07');
INSERT INTO shipments VALUES (102, 146, '0394900014', '2001-08-11 13:34:08-07');
INSERT INTO shipments VALUES (813, 112, '0385121679', '2001-08-08 09:53:46-07');
INSERT INTO shipments VALUES (652, 724, '1885418035', '2001-08-14 13:41:39-07');
INSERT INTO shipments VALUES (599, 430, '0929605942', '2001-08-10 08:29:42-07');
INSERT INTO shipments VALUES (969, 488, '0441172717', '2001-08-14 08:42:58-07');
INSERT INTO shipments VALUES (433, 898, '044100590X', '2001-08-12 08:46:35-07');
INSERT INTO shipments VALUES (660, 409, '0451457994', '2001-08-07 11:56:42-07');
INSERT INTO shipments VALUES (310, 738, '0451198492', '2001-08-15 14:02:01-07');
INSERT INTO shipments VALUES (510, 860, '0823015505', '2001-08-14 07:33:47-07');
INSERT INTO shipments VALUES (997, 185, '039480001X', '2001-08-10 13:47:52-07');
INSERT INTO shipments VALUES (999, 221, '0451160916', '2001-08-14 13:45:51-07');
INSERT INTO shipments VALUES (56, 880, '0590445065', '2001-08-14 13:49:00-07');
INSERT INTO shipments VALUES (72, 574, '0694003611', '2001-08-06 07:49:44-07');
INSERT INTO shipments VALUES (146, 270, '039480001X', '2001-08-13 09:42:10-07');
INSERT INTO shipments VALUES (981, 652, '0451160916', '2001-08-08 08:36:44-07');
INSERT INTO shipments VALUES (95, 480, '0590445065', '2001-08-10 07:29:52-07');
INSERT INTO shipments VALUES (593, 476, '0694003611', '2001-08-15 11:57:40-07');
INSERT INTO shipments VALUES (977, 853, '0679803335', '2001-08-09 09:30:46-07');
INSERT INTO shipments VALUES (117, 185, '0760720002', '2001-08-07 13:00:48-07');
INSERT INTO shipments VALUES (406, 1123, '0394900014', '2001-08-13 09:47:04-07');
INSERT INTO shipments VALUES (340, 1149, '0385121679', '2001-08-12 13:39:22-07');
INSERT INTO shipments VALUES (871, 388, '1885418035', '2001-08-07 11:31:57-07');
INSERT INTO shipments VALUES (1000, 221, '039480001X', '2001-09-14 16:46:32-07');
INSERT INTO shipments VALUES (1001, 107, '039480001X', '2001-09-14 17:42:22-07');
INSERT INTO shipments VALUES (754, 107, '0394800753', '2001-08-11 09:55:05-07');
INSERT INTO shipments VALUES (458, 107, '0394800753', '2001-08-07 10:58:36-07');
INSERT INTO shipments VALUES (189, 107, '0394800753', '2001-08-06 11:46:36-07');
INSERT INTO shipments VALUES (720, 107, '0394800753', '2001-08-08 10:46:13-07');
INSERT INTO shipments VALUES (1002, 107, '0394800753', '2001-09-22 11:23:28-07');
INSERT INTO shipments VALUES (2, 107, '0394800753', '2001-09-22 20:58:56-07');


--
-- Data for Name: states; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO states VALUES (42, 'Washington', 'WA');
INSERT INTO states VALUES (51, 'Oregon', 'OR');


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO stock VALUES ('0385121679', 29.00, 36.95, 65);
INSERT INTO stock VALUES ('039480001X', 30.00, 32.95, 31);
INSERT INTO stock VALUES ('0394900014', 23.00, 23.95, 0);
INSERT INTO stock VALUES ('044100590X', 36.00, 45.95, 89);
INSERT INTO stock VALUES ('0441172717', 17.00, 21.95, 77);
INSERT INTO stock VALUES ('0451160916', 24.00, 28.95, 22);
INSERT INTO stock VALUES ('0451198492', 36.00, 46.95, 0);
INSERT INTO stock VALUES ('0451457994', 17.00, 22.95, 0);
INSERT INTO stock VALUES ('0590445065', 23.00, 23.95, 10);
INSERT INTO stock VALUES ('0679803335', 20.00, 24.95, 18);
INSERT INTO stock VALUES ('0694003611', 25.00, 28.95, 50);
INSERT INTO stock VALUES ('0760720002', 18.00, 23.95, 28);
INSERT INTO stock VALUES ('0823015505', 26.00, 28.95, 16);
INSERT INTO stock VALUES ('0929605942', 19.00, 21.95, 25);
INSERT INTO stock VALUES ('1885418035', 23.00, 24.95, 77);
INSERT INTO stock VALUES ('0394800753', 16.00, 16.95, 4);


--
-- Data for Name: stock_backup; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO stock_backup VALUES ('0385121679', 29.00, 36.95, 65);
INSERT INTO stock_backup VALUES ('039480001X', 30.00, 32.95, 31);
INSERT INTO stock_backup VALUES ('0394800753', 16.00, 16.95, 0);
INSERT INTO stock_backup VALUES ('0394900014', 23.00, 23.95, 0);
INSERT INTO stock_backup VALUES ('044100590X', 36.00, 45.95, 89);
INSERT INTO stock_backup VALUES ('0441172717', 17.00, 21.95, 77);
INSERT INTO stock_backup VALUES ('0451160916', 24.00, 28.95, 22);
INSERT INTO stock_backup VALUES ('0451198492', 36.00, 46.95, 0);
INSERT INTO stock_backup VALUES ('0451457994', 17.00, 22.95, 0);
INSERT INTO stock_backup VALUES ('0590445065', 23.00, 23.95, 10);
INSERT INTO stock_backup VALUES ('0679803335', 20.00, 24.95, 18);
INSERT INTO stock_backup VALUES ('0694003611', 25.00, 28.95, 50);
INSERT INTO stock_backup VALUES ('0760720002', 18.00, 23.95, 28);
INSERT INTO stock_backup VALUES ('0823015505', 26.00, 28.95, 16);
INSERT INTO stock_backup VALUES ('0929605942', 19.00, 21.95, 25);
INSERT INTO stock_backup VALUES ('1885418035', 23.00, 24.95, 77);


--
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO subjects VALUES (0, 'Arts', 'Creativity St');
INSERT INTO subjects VALUES (1, 'Business', 'Productivity Ave');
INSERT INTO subjects VALUES (2, 'Children''s Books', 'Kids Ct');
INSERT INTO subjects VALUES (3, 'Classics', 'Academic Rd');
INSERT INTO subjects VALUES (4, 'Computers', 'Productivity Ave');
INSERT INTO subjects VALUES (5, 'Cooking', 'Creativity St');
INSERT INTO subjects VALUES (6, 'Drama', 'Main St');
INSERT INTO subjects VALUES (7, 'Entertainment', 'Main St');
INSERT INTO subjects VALUES (8, 'History', 'Academic Rd');
INSERT INTO subjects VALUES (9, 'Horror', 'Black Raven Dr');
INSERT INTO subjects VALUES (10, 'Mystery', 'Black Raven Dr');
INSERT INTO subjects VALUES (11, 'Poetry', 'Sunset Dr');
INSERT INTO subjects VALUES (12, 'Religion', NULL);
INSERT INTO subjects VALUES (13, 'Romance', 'Main St');
INSERT INTO subjects VALUES (14, 'Science', 'Productivity Ave');
INSERT INTO subjects VALUES (15, 'Science Fiction', 'Main St');


--
-- Data for Name: text_sorting; Type: TABLE DATA; Schema: public; Owner: cst533
--

INSERT INTO text_sorting VALUES ('0');
INSERT INTO text_sorting VALUES ('1');
INSERT INTO text_sorting VALUES ('2');
INSERT INTO text_sorting VALUES ('3');
INSERT INTO text_sorting VALUES ('A');
INSERT INTO text_sorting VALUES ('B');
INSERT INTO text_sorting VALUES ('C');
INSERT INTO text_sorting VALUES ('D');
INSERT INTO text_sorting VALUES ('a');
INSERT INTO text_sorting VALUES ('b');
INSERT INTO text_sorting VALUES ('c');
INSERT INTO text_sorting VALUES ('d');


--
-- Name: authors_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- Name: books_id_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_id_pkey PRIMARY KEY (id);


--
-- Name: customers_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: employees_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- Name: pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY editions
    ADD CONSTRAINT pkey PRIMARY KEY (isbn);


--
-- Name: publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (id);


--
-- Name: schedules_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY schedules
    ADD CONSTRAINT schedules_pkey PRIMARY KEY (employee_id);


--
-- Name: state_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY states
    ADD CONSTRAINT state_pkey PRIMARY KEY (id);


--
-- Name: stock_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (isbn);


--
-- Name: subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: cst533; Tablespace: 
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- Name: books_title_idx; Type: INDEX; Schema: public; Owner: cst533; Tablespace: 
--

CREATE INDEX books_title_idx ON books USING btree (title);


--
-- Name: shipments_ship_id_key; Type: INDEX; Schema: public; Owner: cst533; Tablespace: 
--

CREATE UNIQUE INDEX shipments_ship_id_key ON shipments USING btree (id);


--
-- Name: text_idx; Type: INDEX; Schema: public; Owner: cst533; Tablespace: 
--

CREATE INDEX text_idx ON text_sorting USING btree (letter);


--
-- Name: unique_publisher_idx; Type: INDEX; Schema: public; Owner: cst533; Tablespace: 
--

CREATE UNIQUE INDEX unique_publisher_idx ON publishers USING btree (name);


--
-- Name: sync_stock_with_editions; Type: RULE; Schema: public; Owner: cst533
--

CREATE RULE sync_stock_with_editions AS ON UPDATE TO editions DO UPDATE stock SET isbn = new.isbn WHERE (stock.isbn = old.isbn);


--
-- Name: RI_ConstraintTrigger_224086; Type: TRIGGER; Schema: public; Owner: cst533
--

CREATE CONSTRAINT TRIGGER valid_employee
    AFTER INSERT OR UPDATE ON schedules
    FROM employees
    NOT DEFERRABLE INITIALLY IMMEDIATE
    FOR EACH ROW
    EXECUTE PROCEDURE "RI_FKey_check_ins"('valid_employee', 'schedules', 'employees', 'FULL', 'employee_id', 'id');


--
-- Name: RI_ConstraintTrigger_224087; Type: TRIGGER; Schema: public; Owner: cst533
--

CREATE CONSTRAINT TRIGGER valid_employee
    AFTER DELETE ON employees
    FROM schedules
    NOT DEFERRABLE INITIALLY IMMEDIATE
    FOR EACH ROW
    EXECUTE PROCEDURE "RI_FKey_noaction_del"('valid_employee', 'schedules', 'employees', 'FULL', 'employee_id', 'id');


--
-- Name: RI_ConstraintTrigger_224088; Type: TRIGGER; Schema: public; Owner: cst533
--

CREATE CONSTRAINT TRIGGER valid_employee
    AFTER UPDATE ON employees
    FROM schedules
    NOT DEFERRABLE INITIALLY IMMEDIATE
    FOR EACH ROW
    EXECUTE PROCEDURE "RI_FKey_noaction_upd"('valid_employee', 'schedules', 'employees', 'FULL', 'employee_id', 'id');


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

