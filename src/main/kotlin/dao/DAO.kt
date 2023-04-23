package dao

import product.Product
import java.sql.ResultSet

interface DAO {
    fun createProduct(product: Product): Int
    fun getById(id: Int): ResultSet?
}