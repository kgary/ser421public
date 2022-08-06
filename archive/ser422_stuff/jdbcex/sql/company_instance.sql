--
-- PostgreSQL database dump
--

SET search_path = public, pg_catalog;

--
-- Data for TOC entry 2 (OID 26561)
-- Name: department; Type: TABLE DATA; Schema: public; Owner: Sam
--

INSERT INTO department VALUES ('Research', 5, 333445555, '0078-05-22');
INSERT INTO department VALUES ('Administration', 4, 987654321, '0085-01-01');
INSERT INTO department VALUES ('Headquarters', 1, 888665555, '0071-06-19');

--
-- Data for TOC entry 1 (OID 26559)
-- Name: employee; Type: TABLE DATA; Schema: public; Owner: Sam
--

INSERT INTO employee VALUES ('James', 'E', 'Borg', 888665555, '0027-11-10', '450 Stone, Houston, TX', 'M', 55000, NULL, 1);
INSERT INTO employee VALUES ('Jennifer', 'S', 'Wallace', 987654321, '0031-07-20', '291 Berry, Bellaire, TX', 'F', 43000, 888665555, 4);
INSERT INTO employee VALUES ('Franklin', 'T', 'Wong', 333445555, '0045-12-08', '638 Voss, Hoston, TX', 'M', 40000, 888665555, 5);
INSERT INTO employee VALUES ('John', 'B', 'Smith', 123456789, '0055-01-09', '731 Fondren, Houston, TX', 'M', 30000, 333445555, 5);
INSERT INTO employee VALUES ('Alicia', 'J', 'Zelaya', 999887777, '0058-07-19', '3321 Castle, Spring, TX', 'F', 25000, 987654321, 4);
INSERT INTO employee VALUES ('Ramesh', 'K', 'Narayan', 666884444, '0052-09-15', '975 Fire Oak, Humble, TX', 'M', 38000, 333445555, 5);
INSERT INTO employee VALUES ('Joyce', 'A', 'English', 453453453, '0062-07-31', '5631 Rice, Houston, TX', 'F', 25000, 333445555, 5);
INSERT INTO employee VALUES ('Ahmad', 'V', 'Jabbar', 987987987, '0059-03-29', '980 Dallas, Houston, TX', 'M', 25000, 987654321, 4);

--
-- Data for TOC entry 3 (OID 26563)
-- Name: dept_locations; Type: TABLE DATA; Schema: public; Owner: Sam
--

INSERT INTO dept_locations VALUES (1, 'Houston');
INSERT INTO dept_locations VALUES (4, 'Stafford');
INSERT INTO dept_locations VALUES (5, 'Bellaire');
INSERT INTO dept_locations VALUES (5, 'Sugarland');
INSERT INTO dept_locations VALUES (5, 'Houston');


--
-- Data for TOC entry 4 (OID 26565)
-- Name: project; Type: TABLE DATA; Schema: public; Owner: Sam
--

INSERT INTO project VALUES ('ProductX', 1, 'Bellaire', 5);
INSERT INTO project VALUES ('ProductY', 2, 'Sugarland', 5);
INSERT INTO project VALUES ('ProductZ', 3, 'Houston', 5);
INSERT INTO project VALUES ('Computerization', 10, 'Stafford', 4);
INSERT INTO project VALUES ('Reorganization', 20, 'Houston', 1);
INSERT INTO project VALUES ('Newbenefits', 30, 'Stafford', 4);


--
-- Data for TOC entry 5 (OID 26567)
-- Name: works_on; Type: TABLE DATA; Schema: public; Owner: Sam
--

INSERT INTO works_on VALUES (123456789, 1, 32.5);
INSERT INTO works_on VALUES (123456789, 2, 7.5);
INSERT INTO works_on VALUES (666884444, 3, 40);
INSERT INTO works_on VALUES (453453453, 1, 20);
INSERT INTO works_on VALUES (453453453, 2, 20);
INSERT INTO works_on VALUES (333445555, 2, 10);
INSERT INTO works_on VALUES (333445555, 3, 10);
INSERT INTO works_on VALUES (333445555, 10, 10);
INSERT INTO works_on VALUES (333445555, 20, 10);
INSERT INTO works_on VALUES (999887777, 30, 30);
INSERT INTO works_on VALUES (999887777, 10, 10);
INSERT INTO works_on VALUES (987987987, 10, 35);
INSERT INTO works_on VALUES (987987987, 30, 5);
INSERT INTO works_on VALUES (987654321, 30, 20);
INSERT INTO works_on VALUES (987654321, 20, 15);
INSERT INTO works_on VALUES (888665555, 20, 0);


--
-- Data for TOC entry 6 (OID 26569)
-- Name: dependent; Type: TABLE DATA; Schema: public; Owner: Sam
--

INSERT INTO dependent VALUES (333445555, 'Alice', 'F', '0076-04-05', 'DAUGHTER');
INSERT INTO dependent VALUES (333445555, 'Theodore', 'M', '0073-10-25', 'SON');
INSERT INTO dependent VALUES (333445555, 'Joy', 'F', '0048-05-03', 'SPOUSE');
INSERT INTO dependent VALUES (987654321, 'Abner', 'M', '0032-02-29', 'SPOUSE');
INSERT INTO dependent VALUES (123456789, 'Michael', 'M', '0078-01-01', 'SON');
INSERT INTO dependent VALUES (123456789, 'Alice', 'F', '0078-12-31', 'DAUGHTER');
INSERT INTO dependent VALUES (123456789, 'Elizabeth', 'F', '0057-05-05', 'SPOUSE');


