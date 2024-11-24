package arnau.projecte.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "customers")
data class Customer(
        @Id val id: UUID = UUID.randomUUID(),
        val name: String,
        val bankAccount: String,
        @Enumerated(EnumType.STRING)
        val role: CustomerRole
) {
    constructor() : this(UUID.randomUUID(), "", "", CustomerRole.USER)//JPA requires that all entity classes have a no-argument constructor.

    class Builder {
        private var id: UUID = UUID.randomUUID()
        private var firstName: String = ""
        private var bankAccount: String = ""
        private var role: CustomerRole = CustomerRole.USER

        fun id(id: UUID) = apply { this.id = id }
        fun name(name: String) = apply { this.firstName = name }
        fun bankAccount(bankAccount: String) = apply { this.bankAccount = bankAccount }
        fun role(role: CustomerRole) = apply { this.role = role }

        fun build() = Customer(id, firstName, bankAccount, role)
    }
}

enum class CustomerRole {
    USER,
    ADMIN,
    MODERATOR
}