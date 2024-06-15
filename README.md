# Simple News Application

A multi-tenant java application that utilizes a scheduler to concurrently gather and store news from news API.

- **Database Structure**:
    - There is a single database used for the simple news application.

- **Multitenancy Design**:
    - Multitenancy is implemented using a "tenant per schema" approach.
    - This means there are `n` separate schemas, each dedicated to a different tenant.

  ![app](https://drive.google.com/uc?export=view&id=1cvpDVHUcYYHwcBnQCMfpN1xyhl1t6Q1L)

- **Schema Details**:
    - **Common Schema**:
        - Contains connection details for all tenant schemas.
    - **Tenant Schemas**:
        - Each tenant has its own dedicated schema.
        - Contains:
            - A table to store news data specific to that tenant.
            - A configuration table listing categories of news enabled for that tenant.
  
  ![app](https://drive.google.com/uc?export=view&id=1pvMZTa9ljzzIPlb51UiyS7lAZ7fxe_rJ)


Application Flow:

![app](https://drive.google.com/uc?export=view&id=1WDwHFZzPfYiyWg-Vp6PtZ3LMkyqtFlWI)

1. The simple news application runs everyday based on a specified **Cron expression**. The scheduler accesses a **common schema** to retrieve tenant DB connection details. For each tenant, the application establishes a dedicated connection pool to their respective database.
2. Utilizing a fixed thread pool from an **ExecutorService**, the application spawns `n` threads, each corresponding to a different tenant.
3. Each thread fetches news data from **News API** parallelly, focusing on all categories to the respective tenant.
4. Post fetching, the retrieved news data is stored in the database allocated to each tenant, ensuring data segregation.


### References:

- [Multi-Tenancy](https://medium.com/swlh/multi-tenancy-implementation-using-spring-boot-hibernate-6a8e3ecb251a)
- [News API](https://newsapi.org/docs/endpoints/top-headlines)

