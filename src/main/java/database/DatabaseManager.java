package database;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.*;
import entity.Category;
import entity.Company;
import entity.Coupon;
import entity.Customer;
import org.apache.commons.lang3.StringUtils;

public class DatabaseManager {

  private static final int MAX_DUMMY_COMPANIES = 10, MAX_DUMMY_COUPONS = 20, MAX_DUMMY_CUSTOMER = MAX_DUMMY_COUPONS / 2, MAX_PURCHASES = MAX_DUMMY_COUPONS / 2;

  public static CategoryDAO categoryDAO = new  CategoryDAOImpl();
  public static final String SCHEMA_NAME = "project_coupons";

  public static final String URL = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME +"?createDatabaseIfNotExist=TRUE";
//  public static final String url = "jdbc:mysql://localhost:3306?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";

  public static final String USERNAME = "root";

  public static final String PASSWORD = "12345678";

  public static final String CREATE_SCHEMA = "create schema `project_coupons`";

  public static final String DROP_SCHEMA = "drop schema `project_coupons`";

  private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `project_coupons`.`companies` (\n" +
      "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
      "  `name` VARCHAR(45) NOT NULL,\n" +
      "  `email` VARCHAR(45) NOT NULL,\n" +
      "  `password` VARCHAR(45) NOT NULL,\n" +
      "  PRIMARY KEY (`id`));\n";

  private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `project_coupons`.`customers` (\n" +
      "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
      "  `first_name` VARCHAR(45) NOT NULL,\n" +
      "  `last_name` VARCHAR(45) NOT NULL,\n" +
      "  `email` VARCHAR(45) NOT NULL,\n" +
      "  `password` VARCHAR(45) NOT NULL,\n" +
      "  PRIMARY KEY (`id`));\n";

  private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `project_coupons`.`categories` (\n" +
      "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
      "  `name` VARCHAR(45) NOT NULL,\n" +
      "  PRIMARY KEY (`id`));";

  private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `project_coupons`.`coupons` (\n" +
      "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
      "  `company_id` INT NOT NULL,\n" +
      "  `category_id` INT NOT NULL,\n" +
      "  `title` VARCHAR(256) NOT NULL,\n" +
      "  `description` VARCHAR(256) NOT NULL,\n" +
      "  `start_date` DATE NOT NULL,\n" +
      "  `end_date` DATE NOT NULL,\n" +
      "  `amount` INT NOT NULL,\n" +
      "  `price` DOUBLE NOT NULL,\n" +
      "  `image` VARCHAR(1000) NOT NULL,\n" +
      "  PRIMARY KEY (`id`),\n" +
      "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\n" +
      "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\n" +
      "  CONSTRAINT `company_id`\n" +
      "    FOREIGN KEY (`company_id`)\n" +
      "    REFERENCES `project_coupons`.`companies` (`id`)\n" +
      "    ON DELETE NO ACTION\n" +
      "    ON UPDATE NO ACTION,\n" +
      "  CONSTRAINT `category_id`\n" +
      "    FOREIGN KEY (`category_id`)\n" +
      "    REFERENCES `project_coupons`.`categories` (`id`)\n" +
      "    ON DELETE NO ACTION\n" +
      "    ON UPDATE NO ACTION);\n";

  private static final String CREATE_TABLE_PURCHASES = "CREATE TABLE `project_coupons`.`purchases` (\n"
      +
      "  `customer_id` INT NOT NULL,\n" +
      "  `coupon_id` INT NOT NULL,\n" +
      "  PRIMARY KEY (`customer_id`, `coupon_id`),\n" +
      "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\n" +
      "  CONSTRAINT `customer_id`\n" +
      "    FOREIGN KEY (`customer_id`)\n" +
      "    REFERENCES `project_coupons`.`customers` (`id`)\n" +
      "    ON DELETE NO ACTION\n" +
      "    ON UPDATE NO ACTION,\n" +
      "  CONSTRAINT `coupon_id`\n" +
      "    FOREIGN KEY (`coupon_id`)\n" +
      "    REFERENCES `project_coupons`.`coupons` (`id`)\n" +
      "    ON DELETE NO ACTION\n" +
      "    ON UPDATE NO ACTION);";


  private static void generateDummyCompanies() {
    for (int i = 1; i <= MAX_DUMMY_COMPANIES; i++) {
      Company company = new Company(0, "name " + i, "email" + i + "@gmail.com", "1234", null);
      try {
        CompaniesDAOImpl.getInstance().add(company);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static void generateDummyCoupons() {
    for (int i = 1; i <= MAX_DUMMY_COUPONS; i++) {
      int randomCompanyId = (int)(Math.random() * MAX_DUMMY_COMPANIES + 1);
      Coupon coupon = new Coupon(0, randomCompanyId, Category.getRandomCategory(), "title " + i, "description " + i, Date.valueOf(LocalDate.now()),
              Date.valueOf(LocalDate.now().plusDays(i)), i, i, "image " + i);
      try {
        CouponsDAOImpl.getInstance().add(coupon);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static void generateDummyCustomers() {
    for (int i = 1; i <= MAX_DUMMY_CUSTOMER; i++) {
      Customer customer = new Customer(0, "firstName " + i, "lastName " + i, "email" + i + "@gmail.com", "1234", null);
      try {
        CustomersDAOImpl.getInstance().add(customer);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private static void generateDummyPurchases() {
    for (int i = 1, j = 1; i <= MAX_PURCHASES && j <= MAX_PURCHASES; i++, j++) {
      try {
        CouponsDAOImpl.getInstance().addCouponPurchase(i, j);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void initializationDummyData() {
    generateDummyCompanies();
    generateDummyCoupons();
    generateDummyCustomers();
    generateDummyPurchases();
  }

  private static void initializationCategories() {
    for (Category category : Category.values()) {
      String name = StringUtils.capitalize(category.name().toLowerCase());
      try {
        categoryDAO.addCategory(name);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }


  public static void dropAndCreateStrategy() throws SQLException {
    JDBCUtils.runQuery(DROP_SCHEMA);
    JDBCUtils.runQuery(CREATE_SCHEMA);
    JDBCUtils.runQuery(CREATE_TABLE_CATEGORIES);
    JDBCUtils.runQuery(CREATE_TABLE_COMPANIES);
    JDBCUtils.runQuery(CREATE_TABLE_COUPONS);
    JDBCUtils.runQuery(CREATE_TABLE_CUSTOMERS);
    JDBCUtils.runQuery(CREATE_TABLE_PURCHASES);
    initializationCategories();
    initializationDummyData();
  }
}
