package info.maila.baseapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SpringSecurityConfiguration {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/**").hasAnyAuthority("public", "user", "admin")
                    .requestMatchers("/public/**").permitAll()
                    .anyRequest().permitAll()
            }
            .oauth2Login(withDefaults())
            .build()
    }

    @Bean
    @Suppress("UNCHECKED_CAST")
    fun grantedAuthoritiesMapper() = GrantedAuthoritiesMapper { authorities ->
        authorities
            .filterIsInstance<OidcUserAuthority>()
            .map { authority: OidcUserAuthority ->
                val map: Map<String, Any>? = authority.idToken.claims["realm_access"] as? Map<String, Any>
                val realmAccess = map ?: return@GrantedAuthoritiesMapper emptySet()
                val roles = realmAccess["roles"] as List<String>
                roles.map { role -> SimpleGrantedAuthority(role) }
            }
            .flatten().toSet()
    }

}
