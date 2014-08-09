--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.9
-- Dumped by pg_dump version 9.1.9
-- Started on 2013-09-19 14:09:09 WIT

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 209 (class 3079 OID 11678)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2179 (class 0 OID 0)
-- Dependencies: 209
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 165 (class 1259 OID 137331)
-- Dependencies: 5
-- Name: address; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE address (
    id bigint NOT NULL,
    name character varying(80) NOT NULL,
    version integer,
    person bigint NOT NULL,
    village bigint NOT NULL
);


ALTER TABLE public.address OWNER TO miles;

--
-- TOC entry 190 (class 1259 OID 137662)
-- Dependencies: 5
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_id_seq OWNER TO miles;

--
-- TOC entry 166 (class 1259 OID 137336)
-- Dependencies: 5
-- Name: country; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE country (
    id bigint NOT NULL,
    code character varying(15) NOT NULL,
    name character varying(80) NOT NULL,
    version integer
);


ALTER TABLE public.country OWNER TO miles;

--
-- TOC entry 191 (class 1259 OID 137664)
-- Dependencies: 5
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO miles;

--
-- TOC entry 167 (class 1259 OID 137341)
-- Dependencies: 5
-- Name: district; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE district (
    id bigint NOT NULL,
    code character varying(15) NOT NULL,
    name character varying(80) NOT NULL,
    version integer,
    province bigint NOT NULL
);


ALTER TABLE public.district OWNER TO miles;

--
-- TOC entry 192 (class 1259 OID 137666)
-- Dependencies: 5
-- Name: district_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE district_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.district_id_seq OWNER TO miles;

--
-- TOC entry 161 (class 1259 OID 45170)
-- Dependencies: 5
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO miles;

--
-- TOC entry 168 (class 1259 OID 137346)
-- Dependencies: 5
-- Name: image; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE image (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    file oid,
    image_path character varying(256),
    modified timestamp without time zone,
    version integer
);


ALTER TABLE public.image OWNER TO miles;

--
-- TOC entry 193 (class 1259 OID 137668)
-- Dependencies: 5
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE image_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_id_seq OWNER TO miles;

--
-- TOC entry 169 (class 1259 OID 137351)
-- Dependencies: 5
-- Name: koordinator; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE koordinator (
    id bigint NOT NULL,
    active character varying(8) NOT NULL,
    version integer,
    login bigint NOT NULL,
    mitra bigint
);


ALTER TABLE public.koordinator OWNER TO miles;

--
-- TOC entry 194 (class 1259 OID 137670)
-- Dependencies: 5
-- Name: koordinator_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE koordinator_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.koordinator_id_seq OWNER TO miles;

--
-- TOC entry 170 (class 1259 OID 137356)
-- Dependencies: 5
-- Name: link; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE link (
    id bigint NOT NULL,
    link_path character varying(256) NOT NULL,
    version integer
);


ALTER TABLE public.link OWNER TO miles;

--
-- TOC entry 195 (class 1259 OID 137672)
-- Dependencies: 5
-- Name: link_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.link_id_seq OWNER TO miles;

--
-- TOC entry 171 (class 1259 OID 137361)
-- Dependencies: 5
-- Name: login; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login (
    id bigint NOT NULL,
    active character varying(8) NOT NULL,
    created timestamp without time zone NOT NULL,
    email character varying(80),
    employee_no character varying(40) NOT NULL,
    last_login timestamp without time zone,
    modified timestamp without time zone,
    name character varying(40) NOT NULL,
    password character varying(64) NOT NULL,
    status character varying(8) NOT NULL,
    username character varying(25) NOT NULL,
    version integer,
    login_role bigint,
    person bigint
);


ALTER TABLE public.login OWNER TO miles;

--
-- TOC entry 196 (class 1259 OID 137674)
-- Dependencies: 5
-- Name: login_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE login_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_id_seq OWNER TO miles;

--
-- TOC entry 172 (class 1259 OID 137366)
-- Dependencies: 5
-- Name: login_links; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login_links (
    login bigint NOT NULL,
    links bigint NOT NULL
);


ALTER TABLE public.login_links OWNER TO miles;

--
-- TOC entry 173 (class 1259 OID 137369)
-- Dependencies: 5
-- Name: login_login_roles; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login_login_roles (
    login bigint NOT NULL,
    login_roles bigint NOT NULL
);


ALTER TABLE public.login_login_roles OWNER TO miles;

--
-- TOC entry 174 (class 1259 OID 137372)
-- Dependencies: 5
-- Name: login_role; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login_role (
    id bigint NOT NULL,
    code character varying(40) NOT NULL,
    name character varying(40) NOT NULL,
    version integer
);


ALTER TABLE public.login_role OWNER TO miles;

--
-- TOC entry 197 (class 1259 OID 137676)
-- Dependencies: 5
-- Name: login_role_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE login_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_role_id_seq OWNER TO miles;

--
-- TOC entry 163 (class 1259 OID 99116)
-- Dependencies: 5
-- Name: login_role_logins; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login_role_logins (
    login_role bigint NOT NULL,
    logins bigint NOT NULL
);


ALTER TABLE public.login_role_logins OWNER TO miles;

--
-- TOC entry 164 (class 1259 OID 99121)
-- Dependencies: 5
-- Name: login_role_menus; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login_role_menus (
    login_roles bigint NOT NULL,
    menus bigint NOT NULL
);


ALTER TABLE public.login_role_menus OWNER TO miles;

--
-- TOC entry 175 (class 1259 OID 137377)
-- Dependencies: 5
-- Name: login_role_permissions; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE login_role_permissions (
    login_roles bigint NOT NULL,
    permissions bigint NOT NULL
);


ALTER TABLE public.login_role_permissions OWNER TO miles;

--
-- TOC entry 176 (class 1259 OID 137380)
-- Dependencies: 5
-- Name: menu; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE menu (
    id bigint NOT NULL,
    active boolean NOT NULL,
    name character varying(40) NOT NULL,
    sequence integer NOT NULL,
    version integer,
    link bigint,
    parent_menu bigint
);


ALTER TABLE public.menu OWNER TO miles;

--
-- TOC entry 198 (class 1259 OID 137678)
-- Dependencies: 5
-- Name: menu_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE menu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.menu_id_seq OWNER TO miles;

--
-- TOC entry 177 (class 1259 OID 137385)
-- Dependencies: 5
-- Name: menu_login_roles; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE menu_login_roles (
    menus bigint NOT NULL,
    login_roles bigint NOT NULL
);


ALTER TABLE public.menu_login_roles OWNER TO miles;

--
-- TOC entry 178 (class 1259 OID 137390)
-- Dependencies: 5
-- Name: mitra; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE mitra (
    id bigint NOT NULL,
    active character varying(8) NOT NULL,
    code character varying(20) NOT NULL,
    created timestamp without time zone NOT NULL,
    modified timestamp without time zone,
    name character varying(30) NOT NULL,
    version integer,
    creator bigint NOT NULL,
    modifier bigint
);


ALTER TABLE public.mitra OWNER TO miles;

--
-- TOC entry 199 (class 1259 OID 137680)
-- Dependencies: 5
-- Name: mitra_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE mitra_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mitra_id_seq OWNER TO miles;

--
-- TOC entry 179 (class 1259 OID 137395)
-- Dependencies: 5
-- Name: pelatih; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE pelatih (
    id bigint NOT NULL,
    active character varying(8) NOT NULL,
    address character varying(80),
    created timestamp without time zone NOT NULL,
    dob timestamp without time zone,
    gender character varying(1),
    modified timestamp without time zone,
    name character varying(50) NOT NULL,
    passport_no character varying(40) NOT NULL,
    place_of_birth character varying(30),
    status character varying(8) NOT NULL,
    version integer,
    creator bigint NOT NULL,
    koordinator bigint,
    login bigint NOT NULL,
    mitra bigint,
    modifier bigint,
    village bigint
);


ALTER TABLE public.pelatih OWNER TO miles;

--
-- TOC entry 200 (class 1259 OID 137682)
-- Dependencies: 5
-- Name: pelatih_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE pelatih_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pelatih_id_seq OWNER TO miles;

--
-- TOC entry 162 (class 1259 OID 85748)
-- Dependencies: 5
-- Name: pelatih_penerima_manfaat_list; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE pelatih_penerima_manfaat_list (
    pelatih bigint NOT NULL,
    penerima_manfaat_list bigint NOT NULL
);


ALTER TABLE public.pelatih_penerima_manfaat_list OWNER TO miles;

--
-- TOC entry 180 (class 1259 OID 137400)
-- Dependencies: 5
-- Name: pelatihan; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE pelatihan (
    id bigint NOT NULL,
    active character varying(8) NOT NULL,
    created timestamp without time zone NOT NULL,
    lokasi character varying(50) NOT NULL,
    modified timestamp without time zone,
    status character varying(8) NOT NULL,
    tanggal timestamp without time zone NOT NULL,
    type character varying(8) NOT NULL,
    version integer,
    creator bigint NOT NULL,
    mitra bigint NOT NULL,
    modifier bigint
);


ALTER TABLE public.pelatihan OWNER TO miles;

--
-- TOC entry 201 (class 1259 OID 137684)
-- Dependencies: 5
-- Name: pelatihan_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE pelatihan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pelatihan_id_seq OWNER TO miles;

--
-- TOC entry 181 (class 1259 OID 137405)
-- Dependencies: 5
-- Name: pelatihan_pelatih_list; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE pelatihan_pelatih_list (
    pelatihan_list bigint NOT NULL,
    pelatih_list bigint NOT NULL
);


ALTER TABLE public.pelatihan_pelatih_list OWNER TO miles;

