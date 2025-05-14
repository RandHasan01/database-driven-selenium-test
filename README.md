# Database-Driven Selenium Test

This is a Java-based automation project that integrates Selenium WebDriver with a MySQL database using JDBC. The goal is to automate web form submissions by fetching dynamic data from the database.

## Features

- Connects to MySQL using JDBC
- Performs CRUD operations (INSERT, UPDATE, SELECT, DELETE)
- Automates filling forms on SmartBuy website using dynamic data
- Uses TestNG for managing and structuring tests

## Test Flow

1. Insert a customer into the database
2. Update the customer’s data
3. Fetch customer data from the database
4. Open the SmartBuy registration page
5. Automatically fill the registration form
6. Delete the test customer from the database

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- JDBC
- MySQL
- ChromeDriver
