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



-- 5. CSV IMPORT
\p data//csv//system_lookup.csv
\mq data//csv//system_lookup.csv
\p

\p data//csv//address.csv
\mq data//csv//address.csv
\p

\p data//csv//login.csv
\mq data//csv//login.csv
\p

\p data//csv//login_role.csv
\mq data//csv//login_role.csv
\p

\p data//csv//link.csv
\mq data//csv//link.csv
\p

\p data//csv//menu.csv
\mq data//csv//menu.csv
\p

\p data//csv//country.csv
\mq data//csv//country.csv
\p

\p data//csv//province.csv
\mq data//csv//province.csv
\p

\p data//csv//district.csv
\mq data//csv//district.csv
\p

\p data//csv//sub_district.csv
\mq data//csv//sub_district.csv
\p

\p data//csv//village.csv
\mq data//csv//village.csv
\p

\p data//csv//login_links.csv
\mq data//csv//login_links.csv
\p

\p data//csv//login_login_roles.csv
\mq data//csv//login_login_roles.csv
\p

\p data//csv//menu_login_roles.csv
\mq data//csv//menu_login_roles.csv
\p

\p data//csv//person.csv
\mq data//csv//person.csv
\p

commit;