--
-- TOC entry 182 (class 1259 OID 137408)
-- Dependencies: 5
-- Name: penerima_manfaat; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE penerima_manfaat (
    id bigint NOT NULL,
    active character varying(8) NOT NULL,
    certificate_no character varying(64) NOT NULL,
    created timestamp without time zone NOT NULL,
    dob timestamp without time zone NOT NULL,
    modified timestamp without time zone,
    name character varying(50) NOT NULL,
    notes character varying(256),
    relation character varying(8) NOT NULL,
    status character varying(8) NOT NULL,
    tanggal_berakhir timestamp without time zone NOT NULL,
    tanggal_kepesertaan timestamp without time zone NOT NULL,
    version integer,
    creator bigint NOT NULL,
    modifier bigint,
    pelatih bigint,
    peserta bigint
);


ALTER TABLE public.penerima_manfaat OWNER TO miles;

--
-- TOC entry 202 (class 1259 OID 137686)
-- Dependencies: 5
-- Name: penerima_manfaat_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE penerima_manfaat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.penerima_manfaat_id_seq OWNER TO miles;

--
-- TOC entry 183 (class 1259 OID 137413)
-- Dependencies: 5
-- Name: permission; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE permission (
    id bigint NOT NULL,
    code character varying(40) NOT NULL,
    name character varying(40) NOT NULL,
    version integer
);


ALTER TABLE public.permission OWNER TO miles;

--
-- TOC entry 203 (class 1259 OID 137688)
-- Dependencies: 5
-- Name: permission_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.permission_id_seq OWNER TO miles;

--
-- TOC entry 184 (class 1259 OID 137418)
-- Dependencies: 5
-- Name: person; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE person (
    id bigint NOT NULL,
    address character varying(80) NOT NULL,
    created timestamp without time zone NOT NULL,
    dob timestamp without time zone NOT NULL,
    gender character varying(1) NOT NULL,
    modified timestamp without time zone,
    mother_maiden character varying(40) NOT NULL,
    name1 character varying(20) NOT NULL,
    name2 character varying(20),
    name3 character varying(20),
    name4 character varying(20),
    npwp character varying(40) NOT NULL,
    passport_no character varying(40) NOT NULL,
    picture oid,
    place_of_birth character varying(20) NOT NULL,
    version integer,
    village bigint NOT NULL
);


ALTER TABLE public.person OWNER TO miles;

--
-- TOC entry 204 (class 1259 OID 137690)
-- Dependencies: 5
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO miles;

--
-- TOC entry 185 (class 1259 OID 137423)
-- Dependencies: 5
-- Name: peserta; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE peserta (
    id bigint NOT NULL,
    address character varying(80) NOT NULL,
    created timestamp without time zone NOT NULL,
    dob timestamp without time zone NOT NULL,
    gender character varying(1) NOT NULL,
    modified timestamp without time zone,
    name character varying(50) NOT NULL,
    passport_no character varying(40) NOT NULL,
    place_of_birth character varying(30) NOT NULL,
    active character varying(8) NOT NULL,
    education character varying(8),
    marital_status character varying(8),
    monthly_expense numeric(19,2),
    occupation character varying(8),
    passport_type character varying(8),
    phone_no character varying(25),
    status character varying(8) NOT NULL,
    version integer,
    village bigint NOT NULL,
    creator bigint NOT NULL,
    modifier bigint,
    pelatihan bigint
);


ALTER TABLE public.peserta OWNER TO miles;

--
-- TOC entry 205 (class 1259 OID 137692)
-- Dependencies: 5
-- Name: peserta_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE peserta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.peserta_id_seq OWNER TO miles;

--
-- TOC entry 186 (class 1259 OID 137428)
-- Dependencies: 5
-- Name: province; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE province (
    id bigint NOT NULL,
    code character varying(15) NOT NULL,
    name character varying(80) NOT NULL,
    version integer,
    country bigint NOT NULL
);


ALTER TABLE public.province OWNER TO miles;

--
-- TOC entry 206 (class 1259 OID 137694)
-- Dependencies: 5
-- Name: province_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE province_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.province_id_seq OWNER TO miles;

--
-- TOC entry 187 (class 1259 OID 137433)
-- Dependencies: 5
-- Name: sub_district; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE sub_district (
    id bigint NOT NULL,
    code character varying(15) NOT NULL,
    name character varying(80) NOT NULL,
    version integer,
    district bigint NOT NULL
);


ALTER TABLE public.sub_district OWNER TO miles;

--
-- TOC entry 207 (class 1259 OID 137696)
-- Dependencies: 5
-- Name: sub_district_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE sub_district_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sub_district_id_seq OWNER TO miles;

--
-- TOC entry 188 (class 1259 OID 137438)
-- Dependencies: 5
-- Name: system_lookup; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE system_lookup (
    id bigint NOT NULL,
    code character varying(8) NOT NULL,
    literal character varying(128) NOT NULL,
    type character varying(8) NOT NULL,
    version integer
);


ALTER TABLE public.system_lookup OWNER TO miles;

--
-- TOC entry 208 (class 1259 OID 137698)
-- Dependencies: 5
-- Name: system_lookup_id_seq; Type: SEQUENCE; Schema: public; Owner: miles
--

CREATE SEQUENCE system_lookup_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.system_lookup_id_seq OWNER TO miles;

--
-- TOC entry 189 (class 1259 OID 137443)
-- Dependencies: 5
-- Name: village; Type: TABLE; Schema: public; Owner: miles; Tablespace: 
--

CREATE TABLE village (
    id bigint NOT NULL,
    code character varying(15) NOT NULL,
    name character varying(80) NOT NULL,
    version integer,
    sub_district bigint NOT NULL
);


ALTER TABLE public.village OWNER TO miles;

--
-- TOC entry 2128 (class 0 OID 137331)
-- Dependencies: 165 2172
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2180 (class 0 OID 0)
-- Dependencies: 190
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('address_id_seq', 1, false);


--
-- TOC entry 2129 (class 0 OID 137336)
-- Dependencies: 166 2172
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO country VALUES (1, 'ID', 'Indonesia', 0);


--
-- TOC entry 2181 (class 0 OID 0)
-- Dependencies: 191
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('country_id_seq', 1, false);


--
-- TOC entry 2130 (class 0 OID 137341)
-- Dependencies: 167 2172
-- Data for Name: district; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO district VALUES (3573, '35.73.00.0000', 'KOTA MALANG', 0, 35);


--
-- TOC entry 2182 (class 0 OID 0)
-- Dependencies: 192
-- Name: district_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('district_id_seq', 1, false);


--
-- TOC entry 2183 (class 0 OID 0)
-- Dependencies: 161
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('hibernate_sequence', 1, true);


--
-- TOC entry 2131 (class 0 OID 137346)
-- Dependencies: 168 2172
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2184 (class 0 OID 0)
-- Dependencies: 193
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('image_id_seq', 1, false);


--
-- TOC entry 2132 (class 0 OID 137351)
-- Dependencies: 169 2172
-- Data for Name: koordinator; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2185 (class 0 OID 0)
-- Dependencies: 194
-- Name: koordinator_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('koordinator_id_seq', 1, true);


--
-- TOC entry 2133 (class 0 OID 137356)
-- Dependencies: 170 2172
-- Data for Name: link; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO link VALUES (1, '#', 0);
INSERT INTO link VALUES (2, 'main', 0);
INSERT INTO link VALUES (3, 'buat-data', 0);
INSERT INTO link VALUES (4, 'ubah-data', 0);
INSERT INTO link VALUES (5, '#', 0);
INSERT INTO link VALUES (6, 'laporan', 0);
INSERT INTO link VALUES (7, '#', 0);
INSERT INTO link VALUES (8, '#', 0);
INSERT INTO link VALUES (9, 'pengaturan-user', 0);
INSERT INTO link VALUES (10, 'parameter', 0);
INSERT INTO link VALUES (11, 'miles-dashboard', 0);
INSERT INTO link VALUES (12, 'admin-dashboard', 0);
INSERT INTO link VALUES (13, 'faq', 0);
INSERT INTO link VALUES (14, 'manual-penggunaan', 0);
INSERT INTO link VALUES (15, 'mitra', 0);
INSERT INTO link VALUES (16, 'pelatih', 0);
INSERT INTO link VALUES (17, 'pekerjaan', 0);
INSERT INTO link VALUES (18, 'pendidikan', 0);
INSERT INTO link VALUES (19, 'hubungan', 0);
INSERT INTO link VALUES (20, 'posisi', 0);
INSERT INTO link VALUES (21, 'pelatihan', 0);
INSERT INTO link VALUES (22, 'pelatihan', 0);
INSERT INTO link VALUES (23, 'peserta', 0);
INSERT INTO link VALUES (24, 'pelatih', 0);
INSERT INTO link VALUES (26, 'ubah-kata-sandi', 0);
INSERT INTO link VALUES (27, 'peserta', 27);
INSERT INTO link VALUES (28, 'pelatihan', 28);
INSERT INTO link VALUES (29, '#', 29);
INSERT INTO link VALUES (30, 'pelatih', 30);
INSERT INTO link VALUES (31, 'mitra', 31);
INSERT INTO link VALUES (32, 'koordinator', 32);


--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 195
-- Name: link_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('link_id_seq', 1, false);


