package arnau.projecte.infrastructure.web.repository

import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.repository.CustomerRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Component//Millor customrepoimplementation
internal class JPACustomerRepository(private val repo: CustomerSpringRepository) : CustomerRepository{
    override fun findById(id: UUID): Customer? {
        return repo.findById(id).orElse(null)
    }

    override fun save(customer: Customer): Customer {
        return repo.save(customer)
    }
    fun delete (customer: Customer){//Pq implementar el domain repo si puc fer això si vull? A qui li importa el domain?
        repo.delete(customer)
    }
}
@Repository
interface CustomerSpringRepository : JpaRepository<Customer, UUID> {//Millor anomenar JPARepo
//Contacta amb db però no vull disposar de tots els mètodes de JPARepository
}