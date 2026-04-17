package info.maila.baseapp.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.web.SecurityFilterChain

@Profile("no-security")
@Configuration
@EnableWebSecurity
class SpringSecurityLocalConfiguration {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .csrf(CsrfConfigurer<HttpSecurity>::disable)
            .authorizeHttpRequests { it.anyRequest().permitAll() }
            .build()

}