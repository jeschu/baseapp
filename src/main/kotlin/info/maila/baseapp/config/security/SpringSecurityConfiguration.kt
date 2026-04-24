package info.maila.baseapp.config.security

import info.maila.baseapp.config.security.Role.RESOURCE_BASEAPP_PUBLIC
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority
import org.springframework.security.web.SecurityFilterChain

@Profile("!no-security")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SpringSecurityConfiguration {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/", "/favicon.ico").permitAll()
                    //.requestMatchers("/**").hasAnyAuthority(RESOURCE_BASEAPP_PUBLIC)
                    //.requestMatchers("/api/**").permitAll() // TODO
                    //.requestMatchers("/public/**").permitAll()
                    .anyRequest().hasAnyAuthority(RESOURCE_BASEAPP_PUBLIC)
            }
            .logout { logout -> logout.logoutSuccessUrl("/") }
            .oauth2Login(Customizer.withDefaults())
            .build()
    }

    @Bean
    @Suppress("UNCHECKED_CAST")
    fun grantedAuthoritiesMapper() = GrantedAuthoritiesMapper { authorities ->
        authorities
            .filterIsInstance<OidcUserAuthority>()
            .flatMap { authority: OidcUserAuthority ->

                val roles = mutableListOf<SimpleGrantedAuthority>()

                val claims = authority.idToken.claims

                val realmAccess: Map<String, Any>? = claims.getMapByKey("realm_access")
                val realmAccessRoles: List<String>? = realmAccess
                    ?.getListByKey("roles")?.map { role -> "realm.$role" }

                val resourceAccess: Map<String, Any>? = claims.getMapByKey("resource_access")
                val resourceAccessRoles: List<String>? = resourceAccess?.keys
                    ?.mapNotNull { key ->
                        resourceAccess
                            .getMapByKey(key)
                            .getListByKey("roles")
                            ?.map { role -> "resource.$key.$role" }
                    }
                    ?.flatten()

                realmAccessRoles?.map(::SimpleGrantedAuthority)?.forEach(roles::add)
                resourceAccessRoles?.map(::SimpleGrantedAuthority)?.forEach(roles::add)

                roles
            }
            .toSet()
    }

    @Suppress("UNCHECKED_CAST")
    private infix fun Map<String, Any>?.getMapByKey(key: String): Map<String, Any>? =
        this?.get(key) as? Map<String, Any>

    @Suppress("UNCHECKED_CAST")
    private infix fun Map<String, Any>?.getListByKey(key: String): List<String>? =
        this?.get(key) as? List<String>

}