--
-- TOC entry 2134 (class 0 OID 137361)
-- Dependencies: 171 2172
-- Data for Name: login; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO login VALUES (1, 'A', '2013-06-05 10:47:11', 'email@email.com', '11111', NULL, NULL, 'Manager', '866485796cfa8d7c0cf7111640205b83076433547577511d81f8030ae99ecea5', 'A', 'manager', 0, 1, NULL);
INSERT INTO login VALUES (2, 'A', '2013-06-05 10:47:11', 'email@email.com', '11112', NULL, NULL, 'MLO', '2cefe42a5fd0a01aaa3c4366a9b4d573f59625039ead461ad22cae97f7a8b391', 'A', 'mlo', 0, 2, NULL);
INSERT INTO login VALUES (3, 'A', '2013-06-05 10:47:11', 'email@email.com', '11113', NULL, NULL, 'Koordinator Pelatih', '13791dda713ceaab1583cdfddfbc2d3c3671475e73f404443bd9ad922fdd6cb4', 'A', 'cpelatih', 0, 3, NULL);
INSERT INTO login VALUES (5, 'A', '2013-06-05 10:47:12', 'email@email.com', '11115', NULL, NULL, 'Prudential', '9ed2ef77d44620c3f094698138ec43656318c90dad66512aa250271cdb733f99', 'A', 'prudential', 0, 5, NULL);
INSERT INTO login VALUES (6, 'A', '2013-06-05 10:47:12', 'email@email.com', '11116', NULL, NULL, 'Pelatih', '90da9876474dc0f3783baa0d3bc79fcfbb660dee7bdbfdfac193a07e813a2d33', 'A', 'pelatih', 0, 6, NULL);


--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 196
-- Name: login_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('login_id_seq', 1, true);


--
-- TOC entry 2135 (class 0 OID 137366)
-- Dependencies: 172 2172
-- Data for Name: login_links; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2136 (class 0 OID 137369)
-- Dependencies: 173 2172
-- Data for Name: login_login_roles; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2137 (class 0 OID 137372)
-- Dependencies: 174 2172
-- Data for Name: login_role; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO login_role VALUES (1, 'ROLE_PRG_MGR', 'Program Manager', 0);
INSERT INTO login_role VALUES (2, 'ROLE_MLO', 'MLO', 0);
INSERT INTO login_role VALUES (3, 'ROLE_CORD_PLTH', 'Koordinator Pelatih', 0);
INSERT INTO login_role VALUES (5, 'ROLE_PRDNTL', 'Prudential', 0);
INSERT INTO login_role VALUES (6, 'ROLE_PELATIH', 'Pelatih', 0);


--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 197
-- Name: login_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('login_role_id_seq', 1, false);


--
-- TOC entry 2126 (class 0 OID 99116)
-- Dependencies: 163 2172
-- Data for Name: login_role_logins; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2127 (class 0 OID 99121)
-- Dependencies: 164 2172
-- Data for Name: login_role_menus; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2138 (class 0 OID 137377)
-- Dependencies: 175 2172
-- Data for Name: login_role_permissions; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO login_role_permissions VALUES (1, 1);
INSERT INTO login_role_permissions VALUES (1, 2);
INSERT INTO login_role_permissions VALUES (1, 3);
INSERT INTO login_role_permissions VALUES (1, 4);
INSERT INTO login_role_permissions VALUES (1, 5);
INSERT INTO login_role_permissions VALUES (1, 6);
INSERT INTO login_role_permissions VALUES (1, 7);
INSERT INTO login_role_permissions VALUES (1, 8);
INSERT INTO login_role_permissions VALUES (1, 9);
INSERT INTO login_role_permissions VALUES (1, 10);
INSERT INTO login_role_permissions VALUES (1, 11);
INSERT INTO login_role_permissions VALUES (1, 12);
INSERT INTO login_role_permissions VALUES (1, 13);
INSERT INTO login_role_permissions VALUES (1, 14);
INSERT INTO login_role_permissions VALUES (1, 15);
INSERT INTO login_role_permissions VALUES (1, 16);
INSERT INTO login_role_permissions VALUES (1, 17);
INSERT INTO login_role_permissions VALUES (1, 18);
INSERT INTO login_role_permissions VALUES (1, 19);
INSERT INTO login_role_permissions VALUES (1, 20);
INSERT INTO login_role_permissions VALUES (1, 21);
INSERT INTO login_role_permissions VALUES (1, 22);
INSERT INTO login_role_permissions VALUES (1, 23);
INSERT INTO login_role_permissions VALUES (1, 24);
INSERT INTO login_role_permissions VALUES (1, 25);
INSERT INTO login_role_permissions VALUES (1, 26);
INSERT INTO login_role_permissions VALUES (1, 27);
INSERT INTO login_role_permissions VALUES (1, 28);
INSERT INTO login_role_permissions VALUES (1, 29);
INSERT INTO login_role_permissions VALUES (1, 30);
INSERT INTO login_role_permissions VALUES (1, 31);
INSERT INTO login_role_permissions VALUES (1, 32);
INSERT INTO login_role_permissions VALUES (1, 33);
INSERT INTO login_role_permissions VALUES (1, 34);
INSERT INTO login_role_permissions VALUES (1, 35);
INSERT INTO login_role_permissions VALUES (1, 36);
INSERT INTO login_role_permissions VALUES (1, 37);
INSERT INTO login_role_permissions VALUES (1, 38);
INSERT INTO login_role_permissions VALUES (1, 39);
INSERT INTO login_role_permissions VALUES (1, 40);
INSERT INTO login_role_permissions VALUES (1, 41);
INSERT INTO login_role_permissions VALUES (1, 42);
INSERT INTO login_role_permissions VALUES (3, 9);
INSERT INTO login_role_permissions VALUES (3, 10);
INSERT INTO login_role_permissions VALUES (3, 11);
INSERT INTO login_role_permissions VALUES (3, 15);
INSERT INTO login_role_permissions VALUES (3, 13);
INSERT INTO login_role_permissions VALUES (3, 17);
INSERT INTO login_role_permissions VALUES (3, 19);
INSERT INTO login_role_permissions VALUES (6, 11);
INSERT INTO login_role_permissions VALUES (6, 12);
INSERT INTO login_role_permissions VALUES (6, 13);
INSERT INTO login_role_permissions VALUES (6, 14);
INSERT INTO login_role_permissions VALUES (6, 15);
INSERT INTO login_role_permissions VALUES (6, 16);
INSERT INTO login_role_permissions VALUES (6, 17);
INSERT INTO login_role_permissions VALUES (6, 18);
INSERT INTO login_role_permissions VALUES (6, 5);
INSERT INTO login_role_permissions VALUES (6, 6);
INSERT INTO login_role_permissions VALUES (6, 7);
INSERT INTO login_role_permissions VALUES (6, 8);
INSERT INTO login_role_permissions VALUES (5, 38);
INSERT INTO login_role_permissions VALUES (5, 39);


--
-- TOC entry 2139 (class 0 OID 137380)
-- Dependencies: 176 2172
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO menu VALUES (1, true, 'Root', 1, 0, 1, NULL);
INSERT INTO menu VALUES (2, true, 'Beranda', 1, 0, 2, 1);
INSERT INTO menu VALUES (3, false, 'Buat Data', 6, 0, 3, 1);
INSERT INTO menu VALUES (4, false, 'Ubah Data', 7, 0, 4, 1);
INSERT INTO menu VALUES (5, true, 'Pengaturan', 8, 0, 5, 1);
INSERT INTO menu VALUES (6, true, 'Laporan', 9, 0, 6, 1);
INSERT INTO menu VALUES (7, true, 'Dashboard', 10, 0, 7, 1);
INSERT INTO menu VALUES (8, true, 'Bantuan', 11, 0, 8, 1);
INSERT INTO menu VALUES (9, true, 'Pengaturan User', 1, 0, 9, 5);
INSERT INTO menu VALUES (10, true, 'Parameter', 2, 0, 10, 5);
INSERT INTO menu VALUES (11, true, 'MILES Dashboard', 1, 0, 11, 7);
INSERT INTO menu VALUES (12, true, 'Admin Dashboard', 2, 0, 12, 7);
INSERT INTO menu VALUES (13, true, 'FAQ', 1, 0, 13, 8);
INSERT INTO menu VALUES (14, true, 'Manual Penggunaan', 2, 0, 14, 8);
INSERT INTO menu VALUES (15, false, 'Kode Mitra', 1, 0, 15, 10);
INSERT INTO menu VALUES (16, false, 'Nama Pelatih', 2, 0, 16, 10);
INSERT INTO menu VALUES (17, true, 'Pekerjaan', 3, 0, 17, 10);
INSERT INTO menu VALUES (18, true, 'Pendidikan', 4, 0, 18, 10);
INSERT INTO menu VALUES (19, true, 'Hubungan', 5, 0, 19, 10);
INSERT INTO menu VALUES (20, true, 'Posisi', 6, 0, 20, 10);
INSERT INTO menu VALUES (21, false, 'Data', 5, 0, 21, 1);
INSERT INTO menu VALUES (22, false, 'Pelatihan', 1, 0, 22, 21);
INSERT INTO menu VALUES (23, false, 'Peserta', 2, 0, 23, 21);
INSERT INTO menu VALUES (24, false, 'Pelatih', 3, 0, 24, 21);
INSERT INTO menu VALUES (26, true, 'Ubah Kata Sandi', 12, 0, 26, 1);
INSERT INTO menu VALUES (27, true, 'Data Peserta', 2, 0, 27, 1);
INSERT INTO menu VALUES (28, true, 'Data Pelatihan', 3, 0, 28, 1);
INSERT INTO menu VALUES (29, true, 'Data Profile', 4, 0, 29, 1);
INSERT INTO menu VALUES (30, true, 'Pelatih', 1, 0, 30, 29);
INSERT INTO menu VALUES (31, true, 'Mitra', 2, 0, 31, 29);
INSERT INTO menu VALUES (32, true, 'Koordinator', 3, 0, 32, 29);


--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 198
-- Name: menu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('menu_id_seq', 1, false);


