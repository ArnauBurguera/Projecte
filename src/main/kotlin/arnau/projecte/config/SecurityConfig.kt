package arnau.projecte.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
/*
- Veure video del pavo de oath i implementar e q faci.
- https://www.youtube.com/watch?v=PczgM2L3w60 and https://www.youtube.com/watch?v=KYNR5js2cXE for more tasty security
*/

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { csrf -> csrf.disable() } // only in dev (Cross-site request forgery is enabled by default for any state-changing operations like POST, PUT, DELETE in Spring Security)
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/users/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login(withDefaults())
            .formLogin(withDefaults())
            .build()
    }
}

