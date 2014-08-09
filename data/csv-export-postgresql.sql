--TRUNCATE mitra CASCADE;
--TRUNCATE pelatih CASCADE;
--TRUNCATE pelatihan CASCADE;
--TRUNCATE peserta CASCADE;

--COPY mitra TO '/home/irfan/4-PROJECTS/miles/data/csv-postgresql/mitra.csv' DELIMITERS ',' NULL 'NULL' CSV;
--COPY pelatih TO '/home/irfan/4-PROJECTS/miles/data/csv-postgresql/pelatih.csv' DELIMITERS ',' CSV;
--COPY pelatihan TO '/home/irfan/4-PROJECTS/miles/data/csv-postgresql/pelatihan.csv' DELIMITERS ',' NULL 'NULL' CSV;
--COPY peserta TO '/home/irfan/4-PROJECTS/miles/data/csv-postgresql/peserta.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY login_role_permissions TO '/home/irfan/4-PROJECTS/miles/data/login_role_permissions.csv' DELIMITERS ',' NULL 'NULL' CSV;
COPY menu_login_roles TO '/home/irfan/4-PROJECTS/miles/data/menu_login_roles.csv' DELIMITERS ',' NULL 'NULL' CSV;
