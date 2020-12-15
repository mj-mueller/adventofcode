package days.utils

class PassportPolicy(val mandatoryFields: Set<String> = emptySet(), val rules: List<(Map<String, String>) -> Boolean> = emptyList()) {

    fun verifyPassport(fields: Map<String, String>): Boolean{
        checkMandatoryFields(fields.keys)
        return true
    }

    fun checkMandatoryFields(fields: Set<String>) = fields.containsAll(mandatoryFields)
}