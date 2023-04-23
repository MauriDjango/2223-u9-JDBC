import dao.DAOH2
import product.Product
import services.UserServImpl
import services.UserService
import sql.utils.DataSourceFactory

fun main() {

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.Embedded)
    val daoH2 = DAOH2(dataSource)
    val userServiceImpl = UserServImpl(daoH2)
    val product = Product(6, "Smartphone", 999.99f, "The latest smartphone model", "Apple", "Electronics")

    val createResult = userServiceImpl.createProduct(product)
    println(createResult)

    val productId = userServiceImpl.getById(2)?.id
    println("Product ID: $productId")
}