package dao

import product.Product
import java.sql.ResultSet
import java.util.*
import javax.sql.DataSource

class DAOH2(private val dataSource: DataSource): DAO {
    override fun createProduct(product: Product): Int {
        val sql = "INSERT INTO products (id, name, price, description, brand, category) VALUES (?, ?, ?, ?, ?, ?)"
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

    override fun getById(id: Int): ResultSet? {
        val sql = "SELECT * FROM products WHERE id = ?"
        dataSource.connection.use {conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val result = stmt.executeQuery()
                if (result.fetchSize == 0)
                    return null
                else return result
            }
        }
    }
}
