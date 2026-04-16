package info.maila.baseapp.config.git

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import java.util.Properties

@Configuration
class GitPropertiesConfiguration {

    @Bean
    fun gitInfo(): GitInfo {
        return ClassPathResource("git.properties")
            .inputStream
            .use { inputStream ->
                val git = Properties()
                    .apply { load(inputStream) }
                    .mapKeys { it.key?.toString() }
                    .mapValues { it.value?.toString() }
                GitInfo(
                    branch = git["git.branch"]?.ifBlank { null },
                    buildHost = git["git.build.host"]?.ifBlank { null },
                    buildUserEmail = git["git.build.user.email"]?.ifBlank { null },
                    buildUserName = git["git.build.user.name"]?.ifBlank { null },
                    buildVersion = git["git.build.version"]?.ifBlank { null },
                    closestTagCommitCount = git["git.closest.tag.commit.count"]?.toIntOrNull(),
                    closestTagName = git["git.closest.tag.name"]?.ifBlank { null },
                    commitId = git["git.commit.id"]?.ifBlank { null },
                    commitIdAbbrev = git["git.commit.id.abbrev"]?.ifBlank { null },
                    commitIdDescribe = git["git.commit.id.describe"]?.ifBlank { null },
                    commitMessageFull = git["git.commit.message.full"]?.ifBlank { null },
                    commitMessageShort = git["git.commit.message.short"]?.ifBlank { null },
                    commitTime = git["git.commit.time"]?.ifBlank { null },
                    commitUserEmail = git["git.commit.user.email"]?.ifBlank { null },
                    commitUserName = git["git.commit.user.name"]?.ifBlank { null },
                    dirty = git["git.dirty"]?.toBoolean(),
                    remoteOriginUrl = git["git.remote.origin.url"]?.ifBlank { null },
                    tags = git["git.tags"]?.ifBlank { null },
                    totalCommitCount = git["git.total.commit.count"]?.toIntOrNull(),
                )
            }
    }

    private fun String.toIntOrNull(): Int? = ifBlank { null }?.toInt()
}