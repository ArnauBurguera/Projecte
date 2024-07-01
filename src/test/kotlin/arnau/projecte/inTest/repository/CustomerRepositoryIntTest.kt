package arnau.projecte.inTest.repository

import org.springframework.test.context.ActiveProfiles
import org.springframework.boot.test.context.SpringBootTest
import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.model.CustomerRole
import arnau.projecte.domain.repository.CustomerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import java.util.UUID

@SpringBootTest
@ActiveProfiles("intTest") // Activate the "intTest" profile
class CustomerRepositoryIntTest {
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    private lateinit var customer: Customer

    @BeforeEach
    fun setup() {
        customer = Customer.Builder()
            .id(UUID.randomUUID())
            .name("Jane Doe")
            .bankAccount("123456789")
            .role(CustomerRole.USER)
            .build()
        customerRepository.save(customer)
    }

    @Test
    fun `test findById returns customer`() {
        val foundCustomer = customerRepository.findById(customer.id)
        assertNotNull(foundCustomer)
        assertEquals(customer.id, foundCustomer?.id)
    }

    @Test
    fun `test save persists customer`() {
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
    fun `test findAll returns paged results`() {
        val pageable = PageRequest.of(0, 10)
        val customersPage = customerRepository.findAll(pageable)
        assertFalse(customersPage.isEmpty)
        assertTrue(customersPage.content.size == 2)
        assertTrue(customersPage.content.contains(customer))
    }
}