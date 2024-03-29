package com.bifos.fospay.common

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(allowedTargets = [AnnotationTarget.TYPE, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class PersistenceAdapter(
    @get:AliasFor(annotation = Component::class) val value: String = ""
)