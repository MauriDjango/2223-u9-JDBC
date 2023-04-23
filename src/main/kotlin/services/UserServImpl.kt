package services

import dao.DAO
import product.Product
import java.sql.ResultSet
import java.util.*

class UserServImpl(private val dao: DAO) : UserService {
    override fun createProduct(product: Product) = dao.createProduct(product)

    override fun getById(id: Int) = dao.getById(id)

    override fun createTable() = dao.createTable()
}