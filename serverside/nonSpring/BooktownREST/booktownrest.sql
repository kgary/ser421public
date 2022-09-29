SET client_encoding = 'SQL_ASCII';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: DATABASE booktown; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON DATABASE booktownrest IS 'The Book Town Database for SER422 REST example.';

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';
SET search_path = public, pg_catalog;


--
-- Name: author_ids; Type: SEQUENCE SET; Schema: public; Owner: kgary
--

SELECT pg_catalog.setval('author_ids', 25044, true);


--
-- Name: authors; Type: TABLE; Schema: public; Owner: kgary; Tablespace:
--

CREATE TABLE authors (
    id integer NOT NULL,
    last_name text,
    first_name text
);


--
-- Name: books; Type: TABLE; Schema: public; Owner: kgary; Tablespace:
--

CREATE TABLE books (
    id integer NOT NULL,
    title text NOT NULL,
    author_id integer,
    subject_id integer
);


--
-- Name: subjects; Type: TABLE; Schema: public; Owner: kgary; Tablespace:
--

CREATE TABLE subjects (
    id integer NOT NULL,
    subject text,
    "location" text
);


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
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: kgary
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
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: kgary
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
-- Name: authors_pkey; Type: CONSTRAINT; Schema: public; Owner: kgary; Tablespace:
--

ALTER TABLE ONLY authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);

--
-- Name: books_id_pkey; Type: CONSTRAINT; Schema: public; Owner: kgary; Tablespace:
--

ALTER TABLE ONLY books
    ADD CONSTRAINT books_id_pkey PRIMARY KEY (id);

--
-- Name: subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: kgary; Tablespace:
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);
