package info.maila.baseapp.domain.animal

import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table(name = "animal", schema = "baseapp")
data class Animal(

    @Id
    val id: Long? = null,

    @NotBlank
    val name: String? = null,

    @NotBlank
    val type: String? = null,

    @Version
    val version: Int? = null

) {

    infix fun updateWith(other: Animal): Animal =
        copy(name = other.name, type = other.type, version = other.version)

}