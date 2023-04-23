package dao

import product.Product
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

class DAOH2(private val dataSource: DataSource): DAO {
    override fun createProduct(product: Product): Int {
        val sql = "INSERT INTO PRODUCTS (id, name, price, description, brand, category) VALUES (?, ?, ?, ?, ?, ?)"
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, product.id.toString())
                stmt.setString(2, product.name)
                stmt.setString(3, product.price.toString())
                stmt.setString(4, product.description)
                stmt.setString(5, product.brand)
                stmt.setString(6, product.category)

                return stmt.executeUpdate()
            }
        }
    }

    override fun getById(id: Int): Product? {
        val sql = "SELECT * FROM products WHERE ID = ?"
        var product: Product? = null
        dataSource.connection.use {conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val result = stmt.executeQuery()
                    if (result.next()) {
                        //TODO This portion could have a problem if the id is ever repeated
                        product = Product(
                            id = result.getInt("id"),
                            name = result.getString("name"),
                            price = result.getFloat("price"),
                            description = result.getString("description"),
                            brand = result.getString("brand"),
                            category = result.getString("category")
                        )
                    }
                }
            }
            return product
        }

    override fun createTable() {
        val sql =
            "CREATE TABLE products (id INT PRIMARY KEY, name VARCHAR(50), price FLOAT, description VARCHAR(100), brand VARCHAR(50), category VARCHAR(50));"
        dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val result = stmt.executeUpdate()
            }
        }
    }
}
