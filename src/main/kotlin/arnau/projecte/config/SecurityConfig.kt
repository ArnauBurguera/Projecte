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
/*
- Fer oauth amb google.
- Intentar veure què fa internament spring security
-Afegir docker-compose-local. On nomes tingui db per tal de que un cambio al jar i una nova build no requereixi de muntar tota la db again i només corro la app en intellij.

-Un bash script(que es com una task en llenguatge maquina) per fer el build automaticament.
-I un altre bash script per insertar 1mil usuaris a la db amb un init.sql o algo així, no cal que sigui migration de moment només crearlos amb un random UUID i
   que faci cherrypicking d'una llita de 5 noms o així. La idea final es fer un que creei com 8M i aleshores provar a fer un redis per guaradr la base a la cache o així i veure la diferencia
- https://www.youtube.com/watch?v=PczgM2L3w60 and https://www.youtube.com/watch?v=KYNR5js2cXE for more tasty security
-add envars maybe to store
-Take a look at openapi schema definitions and how can you apply them to here

*/
