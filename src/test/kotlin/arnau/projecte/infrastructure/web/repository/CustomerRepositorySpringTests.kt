package arnau.projecte.infrastructure.web.repository

import arnau.projecte.domain.model.Customer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class CustomerRepositorySpringTests {

    @Mock
    private lateinit var customerRepositoryJPA: CustomerRepositoryJPA

    @InjectMocks
    private lateinit var customerRepositorySpring: CustomerRepositorySpring

    @Test
    fun `should find customer by ID`() {
        val customerId = UUID.randomUUID()
        val customer = Customer.Builder().id(customerId).name("Jane Doe").build()

        whenever(customerRepositoryJPA.findById(customerId)).thenReturn(Optional.of(customer))

        val result = customerRepositorySpring.findById(customerId)

        verify(customerRepositoryJPA).findById(customerId)
        assert(result == customer)
    }

    @Test
    fun `should save customer`() {
        val customer = Customer.Builder().name("Jane Doe").build()

        whenever(customerRepositoryJPA.save(customer)).thenReturn(customer)

        val savedCustomer = customerRepositorySpring.save(customer)

        verify(customerRepositoryJPA).save(customer)
        assert(savedCustomer == customer)
    }
}
