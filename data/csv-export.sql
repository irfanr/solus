/*
 * $Id: csv-sample.sql 4810 2011-11-20 21:18:10Z unsaved $
 *
 * Create a table, CVSV-export the data, import it back.
 */

* *DSV_COL_DELIM = ,
* *DSV_COL_SPLITTER = ,
-- Following causes a reject report to be written if there are any bad records
-- during the import.  To test it, enable the "FORCE AN ERROR" block below.
* *DSV_REJECT_REPORT = import.html

-- 1. SETTINGS
-- For applications like MS Excel, which can't import or export nulls, we have
-- to dummy down our database empty strings to export and import as if they
-- were nulls.
* *NULL_REP_TOKEN =

-- Enable following line to quote every cell value
-- * *ALL_QUOTED = true

/* Export */
--\xq address
--commit;

\xq system_lookup
commit;

--\xq login_role
--commit;

--\xq menu
--commit;

--\xq link
--commit;

--\xq menu
--commit;

--\xq country
--commit;

--\xq province
--commit;

--\xq district
--commit;

--\xq sub_district
--commit;

--\xq village
--commit;

--\xq login_links
--commit;

--\xq login_login_roles
--commit;

--\xq menu_login_roles
--commit;



