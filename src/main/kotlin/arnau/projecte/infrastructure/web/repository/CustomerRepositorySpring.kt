package arnau.projecte.infrastructure.web.repository

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.model.CustomerRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component
internal class CustomerRepositorySpring(private val repo: CustomerRepositoryJPA) : CustomerRepository {
    override fun findById(id: UUID): Customer? {
        return repo.findById(id).orElse(null)
    }

    override fun save(customer: Customer): Customer {
        return repo.save(customer)
    }
    fun delete(customer: Customer) {
        repo.delete(customer)
    }
}
@Repository
interface CustomerRepositoryJPA : JpaRepository<Customer, UUID> {
//Contacta amb db però no vull disposar de tots els mètodes de JPARepository
}