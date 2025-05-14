package APIProject.APIProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {

	WebDriver driver = new ChromeDriver();
	String smartbuyRegisterUrl = "https://smartbuy-me.com/account/register";

	// to connect to the database
	Connection con;
	// used to execute SQL queries (insert, update, delete, select)
	Statement stmt;
	// to hold data returned from SELECT query
	ResultSet rs;
	String customerFirstName;
	String customerLastName;
	String customerEmail;
	String customerPassword;

	@BeforeTest
	public void mySetup() throws SQLException {
		driver.get(smartbuyRegisterUrl);
		// JDBC is used to connect Java (Selenium) with the database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "012345");
	}

	@Test(priority = 1)
	public void insertIntoDatabase() throws SQLException {

		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00)";
		stmt = con.createStatement();
		// Used when performing write operations on the database (INSERT, UPDATE,
		// DELETE)
		int rowAffected = stmt.executeUpdate(query);
		System.out.println("INSERT query " + rowAffected + " row(s) affected");
	}

	@Test(priority = 2)
	public void updateDatabase() throws SQLException {

		String query = "UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int rowAffected = stmt.executeUpdate(query);
		System.out.println("UPDATE query " + rowAffected + " row(s) affected");
	}

	@Test(priority = 3)
	public void readFromDatabase() throws SQLException {

		String query = "SELECT * FROM customers WHERE customerNumber = 103;";
		stmt = con.createStatement();
		// Used to fetch data from the database using SELECT queries
		rs = stmt.executeQuery(query);
		/*
		 * Loop through the rs as long as there are rows available while for more than
		 * one row
		 */

		while (rs.next()) {
			// (column name) here is string because the data type of this column is string
			customerFirstName = rs.getString("contactFirstName");
			customerLastName = rs.getString("contactLastName");
			customerPassword = rs.getString("country") + rs.getString("customerNumber");

		}

		customerEmail = customerFirstName.trim() + customerLastName + "@gmail.com";
		System.out.println(customerFirstName + " " + customerLastName + " " + customerEmail + " " + customerPassword);

		WebElement firstNameField = driver.findElement(By.id("customer[first_name]"));
		firstNameField.sendKeys(customerFirstName);

		WebElement lastNameField = driver.findElement(By.id("customer[last_name]"));
		lastNameField.sendKeys(customerLastName);

		WebElement emailField = driver.findElement(By.id("customer[email]"));
		emailField.sendKeys(customerEmail);

		WebElement passwordField = driver.findElement(By.id("customer[password]"));
		passwordField.sendKeys(customerPassword);
	}

	@Test(priority = 4)
	public void deleteDataFromDatabase() throws SQLException {

		String query = "DELETE FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		int rowAffected = stmt.executeUpdate(query);
		System.out.println("DELETE query " + rowAffected + " row(s) affected");
	}
}
