package arnau.projecte

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication//(scanBasePackages = ["arnau.projecte.config"])
class ProjecteApplication

fun main(args: Array<String>) {
    runApplication<ProjecteApplication>(*args)
}
