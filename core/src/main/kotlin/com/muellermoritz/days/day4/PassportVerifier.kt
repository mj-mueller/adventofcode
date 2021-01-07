package com.muellermoritz.days.day4

interface PassportVerifier {

    /**
     * Evaluate the passport according to defined (builder pattern) criteria.
     */
    fun checkPassport(passport: Map<String, String>): Boolean

    /**
     * Defines mandatory fields for passport verification.
     */
    var mandatoryFields: Set<String>

    /**
     * Defines regex that need to match for specified passport fields (keys in map).
     */
    var fieldRegexRules: Map<String, Regex>
}
