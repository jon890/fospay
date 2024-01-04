package common

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(allowedTargets = [AnnotationTarget.TYPE, AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class UseCase(
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring b ean in case of an autodetected component.
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @get:AliasFor(annotation = Component::class) val value: String = ""
)