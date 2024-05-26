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
        @Id val id: UUID,
        val name: String,
        val bankAccount: String,
        @Enumerated(EnumType.STRING)
        val role: CustomerRole
) {
    constructor() : this(UUID.randomUUID(), "", "", CustomerRole.CUSTOM)
}

enum class CustomerRole {
    CUSTOM,
    ADMIN,
    MODERATOR
}