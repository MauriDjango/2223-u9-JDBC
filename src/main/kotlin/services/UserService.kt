package services

import product.Product
import java.sql.ResultSet

interface UserService {
    fun createProduct(product: Product): Int
    fun getById(id: Int): Product?
    fun createTable()
}
