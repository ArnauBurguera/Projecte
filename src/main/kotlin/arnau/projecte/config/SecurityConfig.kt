
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component

@Configuration
@EnableWebSecurity
//@Component
//https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html
//https://stackoverflow.com/questions/74206724/how-to-override-securityfilterchain-in-spring-boot-context
//https://github.com/spring-projects/spring-boot/issues/33103#issuecomment-1310815121
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/users/public").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login(withDefaults())
            .formLogin(withDefaults())
            .build()
    }
}
