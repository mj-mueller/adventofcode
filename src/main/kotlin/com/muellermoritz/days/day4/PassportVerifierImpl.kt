package com.muellermoritz.days.day4

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class PassportVerifierImpl : PassportVerifier {
    override var mandatoryFields: Set<String> = emptySet()
    override var fieldRegexRules: Map<String, Regex> = emptyMap()

    override fun checkPassport(passport: Map<String, String>): Boolean {
        return checkMandatoryFields(passport) && checkFieldRegexRules(passport)
    }

    private fun checkMandatoryFields(passport: Map<String, String>): Boolean {
        return passport.keys.containsAll(mandatoryFields)
    }

    private fun checkFieldRegexRules(passport: Map<String, String>): Boolean {
        passport.keys.forEach { fieldName ->
            if (fieldRegexRules.contains(fieldName) && !testPassportField(
                    passport[fieldName]!!,
                    fieldRegexRules[fieldName]!!
                )
            ) {
                logger.debug("Invalid $fieldName.")
                return false
            }

        }
        return true
    }

    private fun testPassportField(value: String, rule: Regex): Boolean {
        val matching = (rule.find(value) != null)
        logger.trace("Matching $value on $rule. Result: $matching.")
        return matching
    }
}