--
-- TOC entry 2140 (class 0 OID 137385)
-- Dependencies: 177 2172
-- Data for Name: menu_login_roles; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO menu_login_roles VALUES (1, 1);
INSERT INTO menu_login_roles VALUES (2, 1);
INSERT INTO menu_login_roles VALUES (3, 1);
INSERT INTO menu_login_roles VALUES (4, 1);
INSERT INTO menu_login_roles VALUES (5, 1);
INSERT INTO menu_login_roles VALUES (6, 1);
INSERT INTO menu_login_roles VALUES (7, 1);
INSERT INTO menu_login_roles VALUES (8, 1);
INSERT INTO menu_login_roles VALUES (9, 1);
INSERT INTO menu_login_roles VALUES (10, 1);
INSERT INTO menu_login_roles VALUES (11, 1);
INSERT INTO menu_login_roles VALUES (12, 1);
INSERT INTO menu_login_roles VALUES (13, 1);
INSERT INTO menu_login_roles VALUES (14, 1);
INSERT INTO menu_login_roles VALUES (15, 1);
INSERT INTO menu_login_roles VALUES (16, 1);
INSERT INTO menu_login_roles VALUES (17, 1);
INSERT INTO menu_login_roles VALUES (18, 1);
INSERT INTO menu_login_roles VALUES (19, 1);
INSERT INTO menu_login_roles VALUES (20, 1);
INSERT INTO menu_login_roles VALUES (21, 1);
INSERT INTO menu_login_roles VALUES (22, 1);
INSERT INTO menu_login_roles VALUES (23, 1);
INSERT INTO menu_login_roles VALUES (24, 1);
INSERT INTO menu_login_roles VALUES (26, 1);
INSERT INTO menu_login_roles VALUES (27, 1);
INSERT INTO menu_login_roles VALUES (28, 1);
INSERT INTO menu_login_roles VALUES (29, 1);
INSERT INTO menu_login_roles VALUES (30, 1);
INSERT INTO menu_login_roles VALUES (31, 1);
INSERT INTO menu_login_roles VALUES (32, 1);
INSERT INTO menu_login_roles VALUES (8, 2);
INSERT INTO menu_login_roles VALUES (13, 2);
INSERT INTO menu_login_roles VALUES (14, 2);
INSERT INTO menu_login_roles VALUES (8, 3);
INSERT INTO menu_login_roles VALUES (13, 3);
INSERT INTO menu_login_roles VALUES (14, 3);
INSERT INTO menu_login_roles VALUES (32, 3);
INSERT INTO menu_login_roles VALUES (8, 5);
INSERT INTO menu_login_roles VALUES (13, 5);
INSERT INTO menu_login_roles VALUES (14, 5);
INSERT INTO menu_login_roles VALUES (8, 6);
INSERT INTO menu_login_roles VALUES (13, 6);
INSERT INTO menu_login_roles VALUES (14, 6);
INSERT INTO menu_login_roles VALUES (1, 6);
INSERT INTO menu_login_roles VALUES (27, 6);
INSERT INTO menu_login_roles VALUES (28, 6);
INSERT INTO menu_login_roles VALUES (29, 6);
INSERT INTO menu_login_roles VALUES (30, 6);
INSERT INTO menu_login_roles VALUES (1, 5);
INSERT INTO menu_login_roles VALUES (6, 5);
INSERT INTO menu_login_roles VALUES (7, 5);
INSERT INTO menu_login_roles VALUES (11, 5);
INSERT INTO menu_login_roles VALUES (1, 3);
INSERT INTO menu_login_roles VALUES (27, 3);
INSERT INTO menu_login_roles VALUES (28, 3);


--
-- TOC entry 2141 (class 0 OID 137390)
-- Dependencies: 178 2172
-- Data for Name: mitra; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 199
-- Name: mitra_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('mitra_id_seq', 1, true);


--
-- TOC entry 2142 (class 0 OID 137395)
-- Dependencies: 179 2172
-- Data for Name: pelatih; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2191 (class 0 OID 0)
-- Dependencies: 200
-- Name: pelatih_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('pelatih_id_seq', 1, false);


--
-- TOC entry 2125 (class 0 OID 85748)
-- Dependencies: 162 2172
-- Data for Name: pelatih_penerima_manfaat_list; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2143 (class 0 OID 137400)
-- Dependencies: 180 2172
-- Data for Name: pelatihan; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 201
-- Name: pelatihan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('pelatihan_id_seq', 1, false);


--
-- TOC entry 2144 (class 0 OID 137405)
-- Dependencies: 181 2172
-- Data for Name: pelatihan_pelatih_list; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2145 (class 0 OID 137408)
-- Dependencies: 182 2172
-- Data for Name: penerima_manfaat; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 202
-- Name: penerima_manfaat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('penerima_manfaat_id_seq', 1, false);


--
-- TOC entry 2146 (class 0 OID 137413)
-- Dependencies: 183 2172
-- Data for Name: permission; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO permission VALUES (1, 'MITRA_READ', 'Mitra Read', 0);
INSERT INTO permission VALUES (2, 'MITRA_CREATE', 'Mitra Create', 0);
INSERT INTO permission VALUES (3, 'MITRA_UPDATE', 'Mitra Update', 0);
INSERT INTO permission VALUES (4, 'MITRA_DELETE', 'Mitra Delete', 0);
INSERT INTO permission VALUES (5, 'PELATIH_READ', 'Pelatih Read', 0);
INSERT INTO permission VALUES (6, 'PELATIH_CREATE', 'Pelatih Create', 0);
INSERT INTO permission VALUES (7, 'PELATIH_UPDATE', 'Pelatih Update', 0);
INSERT INTO permission VALUES (8, 'PELATIH_DELETE', 'Pelatih Delete', 0);
INSERT INTO permission VALUES (9, 'KOORDINATOR_READ', 'Koordinator Read', 0);
INSERT INTO permission VALUES (10, 'KOORDINATOR_UPDATE', 'Koordinator Update', 0);
INSERT INTO permission VALUES (11, 'PESERTA_READ', 'Peserta Read', 0);
INSERT INTO permission VALUES (12, 'PESERTA_CREATE', 'Peserta Create', 0);
INSERT INTO permission VALUES (13, 'PESERTA_UPDATE', 'Peserta Update', 0);
INSERT INTO permission VALUES (14, 'PESERTA_DELETE', 'Peserta Delete', 0);
INSERT INTO permission VALUES (15, 'PELATIHAN_READ', 'Pelatihan Read', 0);
INSERT INTO permission VALUES (16, 'PELATIHAN_CREATE', 'Pelatihan Create', 0);
INSERT INTO permission VALUES (17, 'PELATIHAN_UPDATE', 'Pelatihan Update', 0);
INSERT INTO permission VALUES (18, 'PELATIHAN_DELETE', 'Pelatihan Delete', 0);
INSERT INTO permission VALUES (19, 'APPROVE_DATA', 'Menyetujui Data ', 0);
INSERT INTO permission VALUES (20, 'VIEW_ALL_DATA', 'Melihat Semua Data', 0);
INSERT INTO permission VALUES (21, 'PERMISSION_READ', 'Melihat Izin', 0);
INSERT INTO permission VALUES (22, 'PEKERJAAN_READ', 'Pekerjaan Read', 0);
INSERT INTO permission VALUES (23, 'PEKERJAAN_CREATE', 'Pekerjaan Create', 0);
INSERT INTO permission VALUES (24, 'PEKERJAAN_UPDATE', 'Pekerjaan Update', 0);
INSERT INTO permission VALUES (25, 'PEKERJAAN_DELETE', 'Pekerjaan Delete', 0);
INSERT INTO permission VALUES (26, 'PENDIDIKAN_READ', 'Pendidikan Read', 0);
INSERT INTO permission VALUES (27, 'PENDIDIKAN_CREATE', 'Pendidikan Create', 0);
INSERT INTO permission VALUES (28, 'PENDIDIKAN_UPDATE', 'Pendidikan Update', 0);
INSERT INTO permission VALUES (29, 'PENDIDIKAN_DELETE', 'Pendidikan Delete', 0);
INSERT INTO permission VALUES (30, 'HUBUNGAN_READ', 'Hubungan Read', 0);
INSERT INTO permission VALUES (31, 'HUBUNGAN_CREATE', 'Hubungan Create', 0);
INSERT INTO permission VALUES (32, 'HUBUNGAN_UPDATE', 'Hubungan Update', 0);
INSERT INTO permission VALUES (33, 'HUBUNGAN_DELETE', 'Hubungan Delete', 0);
INSERT INTO permission VALUES (34, 'POSISI_READ', 'Posisi Read', 0);
INSERT INTO permission VALUES (35, 'POSISI_CREATE', 'Posisi Create', 0);
INSERT INTO permission VALUES (36, 'POSISI_UPDATE', 'Posisi Update', 0);
INSERT INTO permission VALUES (37, 'POSISI_DELETE', 'Posisi Delete', 0);
INSERT INTO permission VALUES (38, 'LAPORAN_READ', 'Melihat Laporan', 0);
INSERT INTO permission VALUES (39, 'MILES_DSBRD_READ', 'Melihat MILES Dashboard', 0);
INSERT INTO permission VALUES (40, 'ADMIN_DSBRD_READ', 'Melihat Admin Dashboard', 0);
INSERT INTO permission VALUES (41, 'SUPER_ADMIN', 'Super Admin', 0);
INSERT INTO permission VALUES (42, 'PENUGASAN_KOORD_PELATIH', 'Penugasan Koordinasi Pelatih', 0);


--
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 203
-- Name: permission_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('permission_id_seq', 1, false);


