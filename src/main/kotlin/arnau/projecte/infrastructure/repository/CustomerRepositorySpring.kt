package arnau.projecte.infrastructure.repository

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.repository.CustomerRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Component
internal class CustomerRepositorySpring(private val repo: CustomerRepositoryJPA) : CustomerRepository {
    override fun findById(id: UUID): Customer? {
        return repo.findById(id).orElse(null)
    }

    override fun save(customer: Customer): Customer {
        return repo.save(customer)
    }

    override fun findAll(pageable: Pageable): Page<Customer> {
        return repo.findAll(pageable)
    }
}
@Repository
interface CustomerRepositoryJPA : JpaRepository<Customer, UUID> {
//Contacta amb db però no vull disposar de tots els mètodes de JPARepository
}