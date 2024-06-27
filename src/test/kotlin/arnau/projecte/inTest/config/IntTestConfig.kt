package arnau.projecte.inTest.config

import arnau.projecte.domain.repository.CustomerRepository
import arnau.projecte.domain.process.InMemoryCustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("intTest") // This configuration will be used when the "intTest" profile is active
class IntTestConfig {

    @Bean
    @Primary // This bean will take precedence over other beans of the same type
    fun customerRepository(): CustomerRepository {
        return InMemoryCustomerRepository()
    }
}