--
-- TOC entry 2147 (class 0 OID 137418)
-- Dependencies: 184 2172
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO person VALUES (1, 'address_0', '2013-06-05 10:32:02', '2013-06-05 10:31:46', 'M', '2013-06-05 10:32:20', 'motherMaiden_0', 'name1_0', 'name2_0', 'name3_0', 'name4_0', 'npwp_0', 'passportNo_0', NULL, 'placeOfBirth_0', 0, 3573010010);
INSERT INTO person VALUES (2, 'address_1', '2013-06-05 10:36:18', '2013-06-05 10:44:01', 'M', '2013-06-05 10:43:31', 'motherMaiden_1', 'name1_1', 'name2_1', 'name3_1', 'name4_1', 'npwp_1', 'passportNo_1', NULL, 'placeOfBirth_1', 0, 3573010010);
INSERT INTO person VALUES (3, 'address_2', '2013-06-05 10:42:09', '2013-06-05 10:40:21', 'M', '2013-06-05 10:29:32', 'motherMaiden_2', 'name1_2', 'name2_2', 'name3_2', 'name4_2', 'npwp_2', 'passportNo_2', NULL, 'placeOfBirth_2', 0, 3573010010);
INSERT INTO person VALUES (4, 'address_3', '2013-06-05 10:38:50', '2013-06-05 10:41:31', 'M', '2013-06-05 10:40:50', 'motherMaiden_3', 'name1_3', 'name2_3', 'name3_3', 'name4_3', 'npwp_3', 'passportNo_3', NULL, 'placeOfBirth_3', 0, 3573010010);
INSERT INTO person VALUES (5, 'address_4', '2013-06-05 10:29:33', '2013-06-05 10:44:25', 'M', '2013-06-05 10:30:27', 'motherMaiden_4', 'name1_4', 'name2_4', 'name3_4', 'name4_4', 'npwp_4', 'passportNo_4', NULL, 'placeOfBirth_4', 0, 3573010010);
INSERT INTO person VALUES (6, 'address_5', '2013-06-05 10:41:35', '2013-06-05 10:36:17', 'M', '2013-06-05 10:28:55', 'motherMaiden_5', 'name1_5', 'name2_5', 'name3_5', 'name4_5', 'npwp_5', 'passportNo_5', NULL, 'placeOfBirth_5', 0, 3573010010);
INSERT INTO person VALUES (7, 'address_6', '2013-06-05 10:35:05', '2013-06-05 10:44:55', 'M', '2013-06-05 10:33:09', 'motherMaiden_6', 'name1_6', 'name2_6', 'name3_6', 'name4_6', 'npwp_6', 'passportNo_6', NULL, 'placeOfBirth_6', 0, 3573010010);
INSERT INTO person VALUES (8, 'address_7', '2013-06-05 10:42:15', '2013-06-05 10:39:43', 'M', '2013-06-05 10:34:40', 'motherMaiden_7', 'name1_7', 'name2_7', 'name3_7', 'name4_7', 'npwp_7', 'passportNo_7', NULL, 'placeOfBirth_7', 0, 3573010010);
INSERT INTO person VALUES (9, 'address_8', '2013-06-05 10:43:48', '2013-06-05 10:45:10', 'M', '2013-06-05 10:45:35', 'motherMaiden_8', 'name1_8', 'name2_8', 'name3_8', 'name4_8', 'npwp_8', 'passportNo_8', NULL, 'placeOfBirth_8', 0, 3573010010);
INSERT INTO person VALUES (10, 'address_9', '2013-06-05 10:34:04', '2013-06-05 10:32:49', 'M', '2013-06-05 10:43:21', 'motherMaiden_9', 'name1_9', 'name2_9', 'name3_9', 'name4_9', 'npwp_9', 'passportNo_9', NULL, 'placeOfBirth_9', 0, 3573010010);
INSERT INTO person VALUES (11, 'address_10', '2013-06-05 10:29:39', '2013-06-05 10:43:26', 'M', '2013-06-05 10:33:15', 'motherMaiden_10', 'name1_10', 'name2_10', 'name3_10', 'name4_10', 'npwp_10', 'passportNo_10', NULL, 'placeOfBirth_10', 0, 3573010010);
INSERT INTO person VALUES (12, 'address_11', '2013-06-05 10:35:48', '2013-06-05 10:30:16', 'M', '2013-06-05 10:31:24', 'motherMaiden_11', 'name1_11', 'name2_11', 'name3_11', 'name4_11', 'npwp_11', 'passportNo_11', NULL, 'placeOfBirth_11', 0, 3573010010);
INSERT INTO person VALUES (13, 'address_12', '2013-06-05 10:40:43', '2013-06-05 10:35:49', 'M', '2013-06-05 10:32:52', 'motherMaiden_12', 'name1_12', 'name2_12', 'name3_12', 'name4_12', 'npwp_12', 'passportNo_12', NULL, 'placeOfBirth_12', 0, 3573010010);
INSERT INTO person VALUES (14, 'address_13', '2013-06-05 10:35:01', '2013-06-05 10:44:01', 'M', '2013-06-05 10:39:55', 'motherMaiden_13', 'name1_13', 'name2_13', 'name3_13', 'name4_13', 'npwp_13', 'passportNo_13', NULL, 'placeOfBirth_13', 0, 3573010010);
INSERT INTO person VALUES (15, 'address_14', '2013-06-05 10:30:09', '2013-06-05 10:32:00', 'M', '2013-06-05 10:40:04', 'motherMaiden_14', 'name1_14', 'name2_14', 'name3_14', 'name4_14', 'npwp_14', 'passportNo_14', NULL, 'placeOfBirth_14', 0, 3573010010);
INSERT INTO person VALUES (16, 'address_15', '2013-06-05 10:40:04', '2013-06-05 10:40:01', 'M', '2013-06-05 10:33:57', 'motherMaiden_15', 'name1_15', 'name2_15', 'name3_15', 'name4_15', 'npwp_15', 'passportNo_15', NULL, 'placeOfBirth_15', 0, 3573010010);
INSERT INTO person VALUES (17, 'address_16', '2013-06-05 10:40:52', '2013-06-05 10:30:42', 'M', '2013-06-05 10:30:03', 'motherMaiden_16', 'name1_16', 'name2_16', 'name3_16', 'name4_16', 'npwp_16', 'passportNo_16', NULL, 'placeOfBirth_16', 0, 3573010010);
INSERT INTO person VALUES (18, 'address_17', '2013-06-05 10:30:13', '2013-06-05 10:39:06', 'M', '2013-06-05 10:37:15', 'motherMaiden_17', 'name1_17', 'name2_17', 'name3_17', 'name4_17', 'npwp_17', 'passportNo_17', NULL, 'placeOfBirth_17', 0, 3573010010);
INSERT INTO person VALUES (19, 'address_18', '2013-06-05 10:36:02', '2013-06-05 10:35:51', 'M', '2013-06-05 10:38:24', 'motherMaiden_18', 'name1_18', 'name2_18', 'name3_18', 'name4_18', 'npwp_18', 'passportNo_18', NULL, 'placeOfBirth_18', 0, 3573010010);
INSERT INTO person VALUES (20, 'address_19', '2013-06-05 10:36:59', '2013-06-05 10:41:22', 'M', '2013-06-05 10:32:57', 'motherMaiden_19', 'name1_19', 'name2_19', 'name3_19', 'name4_19', 'npwp_19', 'passportNo_19', NULL, 'placeOfBirth_19', 0, 3573010010);
INSERT INTO person VALUES (21, 'address_20', '2013-06-05 10:43:55', '2013-06-05 10:40:36', 'M', '2013-06-05 10:36:02', 'motherMaiden_20', 'name1_20', 'name2_20', 'name3_20', 'name4_20', 'npwp_20', 'passportNo_20', NULL, 'placeOfBirth_20', 0, 3573010010);
INSERT INTO person VALUES (22, 'address_21', '2013-06-05 10:40:39', '2013-06-05 10:44:43', 'M', '2013-06-05 10:35:38', 'motherMaiden_21', 'name1_21', 'name2_21', 'name3_21', 'name4_21', 'npwp_21', 'passportNo_21', NULL, 'placeOfBirth_21', 0, 3573010010);
INSERT INTO person VALUES (23, 'address_22', '2013-06-05 10:33:37', '2013-06-05 10:39:44', 'M', '2013-06-05 10:39:39', 'motherMaiden_22', 'name1_22', 'name2_22', 'name3_22', 'name4_22', 'npwp_22', 'passportNo_22', NULL, 'placeOfBirth_22', 0, 3573010010);
INSERT INTO person VALUES (24, 'address_23', '2013-06-05 10:45:10', '2013-06-05 10:40:35', 'M', '2013-06-05 10:29:35', 'motherMaiden_23', 'name1_23', 'name2_23', 'name3_23', 'name4_23', 'npwp_23', 'passportNo_23', NULL, 'placeOfBirth_23', 0, 3573010010);
INSERT INTO person VALUES (25, 'address_24', '2013-06-05 10:40:59', '2013-06-05 10:43:42', 'M', '2013-06-05 10:44:25', 'motherMaiden_24', 'name1_24', 'name2_24', 'name3_24', 'name4_24', 'npwp_24', 'passportNo_24', NULL, 'placeOfBirth_24', 0, 3573010010);


--
-- TOC entry 2195 (class 0 OID 0)
-- Dependencies: 204
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('person_id_seq', 1, false);


--
-- TOC entry 2148 (class 0 OID 137423)
-- Dependencies: 185 2172
-- Data for Name: peserta; Type: TABLE DATA; Schema: public; Owner: miles
--



--
-- TOC entry 2196 (class 0 OID 0)
-- Dependencies: 205
-- Name: peserta_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('peserta_id_seq', 1, false);


