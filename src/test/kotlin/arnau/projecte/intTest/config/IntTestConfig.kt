package arnau.projecte.intTest.config

import arnau.projecte.domain.repository.CustomerRepository
import arnau.projecte.domain.process.InMemoryCustomerRepository
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
@Profile("test") // This configuration will be used when the "test" profile is active
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
    @Qualifier("jpaRepository")
    fun customerRepositoryJPA(): CustomerRepository {
        return CustomerRepositorySpring(repo)
    }
}