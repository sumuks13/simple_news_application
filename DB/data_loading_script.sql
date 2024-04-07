INSERT INTO news_app_tenants.tenants
(tenant_id, db_url, db_user, db_pass)
VALUES('in', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_india', 'id', 'password'),
	('br', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_brazil', 'id', 'password'),
	('jp', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_japan', 'id', 'password'),
    ('au', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_australia', 'id', 'password'),
    ('us', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_us', 'id', 'password'),
    ('gb', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_uk', 'id', 'password'),
    ('def', 'jdbc:postgresql://localhost:5432/simple_news_app?currentSchema=news_app_tenants', 'id', 'password');

-------------------------------

INSERT INTO news_app_india.categories
(category)
VALUES('business'),('general'),('science'),('sports'),('technology');