--
-- TOC entry 2149 (class 0 OID 137428)
-- Dependencies: 186 2172
-- Data for Name: province; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO province VALUES (11, '11.00.00.0000', 'Nanggroe Aceh Darussalaam', 0, 1);
INSERT INTO province VALUES (12, '12.00.00.0000', 'Sumatra Utara', 0, 1);
INSERT INTO province VALUES (13, '13.00.00.0000', 'Sumatra Barat', 0, 1);
INSERT INTO province VALUES (14, '14.00.00.0000', 'Riau', 0, 1);
INSERT INTO province VALUES (15, '15.00.00.0000', 'Jambi', 0, 1);
INSERT INTO province VALUES (16, '16.00.00.0000', 'Sumatra Selatan', 0, 1);
INSERT INTO province VALUES (17, '17.00.00.0000', 'Bengkulu', 0, 1);
INSERT INTO province VALUES (18, '18.00.00.0000', 'Lampung', 0, 1);
INSERT INTO province VALUES (19, '19.00.00.0000', 'Kep. Bangka Belitung', 0, 1);
INSERT INTO province VALUES (21, '21.00.00.0000', 'Kep. Riau', 0, 1);
INSERT INTO province VALUES (31, '31.00.00.0000', 'DKI Jakarta', 0, 1);
INSERT INTO province VALUES (32, '32.00.00.0000', 'Jawa Barat', 0, 1);
INSERT INTO province VALUES (33, '33.00.00.0000', 'Jawa Tengah', 0, 1);
INSERT INTO province VALUES (34, '34.00.00.0000', 'DI Yogyakarta', 0, 1);
INSERT INTO province VALUES (35, '35.00.00.0000', 'Jawa Timur', 0, 1);
INSERT INTO province VALUES (36, '36.00.00.0000', 'Banten', 0, 1);
INSERT INTO province VALUES (51, '51.00.00.0000', 'Bali', 0, 1);
INSERT INTO province VALUES (52, '52.00.00.0000', 'Nusa Tenggara Barat', 0, 1);
INSERT INTO province VALUES (53, '53.00.00.0000', 'Nusa Tenggara Timur', 0, 1);
INSERT INTO province VALUES (61, '61.00.00.0000', 'Kalimantan Barat', 0, 1);
INSERT INTO province VALUES (62, '62.00.00.0000', 'Kalimantan Tengah', 0, 1);
INSERT INTO province VALUES (63, '63.00.00.0000', 'Kalimantan Selatan', 0, 1);
INSERT INTO province VALUES (64, '64.00.00.0000', 'Kalimantan Timur', 0, 1);
INSERT INTO province VALUES (71, '71.00.00.0000', 'Sulawesi Utara', 0, 1);
INSERT INTO province VALUES (72, '72.00.00.0000', 'Sulawesi Tengah', 0, 1);
INSERT INTO province VALUES (73, '73.00.00.0000', 'Sulawesi Selatan', 0, 1);
INSERT INTO province VALUES (74, '74.00.00.0000', 'Sulawesi Tenggara', 0, 1);
INSERT INTO province VALUES (75, '75.00.00.0000', 'Gorontalo', 0, 1);
INSERT INTO province VALUES (81, '81.00.00.0000', 'Maluku', 0, 1);
INSERT INTO province VALUES (82, '82.00.00.0000', 'Maluku Utara', 0, 1);
INSERT INTO province VALUES (91, '91.00.00.0000', 'Papua', 0, 1);
INSERT INTO province VALUES (92, '92.00.00.0000', 'Irian Jaya Barat', 0, 1);


--
-- TOC entry 2197 (class 0 OID 0)
-- Dependencies: 206
-- Name: province_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('province_id_seq', 1, false);


--
-- TOC entry 2150 (class 0 OID 137433)
-- Dependencies: 187 2172
-- Data for Name: sub_district; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO sub_district VALUES (357301, '35.73.01.0000', 'KLOJEN', 0, 3573);
INSERT INTO sub_district VALUES (357302, '35.73.02.0000', 'BLIMBING', 0, 3573);
INSERT INTO sub_district VALUES (357303, '35.73.03.0000', 'KEDUNGKANDANG', 0, 3573);
INSERT INTO sub_district VALUES (357304, '35.73.04.0000', 'LOWOKWARU', 0, 3573);
INSERT INTO sub_district VALUES (357305, '35.73.05.0000', 'SUKUN', 0, 3573);


--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 207
-- Name: sub_district_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('sub_district_id_seq', 1, false);


--
-- TOC entry 2151 (class 0 OID 137438)
-- Dependencies: 188 2172
-- Data for Name: system_lookup; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO system_lookup VALUES (1, 'M', 'Laki-laki', 'GNDR', 0);
INSERT INTO system_lookup VALUES (2, 'F', 'Perempuan', 'GNDR', 0);
INSERT INTO system_lookup VALUES (3, 'A', 'Aktif', 'STATUS', 0);
INSERT INTO system_lookup VALUES (4, 'N', 'Tidak Aktif', 'STATUS', 0);
INSERT INTO system_lookup VALUES (5, 'TOT', 'Training of Trainer', 'PLTTYPE', 0);
INSERT INTO system_lookup VALUES (6, 'PLTH', 'Pelatihan', 'PLTTYPE', 0);
INSERT INTO system_lookup VALUES (7, 'INSR', 'Input', 'PLTSTTS', 0);
INSERT INTO system_lookup VALUES (8, 'APPR', 'Approved', 'PLTSTTS', 0);
INSERT INTO system_lookup VALUES (9, 'PNS', 'PNS', 'OCCU', 0);
INSERT INTO system_lookup VALUES (10, 'KRY', 'Karyawan Swasta', 'OCCU', 0);
INSERT INTO system_lookup VALUES (11, 'WRS', 'Wiraswasta (Pedagang, Penjahit)', 'OCCU', 0);
INSERT INTO system_lookup VALUES (12, 'BRHP', 'Buruh Pabrik', 'OCCU', 0);
INSERT INTO system_lookup VALUES (13, 'BRHL', 'Buruh Lepas (Tukang Cuci, Ojek)', 'OCCU', 0);
INSERT INTO system_lookup VALUES (14, 'PTN', 'Petani (Beras, Nelayan)', 'OCCU', 0);
INSERT INTO system_lookup VALUES (15, 'SMI', 'Suami', 'RELT', 0);
INSERT INTO system_lookup VALUES (16, 'IST', 'Istri', 'RELT', 0);
INSERT INTO system_lookup VALUES (17, 'ANK', 'Anak', 'RELT', 0);
INSERT INTO system_lookup VALUES (18, 'ORT', 'Orangtua', 'RELT', 0);
INSERT INTO system_lookup VALUES (19, 'PMBB', 'Paman/Bibi', 'RELT', 0);
INSERT INTO system_lookup VALUES (20, 'SD', 'SD', 'EDU', 0);
INSERT INTO system_lookup VALUES (21, 'SMP', 'SMP', 'EDU', 0);
INSERT INTO system_lookup VALUES (22, 'SMU', 'SMU', 'EDU', 0);
INSERT INTO system_lookup VALUES (23, 'D1', 'D1', 'EDU', 0);
INSERT INTO system_lookup VALUES (24, 'D2', 'D2', 'EDU', 0);
INSERT INTO system_lookup VALUES (25, 'D3', 'D3', 'EDU', 0);
INSERT INTO system_lookup VALUES (26, 'S1', 'S1', 'EDU', 0);
INSERT INTO system_lookup VALUES (27, 'S2', 'S2', 'EDU', 0);
INSERT INTO system_lookup VALUES (28, 'S3', 'S3', 'EDU', 0);
INSERT INTO system_lookup VALUES (29, 'M', 'Menikah', 'MRTSTS', 0);
INSERT INTO system_lookup VALUES (30, 'BM', 'Belum Menikah', 'MRTSTS', 0);
INSERT INTO system_lookup VALUES (31, 'CR', 'Cerai', 'MRTSTS', 0);
INSERT INTO system_lookup VALUES (32, 'KTP', 'KTP', 'IDTYPE', 0);
INSERT INTO system_lookup VALUES (33, 'KK', 'Kartu Keluarga', 'IDTYPE', 0);
INSERT INTO system_lookup VALUES (34, 'SLKM', 'SK LKM', 'IDTYPE', 0);
INSERT INTO system_lookup VALUES (35, 'SKEL', 'SK Desa/Kelurahan', 'IDTYPE', 0);


--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 208
-- Name: system_lookup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: miles
--

SELECT pg_catalog.setval('system_lookup_id_seq', 1, false);


--
-- TOC entry 2152 (class 0 OID 137443)
-- Dependencies: 189 2172
-- Data for Name: village; Type: TABLE DATA; Schema: public; Owner: miles
--

INSERT INTO village VALUES (3573010001, '35.73.01.0001', 'Rampal Celaket', 0, 357301);
INSERT INTO village VALUES (3573010002, '35.73.01.0002', 'Oro-Oro Dowo', 0, 357301);
INSERT INTO village VALUES (3573010003, '35.73.01.0003', 'Samaan', 0, 357301);
INSERT INTO village VALUES (3573010004, '35.73.01.0004', 'Penanggungan', 0, 357301);
INSERT INTO village VALUES (3573010005, '35.73.01.0005', 'Gadingasri', 0, 357301);
INSERT INTO village VALUES (3573010006, '35.73.01.0006', 'Bareng', 0, 357301);
INSERT INTO village VALUES (3573010007, '35.73.01.0007', 'Kasin', 0, 357301);
INSERT INTO village VALUES (3573010008, '35.73.01.0008', 'Sukoharjo', 0, 357301);
INSERT INTO village VALUES (3573010009, '35.73.01.0009', 'Kauman', 0, 357301);
INSERT INTO village VALUES (3573010010, '35.73.01.0010', 'Kiduldalem', 0, 357301);


