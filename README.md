1. install postgres sql 17: 

sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'
sudo apt install postgresql-17
sudo systemctl start postgresql
sudo systemctl enable postgresql
sudo -u postgres psql

2. set up database:
CREATE DATABASE vaccination_portal; 

3. replace following db username and password at src/main/resources/application.properties :

spring.datasource.username=postgres
spring.datasource.password=postgres
if postgres port is different other then 5432 update this property spring.datasource.url=jdbc:postgresql://localhost:5432/vaccination_portal

4. run the backend by running this entry point for backend 
backend/src/main/java/bits/wilp/FullStackDevelopment/SchoolVaccinationPortalApplication.java

5. run the frontend using following commands: 
cd frontend/
npm install axios react-router-dom bootstrap
npm run dev

6. use follwoing username and password to login
user: admin
password: password123
