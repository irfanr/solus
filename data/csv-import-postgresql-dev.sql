TRUNCATE system_lookup CASCADE;

TRUNCATE login_role CASCADE;
TRUNCATE login CASCADE;
TRUNCATE permission CASCADE;
TRUNCATE link CASCADE;
TRUNCATE menu CASCADE;
TRUNCATE login_links CASCADE;
TRUNCATE login_login_roles CASCADE;
TRUNCATE login_role_permissions CASCADE;
TRUNCATE menu_login_roles CASCADE;

TRUNCATE country CASCADE;
TRUNCATE province CASCADE;
TRUNCATE district CASCADE;
TRUNCATE sub_district CASCADE;
TRUNCATE village CASCADE;

TRUNCATE category CASCADE;
TRUNCATE product CASCADE;
TRUNCATE service CASCADE;
TRUNCATE customer CASCADE;
TRUNCATE employee CASCADE;
TRUNCATE sales CASCADE;

COPY system_lookup FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/system_lookup.csv' DELIMITERS ',' NULL 'NULL' CSV;

COPY login_role FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/login_role.csv' DELIMITERS ',' CSV;
COPY login FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/login.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY permission FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/permission.csv' DELIMITERS ',' CSV;
COPY link FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/link.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY menu FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/menu.csv' DELIMITERS ',' CSV;
COPY login_role_permissions FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/login_role_permissions.csv' DELIMITERS ',' CSV;
COPY menu_login_roles FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/menu_login_roles.csv' DELIMITERS ',' CSV;

COPY country FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/country.csv' DELIMITERS ',' CSV;
COPY province FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/province.csv' DELIMITERS ',' CSV;
COPY district FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/district.DEV.csv' DELIMITERS ',' CSV;
COPY sub_district FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/sub_district.DEV.csv' DELIMITERS ',' CSV;
COPY village FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql/village.DEV.csv' DELIMITERS ',' CSV;

COPY category FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql-dev/category.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY product FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql-dev/product.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY service FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql-dev/service.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY customer FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql-dev/customer.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY employee FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql-dev/employee.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY sales FROM '/home/irfan/4-PROJECTS/solus/data/csv-postgresql-dev/sales.csv' DELIMITERS ',' NULL 'NULL' CSV;

ALTER SEQUENCE system_lookup_id_seq RESTART WITH 5;

ALTER SEQUENCE country_id_seq RESTART WITH 2;
ALTER SEQUENCE province_id_seq RESTART WITH 33;
ALTER SEQUENCE district_id_seq RESTART WITH 453;
ALTER SEQUENCE sub_district_id_seq RESTART WITH 4634;
ALTER SEQUENCE village_id_seq RESTART WITH 63294;

ALTER SEQUENCE product_id_seq RESTART WITH 14;
--ALTER SEQUENCE product_id_seq RESTART WITH 1;
ALTER SEQUENCE customer_id_seq RESTART WITH 14;
--ALTER SEQUENCE product_id_seq RESTART WITH 1;
--ALTER SEQUENCE product_id_seq RESTART WITH 1;

-- END Solus ---
