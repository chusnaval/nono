
CREATE ROLE nono_query_role WITH
  NOLOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

CREATE ROLE nono_role WITH
  NOLOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

CREATE ROLE nono_pool WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

GRANT nono_role TO nono_pool;

CREATE ROLE nono WITH
  NOLOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;
  
CREATE TABLESPACE nono_ts_idx
  OWNER postgres
  LOCATION 'C:\pg_tablespaces\nono\idx';

ALTER TABLESPACE nono_ts_idx
  OWNER TO postgres;
  

GRANT CREATE ON TABLESPACE nono_ts_idx TO postgres;

GRANT CREATE ON TABLESPACE nono_ts_idx TO nono;


CREATE TABLESPACE nono_ts_dat
  OWNER postgres
  LOCATION 'C:\pg_tablespaces\nono\dat';

ALTER TABLESPACE nono_ts_dat
  OWNER TO postgres;

GRANT CREATE ON TABLESPACE nono_ts_dat TO postgres;

GRANT CREATE ON TABLESPACE nono_ts_dat TO nono;

CREATE DATABASE nono
    WITH 
    OWNER = nono
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = nono_ts_dat
    CONNECTION LIMIT = -1;


CREATE SCHEMA nono;


ALTER SCHEMA nono OWNER TO nono;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE nono.rss_channels (
    node_id integer NOT NULL,
    description character varying(100) NOT NULL,
    rss character varying(200) NOT NULL,
    class_code character varying(36) NOT NULL,
    leaf boolean NOT NULL,
    parent_id integer,
    user_id character varying(20) NOT NULL
);


ALTER TABLE nono.rss_channels OWNER TO nono;

COMMENT ON TABLE nono.rss_channels IS 'Keeps rss channels';


ALTER TABLE ONLY nono.rss_channels
    ADD CONSTRAINT "RssChannels_pkey" PRIMARY KEY (node_id);


CREATE INDEX "fki_RssChannels_fkey" ON nono.rss_channels USING btree (parent_id);


ALTER TABLE ONLY nono.rss_channels
    ADD CONSTRAINT "RssChannels_fkey" FOREIGN KEY (parent_id) REFERENCES nono.rss_channels(node_id);


GRANT USAGE ON SCHEMA nono TO nono_role;
GRANT USAGE ON SCHEMA nono TO nono_query_role;


GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE nono.rss_channels TO nono_role;
GRANT SELECT ON TABLE nono.rss_channels TO nono_query_role;


-- example
COPY nono.rss_channels (node_id, description, rss, class_code, leaf, parent_id, user_id) FROM stdin;
1	La escóbula de la brújula	http://fapi-top.prisasd.com/podcast/podium/la_escobula_de_la_brujula.xml	e1	f	\N	chus
\.



