package arnau.projecte.intTest.process

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.repository.CustomerRepository
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class InMemoryCustomerRepository : CustomerRepository {
    private val customers = ConcurrentHashMap<UUID, Customer>()

    override fun findById(id: UUID): Customer? = customers[id]

    override fun save(customer: Customer): Customer {
        customers[customer.id] = customer
        return customer
    }

    override fun findAll(pageable: Pageable): Page<Customer> {
        val customersList = customers.values.toList()
        val start = pageable.offset.toInt()
        val end = (start + pageable.pageSize).coerceAtMost(customersList.size)//coerceAtMost ensures that the end index does not exceed the size of the list, preventing an IndexOutOfBoundsException
        return PageImpl(customersList.subList(start, end), pageable, customersList.size.toLong())
    }

    override fun clearDB() {
        customers.clear()
    }
}