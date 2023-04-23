package services

import dao.DAO
import product.Product
import java.sql.ResultSet
import java.util.*

class UserServImpl(private val dao: DAO) : UserService {
    override fun createProduct(product: Product) = dao.createProduct(product)

    override fun getById(id: Int): Product? {
        val result = dao.getById(id)
        var product: Product? = null
        if (result != null) {
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
        return product
    }
}