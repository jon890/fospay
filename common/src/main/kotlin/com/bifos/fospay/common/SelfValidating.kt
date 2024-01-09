package com.bifos.fospay.common

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

abstract class SelfValidating<T>(

) {

    private val validator: Validator

    init {
        val factory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected fun validateSelf() {
        val violations = validator.validate(this)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}