--
-- TOC entry 2037 (class 2606 OID 137335)
-- Dependencies: 165 165 2173
-- Name: address_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- TOC entry 2039 (class 2606 OID 137340)
-- Dependencies: 166 166 2173
-- Name: country_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 137345)
-- Dependencies: 167 167 2173
-- Name: district_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY district
    ADD CONSTRAINT district_pkey PRIMARY KEY (id);


--
-- TOC entry 2043 (class 2606 OID 137350)
-- Dependencies: 168 168 2173
-- Name: image_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 2045 (class 2606 OID 137355)
-- Dependencies: 169 169 2173
-- Name: koordinator_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY koordinator
    ADD CONSTRAINT koordinator_pkey PRIMARY KEY (id);


--
-- TOC entry 2047 (class 2606 OID 137360)
-- Dependencies: 170 170 2173
-- Name: link_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY link
    ADD CONSTRAINT link_pkey PRIMARY KEY (id);


--
-- TOC entry 2049 (class 2606 OID 137365)
-- Dependencies: 171 171 2173
-- Name: login_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY login
    ADD CONSTRAINT login_pkey PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 99120)
-- Dependencies: 163 163 163 2173
-- Name: login_role_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY login_role_logins
    ADD CONSTRAINT login_role_logins_pkey PRIMARY KEY (login_role, logins);


--
-- TOC entry 2053 (class 2606 OID 137376)
-- Dependencies: 174 174 2173
-- Name: login_role_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY login_role
    ADD CONSTRAINT login_role_pkey PRIMARY KEY (id);


--
-- TOC entry 2057 (class 2606 OID 137389)
-- Dependencies: 177 177 177 2173
-- Name: menu_login_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY menu_login_roles
    ADD CONSTRAINT menu_login_roles_pkey PRIMARY KEY (menus, login_roles);


--
-- TOC entry 2055 (class 2606 OID 137384)
-- Dependencies: 176 176 2173
-- Name: menu_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- TOC entry 2059 (class 2606 OID 137394)
-- Dependencies: 178 178 2173
-- Name: mitra_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY mitra
    ADD CONSTRAINT mitra_pkey PRIMARY KEY (id);


--
-- TOC entry 2061 (class 2606 OID 137399)
-- Dependencies: 179 179 2173
-- Name: pelatih_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT pelatih_pkey PRIMARY KEY (id);


--
-- TOC entry 2063 (class 2606 OID 137404)
-- Dependencies: 180 180 2173
-- Name: pelatihan_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY pelatihan
    ADD CONSTRAINT pelatihan_pkey PRIMARY KEY (id);


--
-- TOC entry 2065 (class 2606 OID 137412)
-- Dependencies: 182 182 2173
-- Name: penerima_manfaat_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY penerima_manfaat
    ADD CONSTRAINT penerima_manfaat_pkey PRIMARY KEY (id);


--
-- TOC entry 2067 (class 2606 OID 137417)
-- Dependencies: 183 183 2173
-- Name: permission_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);


--
-- TOC entry 2069 (class 2606 OID 137422)
-- Dependencies: 184 184 2173
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 137427)
-- Dependencies: 185 185 2173
-- Name: peserta_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY peserta
    ADD CONSTRAINT peserta_pkey PRIMARY KEY (id);


--
-- TOC entry 2075 (class 2606 OID 137432)
-- Dependencies: 186 186 2173
-- Name: province_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY province
    ADD CONSTRAINT province_pkey PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 137437)
-- Dependencies: 187 187 2173
-- Name: sub_district_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY sub_district
    ADD CONSTRAINT sub_district_pkey PRIMARY KEY (id);


--
-- TOC entry 2079 (class 2606 OID 137442)
-- Dependencies: 188 188 2173
-- Name: system_lookup_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY system_lookup
    ADD CONSTRAINT system_lookup_pkey PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 137626)
-- Dependencies: 185 185 185 2173
-- Name: uk_2c5e43caf8e7427da12f4f741cf; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY peserta
    ADD CONSTRAINT uk_2c5e43caf8e7427da12f4f741cf UNIQUE (passport_type, passport_no);


--
-- TOC entry 2051 (class 2606 OID 137474)
-- Dependencies: 171 171 2173
-- Name: uk_5fb15747bb794d6099ef459fc65; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY login
    ADD CONSTRAINT uk_5fb15747bb794d6099ef459fc65 UNIQUE (username);


--
-- TOC entry 2033 (class 2606 OID 85890)
-- Dependencies: 162 162 2173
-- Name: uk_e28b73dc6b27477c99f0d1e3029; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY pelatih_penerima_manfaat_list
    ADD CONSTRAINT uk_e28b73dc6b27477c99f0d1e3029 UNIQUE (penerima_manfaat_list);


--
-- TOC entry 2081 (class 2606 OID 137447)
-- Dependencies: 189 189 2173
-- Name: village_pkey; Type: CONSTRAINT; Schema: public; Owner: miles; Tablespace: 
--

ALTER TABLE ONLY village
    ADD CONSTRAINT village_pkey PRIMARY KEY (id);


--
-- TOC entry 2094 (class 2606 OID 137510)
-- Dependencies: 2052 175 174 2173
-- Name: fk_013a1b90a3cd4635bd88a71f57a; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login_role_permissions
    ADD CONSTRAINT fk_013a1b90a3cd4635bd88a71f57a FOREIGN KEY (login_roles) REFERENCES login_role(id);


--
-- TOC entry 2084 (class 2606 OID 137458)
-- Dependencies: 2074 167 186 2173
-- Name: fk_02080463eb6e4edcb3bb33cc0ca; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY district
    ADD CONSTRAINT fk_02080463eb6e4edcb3bb33cc0ca FOREIGN KEY (province) REFERENCES province(id);


--
-- TOC entry 2098 (class 2606 OID 137530)
-- Dependencies: 2054 176 177 2173
-- Name: fk_0412a46c6e17427e84244320ac0; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY menu_login_roles
    ADD CONSTRAINT fk_0412a46c6e17427e84244320ac0 FOREIGN KEY (menus) REFERENCES menu(id);


--
-- TOC entry 2101 (class 2606 OID 137545)
-- Dependencies: 2048 171 179 2173
-- Name: fk_11d42ee18a404e678b4b29a236c; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT fk_11d42ee18a404e678b4b29a236c FOREIGN KEY (creator) REFERENCES login(id);


--
-- TOC entry 2090 (class 2606 OID 137490)
-- Dependencies: 2048 171 172 2173
-- Name: fk_12888430d3ab497f9bca31a4f20; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login_links
    ADD CONSTRAINT fk_12888430d3ab497f9bca31a4f20 FOREIGN KEY (login) REFERENCES login(id);


--
-- TOC entry 2097 (class 2606 OID 137525)
-- Dependencies: 177 2052 174 2173
-- Name: fk_1456355099814afa9af84e39a33; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY menu_login_roles
    ADD CONSTRAINT fk_1456355099814afa9af84e39a33 FOREIGN KEY (login_roles) REFERENCES login_role(id);


--
-- TOC entry 2119 (class 2606 OID 137637)
-- Dependencies: 171 185 2048 2173
-- Name: fk_23ae33683945493f8a622b10821; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY peserta
    ADD CONSTRAINT fk_23ae33683945493f8a622b10821 FOREIGN KEY (modifier) REFERENCES login(id);


--
-- TOC entry 2082 (class 2606 OID 137448)
-- Dependencies: 184 165 2068 2173
-- Name: fk_2f413f7589e244cfb155ad5002d; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY address
    ADD CONSTRAINT fk_2f413f7589e244cfb155ad5002d FOREIGN KEY (person) REFERENCES person(id);


--
-- TOC entry 2088 (class 2606 OID 137480)
-- Dependencies: 171 184 2068 2173
-- Name: fk_31c99415fe984372abbed134aab; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login
    ADD CONSTRAINT fk_31c99415fe984372abbed134aab FOREIGN KEY (person) REFERENCES person(id);


--
-- TOC entry 2116 (class 2606 OID 137620)
-- Dependencies: 2080 184 189 2173
-- Name: fk_321836c8c60741db9be8e325371; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY person
    ADD CONSTRAINT fk_321836c8c60741db9be8e325371 FOREIGN KEY (village) REFERENCES village(id);


--
-- TOC entry 2111 (class 2606 OID 137595)
-- Dependencies: 181 2062 180 2173
-- Name: fk_42733f96c4f8482faf753e3ec09; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatihan_pelatih_list
    ADD CONSTRAINT fk_42733f96c4f8482faf753e3ec09 FOREIGN KEY (pelatihan_list) REFERENCES pelatihan(id);


--
-- TOC entry 2096 (class 2606 OID 137520)
-- Dependencies: 176 2054 176 2173
-- Name: fk_4a6d3279f0434d7a8d1e5d7e90f; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT fk_4a6d3279f0434d7a8d1e5d7e90f FOREIGN KEY (parent_menu) REFERENCES menu(id);


--
-- TOC entry 2099 (class 2606 OID 137535)
-- Dependencies: 178 2048 171 2173
-- Name: fk_4acf7bc143e2493d8c042e2acf6; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY mitra
    ADD CONSTRAINT fk_4acf7bc143e2493d8c042e2acf6 FOREIGN KEY (creator) REFERENCES login(id);


--
-- TOC entry 2120 (class 2606 OID 137642)
-- Dependencies: 180 2062 185 2173
-- Name: fk_4b577a0e64a449fd9e94a0e2a26; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY peserta
    ADD CONSTRAINT fk_4b577a0e64a449fd9e94a0e2a26 FOREIGN KEY (pelatihan) REFERENCES pelatihan(id);


