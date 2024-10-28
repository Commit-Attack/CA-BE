package com.commitAttack.be.user.domain

import com.commitAttack.libraries.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete

@Entity
@SQLDelete(sql = "UPDATE public.\"Users\" SET \"deletedAt\" = current_timestamp WHERE \"id\" = ?")
@Table(name = "\"Users\"", schema = "public")
class User(
    name: String,
    profileImageUrl: String?,
    initialCommitCount: Int,
) : BaseEntity() {

    @Column(name = "\"name\"", nullable = false)
    var name: String = name
        protected set

    @Column(name = "\"profileImageUrl\"", nullable = true)
    var profileImageUrl: String? = profileImageUrl
        protected set

    @Column(name = "\"initialCommitCount\"", nullable = false)
    var initialCommitCount: Int = initialCommitCount
        protected set
}