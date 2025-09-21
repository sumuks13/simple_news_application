# Simple News Application

A multi-tenant java application that utilizes a scheduler to concurrently gather and store news from news API.

- **Database Structure**:
    - There is a single database used for the simple news application.

- **Multitenancy Design**:
    - Multitenancy is implemented using a "tenant per schema" approach.
    - This means there are `n` separate schemas, each dedicated to a different tenant.
 
  <br/> <img width="330" height="395" alt="image" src="https://github.com/user-attachments/assets/4f88f84c-3fe0-4276-b582-8b45c049202f" />

- **Schema Details**:
    - **Master Schema**:
        - Contains connection details for all tenant schemas.
    - **Tenant Schemas**:
        - Each tenant has its own dedicated schema.
        - Contains:
            - A table to store news data specific to that tenant.
            - A configuration table listing categories of news enabled for that tenant.
  
  <br/> <img width="1239" height="794" alt="image" src="https://github.com/user-attachments/assets/d074c620-ea5d-4d44-bb3d-1449353810d8" />

Application Flow:

1. The simple news application runs everyday based on a specified **Cron expression**. The scheduler accesses the **master schema** to retrieve tenant DB connection details. For each tenant, the application establishes a dedicated connection pool to their respective database.
2. Utilizing a fixed thread pool from an **ExecutorService**, the application spawns `n` threads, each corresponding to a different tenant.
3. Each thread fetches news data from **News API** parallelly, focusing on all categories to the respective tenant.
4. Post fetching, the retrieved news data is stored in the database allocated to each tenant, ensuring data segregation.

<br/> <img width="900" height="699" alt="image" src="https://github.com/user-attachments/assets/0510c870-a7b2-43c2-9802-3a2a5add6360" />

### References:

- [Multi-Tenancy](https://medium.com/swlh/multi-tenancy-implementation-using-spring-boot-hibernate-6a8e3ecb251a)
- [News API](https://newsapi.org/docs/endpoints/top-headlines)

