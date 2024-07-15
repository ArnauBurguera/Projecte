package arnau.projecte.intTest.repository

import arnau.projecte.domain.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class CustomerRepositoryInMemoryTest : AbstractCustomerRepositoryTest() {
    @Autowired
    @Qualifier("inMemoryRepository")
    override lateinit var customerRepository: CustomerRepository
}