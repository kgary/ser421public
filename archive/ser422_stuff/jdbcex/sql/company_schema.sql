-- This script was confirmed loadable into Apache Derby 10.13.1.1 January 5, 2017
--
-- PostgreSQL database dump
--

-- SET search_path = public, pg_catalog;

--
-- TOC entry 2 (OID 26559)
-- Name: employee; Type: TABLE; Schema: public; Owner: Sam
--

CREATE TABLE employee (
    fname character varying(30),
    minit character(1),
    lname character varying(30),
    ssn integer NOT NULL,
    bdate date,
    address character varying(50),
    sex character(1),
    salary double precision,
    superssn integer,
    dno integer
);


--
-- TOC entry 3 (OID 26561)
-- Name: department; Type: TABLE; Schema: public; Owner: Sam
--

CREATE TABLE department (
    dname character varying(30),
    dnumber integer NOT NULL,
    mgrssn integer,
    mgrstartdate date
);


--
-- TOC entry 4 (OID 26563)
-- Name: dept_locations; Type: TABLE; Schema: public; Owner: Sam
--

CREATE TABLE dept_locations (
    dnumber integer NOT NULL,
    dlocation character varying(30) NOT NULL
);


--
-- TOC entry 5 (OID 26565)
-- Name: project; Type: TABLE; Schema: public; Owner: Sam
--

CREATE TABLE project (
    pname character varying(30),
    pnumber integer NOT NULL,
    plocation character varying(30),
    dnum integer
);


--
-- TOC entry 6 (OID 26567)
-- Name: works_on; Type: TABLE; Schema: public; Owner: Sam
--

CREATE TABLE works_on (
    essn integer NOT NULL,
    pno integer NOT NULL,
    hours real
);


--
-- TOC entry 7 (OID 26569)
-- Name: dependent; Type: TABLE; Schema: public; Owner: Sam
--

CREATE TABLE dependent (
    essn integer NOT NULL,
    dependent_name character varying(30) NOT NULL,
    sex character(1),
    bdate date,
    relationship character varying(30)
);


--
-- TOC entry 9 (OID 26573)
-- Name: employee_ssn_U; Type: CONSTRAINT; Schema: public; Owner: Sam
--

--ALTER TABLE employee
--    ADD CONSTRAINT "employee_ssn_U" UNIQUE (ssn);

-- In derby a column can have either a unique constraint or a primary key constraint and not both. So i removed the unique key constraint.
ALTER TABLE employee
    ADD CONSTRAINT "employee_PK" PRIMARY KEY (ssn);
--
-- TOC entry 10 (OID 26575)
-- Name: department_dnumber_PK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE department
    ADD CONSTRAINT "department_dnumber_PK" PRIMARY KEY (dnumber);


--
-- TOC entry 17 (OID 26577)
-- Name: department_mgrssn_FK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

-- ALTER TABLE ONLY department
--     ADD CONSTRAINT "department_mgrssn_FK" FOREIGN KEY (mgrssn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 14 (OID 26581)
-- Name: dependent_essn_dname_PK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE dependent
    ADD CONSTRAINT "dependent_essn_dname_PK" PRIMARY KEY (essn, dependent_name);


--
-- TOC entry 21 (OID 26583)
-- Name: dependent_essn_FK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE dependent
    ADD CONSTRAINT "dependent_essn_FK" FOREIGN KEY (essn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 11 (OID 26587)
-- Name: dept_location_PK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE dept_locations
    ADD CONSTRAINT "dept_location_PK" PRIMARY KEY (dnumber, dlocation);


--
-- TOC entry 18 (OID 26589)
-- Name: dept_locations_FK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE dept_locations
    ADD CONSTRAINT "dept_locations_FK" FOREIGN KEY (dnumber) REFERENCES department(dnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 8 (OID 26593)
-- Name: employee_PK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

-- Added above instead of unique keey constraint.
--ALTER TABLE employee
--    ADD CONSTRAINT "employee_PK" PRIMARY KEY (ssn);*/


--
-- TOC entry 15 (OID 26595)
-- Name: employee_superssn_FK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE employee
    ADD CONSTRAINT "employee_superssn_FK" FOREIGN KEY (superssn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 16 (OID 26599)
-- Name: employee_dno_FK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE employee
    ADD CONSTRAINT "employee_dno_FK" FOREIGN KEY (dno) REFERENCES department(dnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 12 (OID 26603)
-- Name: project_PK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE project
    ADD CONSTRAINT "project_PK" PRIMARY KEY (pnumber);


--
-- TOC entry 13 (OID 26605)
-- Name: works_on_PK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE works_on
    ADD CONSTRAINT "works_on_PK" PRIMARY KEY (essn, pno);


--
-- TOC entry 19 (OID 26607)
-- Name: works_on_essn_FK; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE works_on
    ADD CONSTRAINT "works_on_essn_FK" FOREIGN KEY (essn) REFERENCES employee(ssn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 20 (OID 26611)
-- Name: works_on_pno; Type: CONSTRAINT; Schema: public; Owner: Sam
--

ALTER TABLE works_on
    ADD CONSTRAINT works_on_pno FOREIGN KEY (pno) REFERENCES project(pnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- Added by KG, didn't see this FK
ALTER TABLE project
	ADD CONSTRAINT project_dnum_fk FOREIGN KEY(dnum) REFERENCES department(dnumber) ON UPDATE RESTRICT ON DELETE RESTRICT;
