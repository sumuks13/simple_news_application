CREATE USER admin WITH ENCRYPTED PASSWORD 'admin@123'

CREATE SCHEMA news_application_tenant AUTHORIZATION admin;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA news_application_tenant TO admin;
GRANT USAGE ON SCHEMA news_application_tenant TO admin;

GRANT ALL PRIVILEGES ON DATABASE news_application TO admin;

---------------------

CREATE SEQUENCE news_application_tenant.id_generator_seq
START 1
INCREMENT 1

CREATE SEQUENCE news_application_tenant.news_id_seq
START 1
INCREMENT 1

CREATE SEQUENCE news_application_tenant.categories_id_seq
START 1
INCREMENT 1

----------------------

CREATE OR REPLACE FUNCTION news_application_tenant.delete_old_records_after_insert()
RETURNS TRIGGER AS $$
BEGIN
    -- Delete old records based on a condition
    -- For example, delete records older than  30 days
    DELETE FROM news_application_tenant.news WHERE created_on < (NOW() - INTERVAL '7 days');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Step  2: Create the Trigger
CREATE TRIGGER delete_old_records_trigger
AFTER INSERT ON news_application_tenant.news
FOR EACH ROW EXECUTE FUNCTION news_application_tenant.delete_old_records_after_insert();
