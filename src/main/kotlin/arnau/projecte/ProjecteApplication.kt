package arnau.projecte

import arnau.projecte.config.EnvLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProjecteApplication

fun main(args: Array<String>) {

    EnvLoader.loadEnv()
    runApplication<ProjecteApplication>(*args)
}
