package info.maila.baseapp.config.git

data class GitInfo(
    val branch: String? = null,
    val buildHost: String? = null,
    val buildUserEmail: String? = null,
    val buildUserName: String? = null,
    val buildVersion: String? = null,
    val closestTagCommitCount: Int? = null,
    val closestTagName: String? = null,
    val commitId: String? = null,
    val commitIdAbbrev: String? = null,
    val commitIdDescribe: String? = null,
    val commitMessageFull: String? = null,
    val commitMessageShort: String? = null,
    val commitTime: String? = null,
    val commitUserEmail: String? = null,
    val commitUserName: String? = null,
    val dirty: Boolean? = null,
    val remoteOriginUrl: String? = null,
    val tags: String? = null,
    val totalCommitCount: Int? = null
)