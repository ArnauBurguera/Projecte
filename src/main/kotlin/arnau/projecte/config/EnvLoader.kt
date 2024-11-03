package arnau.projecte.config

import io.github.cdimascio.dotenv.Dotenv

object EnvLoader {
    fun loadEnv() {
        val dotenv = Dotenv.configure()
            .directory("./envvars")
            .filename("envvars.env")
            .load()

        dotenv.entries().forEach { entry ->
            System.setProperty(entry.key, entry.value)
        }
    }
}