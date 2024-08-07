package arnau.projecte.intTest.config

import arnau.projecte.domain.repository.CustomerRepository
import arnau.projecte.intTest.process.InMemoryCustomerRepository
import arnau.projecte.infrastructure.repository.CustomerRepositoryJPA
import arnau.projecte.infrastructure.repository.CustomerRepositorySpring
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("test") // This configuration will be used when the "test" profile is active: application-{profile}.properties
class IntTestConfig(
    @Autowired
    private val repo: CustomerRepositoryJPA
) {

    @Bean
    @Primary // This bean will take precedence over other beans of the same type
    @Qualifier("inMemoryRepository")
    fun customerRepository(): CustomerRepository {
        return InMemoryCustomerRepository()
    }

    @Bean
    @Qualifier("H2")
    fun customerRepositoryJPAH2(): CustomerRepository {//Needs to be called differently from actual customerRepositoryJPA bean
        return CustomerRepositorySpring(repo)
    }
}