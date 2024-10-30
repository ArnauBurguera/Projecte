package arnau.projecte

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProjecteApplication

fun main(args: Array<String>) {

    // Load the .env file
    val dotenv = Dotenv.configure()
        .directory("./envvars") // Set the directory where the .env file is located
        .filename("envvars.env") // Specify the .env filename
        .load()

    // Pass environment variables to Spring context
    dotenv.entries().forEach { entry ->
        System.setProperty(entry.key, entry.value)
    }

    runApplication<ProjecteApplication>(*args)
}
