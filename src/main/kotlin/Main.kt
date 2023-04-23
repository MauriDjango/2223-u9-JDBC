import dao.DAOH2
import product.Product
import services.UserServImpl
import services.UserService
import sql.utils.DataSourceFactory

fun main() {

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)
    val daoH2 = DAOH2(dataSource)
    val userServiceImpl = UserServImpl(daoH2)
    val product = Product(1, "Smartphone", 999.99f, "The latest smartphone model", "Apple", "Electronics")

    userServiceImpl.createProduct(product)

    val productId = userServiceImpl.getById(1)?.id

    println("Product ID: $productId")
}