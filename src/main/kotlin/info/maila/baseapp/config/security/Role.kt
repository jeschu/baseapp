package info.maila.baseapp.config.security

import kotlin.enums.enumEntries

enum class Role(val role: String) {

    RESOURCE_BASEAPP_PUBLIC("resource.baseapp.public"),
    RESOURCE_BASEAPP_ADMIN("resource.baseapp.admin"),
    RESOURCE_BASEAPP_EDITOR("resource.baseapp.editor");

    companion object {
        fun allRoles() = enumEntries<Role>().map(Role::role).toTypedArray()
        fun roles(vararg roles: Role) = enumEntries<Role>().filter { it in roles }.map(Role::role).toTypedArray()
    }

}