--
-- TOC entry 2115 (class 2606 OID 137615)
-- Dependencies: 182 2070 185 2173
-- Name: fk_4b888e88425f41dd9a91d10e808; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY penerima_manfaat
    ADD CONSTRAINT fk_4b888e88425f41dd9a91d10e808 FOREIGN KEY (peserta) REFERENCES peserta(id);


--
-- TOC entry 2114 (class 2606 OID 137610)
-- Dependencies: 182 2060 179 2173
-- Name: fk_502772ee56504d91877c6775c27; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY penerima_manfaat
    ADD CONSTRAINT fk_502772ee56504d91877c6775c27 FOREIGN KEY (pelatih) REFERENCES pelatih(id);


--
-- TOC entry 2093 (class 2606 OID 137505)
-- Dependencies: 183 175 2066 2173
-- Name: fk_50a201cf21e44b4e9dc8e6cb978; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login_role_permissions
    ADD CONSTRAINT fk_50a201cf21e44b4e9dc8e6cb978 FOREIGN KEY (permissions) REFERENCES permission(id);


--
-- TOC entry 2113 (class 2606 OID 137605)
-- Dependencies: 182 171 2048 2173
-- Name: fk_53fa4ace8efb4a81811189a7cb2; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY penerima_manfaat
    ADD CONSTRAINT fk_53fa4ace8efb4a81811189a7cb2 FOREIGN KEY (modifier) REFERENCES login(id);


--
-- TOC entry 2092 (class 2606 OID 137500)
-- Dependencies: 173 2048 171 2173
-- Name: fk_564df1ce281949d080d3dbc1cdf; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login_login_roles
    ADD CONSTRAINT fk_564df1ce281949d080d3dbc1cdf FOREIGN KEY (login) REFERENCES login(id);


--
-- TOC entry 2103 (class 2606 OID 137555)
-- Dependencies: 179 171 2048 2173
-- Name: fk_5bc379e659fb4d56be18f2e79df; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT fk_5bc379e659fb4d56be18f2e79df FOREIGN KEY (login) REFERENCES login(id);


--
-- TOC entry 2095 (class 2606 OID 137515)
-- Dependencies: 170 2046 176 2173
-- Name: fk_5f29a8306ee34ecea0fc9c000b2; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT fk_5f29a8306ee34ecea0fc9c000b2 FOREIGN KEY (link) REFERENCES link(id);


--
-- TOC entry 2110 (class 2606 OID 137590)
-- Dependencies: 2060 179 181 2173
-- Name: fk_75d3192ef01a42cbafc1d10dc23; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatihan_pelatih_list
    ADD CONSTRAINT fk_75d3192ef01a42cbafc1d10dc23 FOREIGN KEY (pelatih_list) REFERENCES pelatih(id);


--
-- TOC entry 2104 (class 2606 OID 137560)
-- Dependencies: 179 2058 178 2173
-- Name: fk_7ba764b1b9a7419da3c4a4ed46d; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT fk_7ba764b1b9a7419da3c4a4ed46d FOREIGN KEY (mitra) REFERENCES mitra(id);


--
-- TOC entry 2106 (class 2606 OID 137570)
-- Dependencies: 179 2080 189 2173
-- Name: fk_806e169290f0449c9869c8e91b6; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT fk_806e169290f0449c9869c8e91b6 FOREIGN KEY (village) REFERENCES village(id);


--
-- TOC entry 2107 (class 2606 OID 137575)
-- Dependencies: 2048 171 180 2173
-- Name: fk_811b0b5104a84ff1a88feb95ff9; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatihan
    ADD CONSTRAINT fk_811b0b5104a84ff1a88feb95ff9 FOREIGN KEY (creator) REFERENCES login(id);


--
-- TOC entry 2112 (class 2606 OID 137600)
-- Dependencies: 182 2048 171 2173
-- Name: fk_8793a84898734c12ab4f3cbe447; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY penerima_manfaat
    ADD CONSTRAINT fk_8793a84898734c12ab4f3cbe447 FOREIGN KEY (creator) REFERENCES login(id);


--
-- TOC entry 2102 (class 2606 OID 137550)
-- Dependencies: 179 169 2044 2173
-- Name: fk_8863c5af692d4de4b46f841e506; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT fk_8863c5af692d4de4b46f841e506 FOREIGN KEY (koordinator) REFERENCES koordinator(id);


--
-- TOC entry 2108 (class 2606 OID 137580)
-- Dependencies: 178 180 2058 2173
-- Name: fk_97345aeb99e34011859655e9883; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatihan
    ADD CONSTRAINT fk_97345aeb99e34011859655e9883 FOREIGN KEY (mitra) REFERENCES mitra(id);


--
-- TOC entry 2117 (class 2606 OID 137627)
-- Dependencies: 185 2080 189 2173
-- Name: fk_9dd0e6a639e14795a7bea0c1d05; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY peserta
    ADD CONSTRAINT fk_9dd0e6a639e14795a7bea0c1d05 FOREIGN KEY (village) REFERENCES village(id);


--
-- TOC entry 2086 (class 2606 OID 137468)
-- Dependencies: 169 2058 178 2173
-- Name: fk_a3cff3ddf3ab40429dda068a441; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY koordinator
    ADD CONSTRAINT fk_a3cff3ddf3ab40429dda068a441 FOREIGN KEY (mitra) REFERENCES mitra(id);


--
-- TOC entry 2105 (class 2606 OID 137565)
-- Dependencies: 179 171 2048 2173
-- Name: fk_a42ef6146bdd44a996361ae7224; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatih
    ADD CONSTRAINT fk_a42ef6146bdd44a996361ae7224 FOREIGN KEY (modifier) REFERENCES login(id);


--
-- TOC entry 2123 (class 2606 OID 137657)
-- Dependencies: 2076 187 189 2173
-- Name: fk_a47f024b6ecd48b29bdcae375b7; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY village
    ADD CONSTRAINT fk_a47f024b6ecd48b29bdcae375b7 FOREIGN KEY (sub_district) REFERENCES sub_district(id);


--
-- TOC entry 2121 (class 2606 OID 137647)
-- Dependencies: 2038 166 186 2173
-- Name: fk_adde1a6747f043d3870e0e8a984; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY province
    ADD CONSTRAINT fk_adde1a6747f043d3870e0e8a984 FOREIGN KEY (country) REFERENCES country(id);


--
-- TOC entry 2091 (class 2606 OID 137495)
-- Dependencies: 2052 174 173 2173
-- Name: fk_b587a70887f04d4d8cebcf8430c; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login_login_roles
    ADD CONSTRAINT fk_b587a70887f04d4d8cebcf8430c FOREIGN KEY (login_roles) REFERENCES login_role(id);


--
-- TOC entry 2089 (class 2606 OID 137485)
-- Dependencies: 2046 172 170 2173
-- Name: fk_b5b7a535ede740c2a62b3bafd15; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login_links
    ADD CONSTRAINT fk_b5b7a535ede740c2a62b3bafd15 FOREIGN KEY (links) REFERENCES link(id);


--
-- TOC entry 2085 (class 2606 OID 137463)
-- Dependencies: 169 2048 171 2173
-- Name: fk_baeca8c1ba064b0b93d5476247c; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY koordinator
    ADD CONSTRAINT fk_baeca8c1ba064b0b93d5476247c FOREIGN KEY (login) REFERENCES login(id);


--
-- TOC entry 2122 (class 2606 OID 137652)
-- Dependencies: 167 2040 187 2173
-- Name: fk_bdcde4cb276c45b0a439c10f8e4; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY sub_district
    ADD CONSTRAINT fk_bdcde4cb276c45b0a439c10f8e4 FOREIGN KEY (district) REFERENCES district(id);


--
-- TOC entry 2100 (class 2606 OID 137540)
-- Dependencies: 2048 171 178 2173
-- Name: fk_c8250563f66243698ef0b959ca0; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY mitra
    ADD CONSTRAINT fk_c8250563f66243698ef0b959ca0 FOREIGN KEY (modifier) REFERENCES login(id);


--
-- TOC entry 2083 (class 2606 OID 137453)
-- Dependencies: 189 2080 165 2173
-- Name: fk_cee9d13ede214b22ac8f17bac75; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY address
    ADD CONSTRAINT fk_cee9d13ede214b22ac8f17bac75 FOREIGN KEY (village) REFERENCES village(id);


--
-- TOC entry 2109 (class 2606 OID 137585)
-- Dependencies: 180 2048 171 2173
-- Name: fk_e6bc7e0e26c1429badb39adaf31; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY pelatihan
    ADD CONSTRAINT fk_e6bc7e0e26c1429badb39adaf31 FOREIGN KEY (modifier) REFERENCES login(id);


--
-- TOC entry 2087 (class 2606 OID 137475)
-- Dependencies: 2052 174 171 2173
-- Name: fk_f28a12ea2bf647fa800e404457c; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY login
    ADD CONSTRAINT fk_f28a12ea2bf647fa800e404457c FOREIGN KEY (login_role) REFERENCES login_role(id);


--
-- TOC entry 2118 (class 2606 OID 137632)
-- Dependencies: 185 171 2048 2173
-- Name: fk_fcd402db1dc74a5ebc7bd277342; Type: FK CONSTRAINT; Schema: public; Owner: miles
--

ALTER TABLE ONLY peserta
    ADD CONSTRAINT fk_fcd402db1dc74a5ebc7bd277342 FOREIGN KEY (creator) REFERENCES login(id);


--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-09-19 14:09:09 WIT

--
-- PostgreSQL database dump complete
--

