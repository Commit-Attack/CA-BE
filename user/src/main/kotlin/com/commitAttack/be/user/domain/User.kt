package com.commitAttack.be.user.domain

import com.commitAttack.libraries.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete

@Entity
@SQLDelete(sql = "UPDATE account.\"users\" SET \"deletedAt\" = current_timestamp WHERE \"id\" = ?")
@Table(name = "\"users\"", schema = "account")
class User(
    githubId: String,
    name: String,
    profileImageUrl: String?,
    initialCommitCount: Int? = 0,
) : BaseEntity() {

    @Column(name = "\"githubId\"", nullable = false, unique = true)
    var githubId: String = githubId
        protected set

    @Column(name = "\"name\"", nullable = false)
    var name: String = name
        protected set

    @Column(name = "\"profileImageUrl\"", nullable = true)
    var profileImageUrl: String? = profileImageUrl
        protected set

    @Column(name = "\"initialCommitCount\"", nullable = true)
    var initialCommitCount: Int? = initialCommitCount
        protected set
}