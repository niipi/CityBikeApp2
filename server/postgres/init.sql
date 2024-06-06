CREATE USER springboot WITH PASSWORD '${SPRINGBOOT_PW}';
CREATE USER rscript WITH PASSWORD '${RSCRIPT_PW}';

CREATE DATABASE citybikedata;
GRANT CONNECT ON DATABASE citybikedata TO springboot;
GRANT CONNECT ON DATABASE citybikedata TO rscript;

CREATE DATABASE citybikepred;
GRANT CONNECT ON DATABASE citybikepred TO springboot;
GRANT CONNECT ON DATABASE citybikepred TO rscript;