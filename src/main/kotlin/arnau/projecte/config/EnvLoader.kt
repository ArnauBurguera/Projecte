package arnau.projecte.config

import io.github.cdimascio.dotenv.Dotenv

object EnvLoader {
    fun loadEnv() {
        val dotenv = Dotenv.configure()
            .directory("./envvars") // Directory where .env is located
            .filename("envvars.env") // Specify the .env filename
            .load()

        // Set each environment variable to system properties for Spring
        dotenv.entries().forEach { entry ->
            System.setProperty(entry.key, entry.value)
        }
    }
}