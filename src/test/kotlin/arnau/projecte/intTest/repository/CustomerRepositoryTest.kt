package arnau.projecte.intTest.repository

interface CustomerRepositoryTest {
    fun setup()
    fun tearDown()
    fun testFindByIdReturnsCustomer()
    fun testSavePersistsCustomer()
    fun testFindAllReturnsPagedResults()
}