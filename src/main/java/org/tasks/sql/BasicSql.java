package org.tasks.sql;

public class BasicSql {

  // just to refresh my SQL knowledge
  String sqlQ01 =
      "SELECT product_name, price, stock_quantity"
          + "FROM products"
          + "WHERE price > 100 AND stock_quantity"
          + "ORDER BY price DESC, product_name ASC";

  String sql02 =
      "SELECT"
          + "department_id,"
          + "COUNT(*) as employee_count,"
          + "AVG(salary) as avg_salary,"
          + "MAX(salary) as highest_salary,"
          + "MIN(salary) as lowest_salary"
          + "FROM employees"
          + "GROUP BY department_id"
          + "HAVING COUNT(*) > 5";

  String sql03 =
      // INNER JOIN
      "SELECT "
          + "e.first_name,"
          + "e.last_name,"
          + "d.department_name"
          + "FROM employees e"
          + "INNER JOIN departments d ON e.department_id = d.department_id;";

  String sql04 =
      // LEFT JOIN with multiple conditions"
      "SELECT"
          + "c.customer_name,"
          + "o.order_date,"
          + "o.total_amount"
          + "FROM customers c"
          + "LEFT JOIN orders o ON c.customer_id = o.customer_id"
          + "AND o.order_date >= '2024-01-01';";

  String sql05 =
      "SELECT first_name, last_name, salary"
          + "FROM employees"
          + "WHERE salary > ("
          + "    SELECT AVG(salary)"
          + "    FROM employees"
          + ");";

  String sql06 =
  // INNER JOIN
      "SELECT e.name, d.department_name"
          + "FROM employees e"
          + "INNER JOIN departments d ON e.department_id = d.department_id;";

  // LEFT JOIN
  String sql07 =
      "SELECT e.name, d.department_name"
          + "FROM employees e"
          + "LEFT JOIN departments d ON e.department_id = d.department_id;";

}
