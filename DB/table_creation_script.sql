
CREATE TABLE news_app_india.categories(
   id          SERIAL		PRIMARY KEY,
   category    VARCHAR(50)	NOT NULL UNIQUE,
   is_enabled  BOOLEAN		DEFAULT TRUE
);

SELECT * FROM news_app_india.categories

--------------------------

CREATE TABLE news_app_india.news(
  id			SERIAL		PRIMARY KEY,
  headlines 		VARCHAR(250),
  short_description 	VARCHAR(500),
  created_on 		TIMESTAMP,
  url 			VARCHAR,
  image_url 		VARCHAR,
  website_name 		VARCHAR,
  category 		VARCHAR(50) REFERENCES news_app_india.categories(category)
)

----------------------------

CREATE TABLE news_app_tenants.tenants(
  tenant_id		VARCHAR(3)	PRIMARY KEY,
  db_driver		VARCHAR		DEFAULT 'org.postgresql.Driver',
  db_url		VARCHAR,
  db_user		VARCHAR,
  db_pass		VARCHAR
)

---------------------------

CREATE SCHEMA newdb WITH TEMPLATE olddb;

--------------------

CREATE TABLE news_app_brazil.categories (LIKE news_app_india.categories INCLUDING ALL);
CREATE TABLE news_app_brazil.news (LIKE news_app_india.news INCLUDING ALL);

CREATE TABLE news_app_us.categories (LIKE news_app_india.categories INCLUDING ALL);
CREATE TABLE news_app_us.news (LIKE news_app_india.news INCLUDING ALL);

CREATE TABLE news_app_uk.categories (LIKE news_app_india.categories INCLUDING ALL);
CREATE TABLE news_app_uk.news (LIKE news_app_india.news INCLUDING ALL);

CREATE TABLE news_app_australia.categories (LIKE news_app_india.categories INCLUDING ALL);
CREATE TABLE news_app_australia.news (LIKE news_app_india.news INCLUDING ALL);

CREATE TABLE news_app_japan.categories (LIKE news_app_india.categories INCLUDING ALL);
CREATE TABLE news_app_japan.news (LIKE news_app_india.news INCLUDING ALL);



