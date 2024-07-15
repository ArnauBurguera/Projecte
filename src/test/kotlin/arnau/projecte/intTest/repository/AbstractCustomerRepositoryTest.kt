package arnau.projecte.intTest.repository

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.model.CustomerRole
import arnau.projecte.domain.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.data.domain.PageRequest

@SpringBootTest
@ActiveProfiles("test")
abstract class AbstractCustomerRepositoryTest : CustomerRepositoryTest{
    @Autowired
    lateinit var customerRepository: CustomerRepository

    lateinit var customer: Customer

    @BeforeEach
    override fun setup() {
        customer = Customer.Builder()
            .id(UUID.randomUUID())
            .name("Jane Doe")
            .bankAccount("123456789")
            .role(CustomerRole.USER)
            .build()
        customerRepository.save(customer)
    }

    @AfterEach
    override fun tearDown() {
        customerRepository.clearDB()
    }

    @Test
    override fun testFindByIdReturnsCustomer() {
        val foundCustomer = customerRepository.findById(customer.id)
        assertNotNull(foundCustomer)
        assertEquals(customer.id, foundCustomer?.id)
    }

    @Test
    override fun testSavePersistsCustomer() {
        val newCustomer = Customer.Builder()
            .id(UUID.randomUUID())
            .name("John Doe")
            .bankAccount("987654321")
            .role(CustomerRole.USER)
            .build()
        val savedCustomer = customerRepository.save(newCustomer)
        assertNotNull(savedCustomer)
        assertEquals(newCustomer.id, savedCustomer.id)
    }

    @Test
    override fun testFindAllReturnsPagedResults() {
        val pageable = PageRequest.of(0, 10)
        val customersPage = customerRepository.findAll(pageable)
        assertFalse(customersPage.isEmpty)
        assertTrue(customersPage.content.size == 1)
        assertTrue(customersPage.content.contains(customer))
    }
}