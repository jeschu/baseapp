package info.maila.baseapp.domain.animal.model

import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table(name = "animal", schema = "baseapp")
data class Animal(

    @Id
    val id: Long? = null,

    @Version
    val dbVersion: Int? = null,

    @NotBlank
    val name: String? = null,

    val type: String? = null

) {

    infix fun updateWith(other: Animal): Animal =
        copy(name = other.name, type = other.type, dbVersion = other.dbVersion)

}