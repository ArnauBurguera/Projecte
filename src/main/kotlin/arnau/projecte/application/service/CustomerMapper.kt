package arnau.projecte.application.service

import arnau.projecte.dto.CustomerDTO
import arnau.projecte.domain.model.Customer
import arnau.projecte.domain.model.CustomerRole
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CustomerMapper {

    fun toCustomer(customerDTO: CustomerDTO): Customer {
        return Customer.Builder()
            .id(UUID.randomUUID())
            .name(customerDTO.name)
            .bankAccount(customerDTO.bankAccount)
            .role(CustomerRole.USER)
            .build()
    }
}