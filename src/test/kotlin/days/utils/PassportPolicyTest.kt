package days.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PassportPolicyTest{
    @Test
    fun mandatoryFields(){
        val mandatoryFields = setOf("cid", "year")
        val policy = PassportPolicy(mandatoryFields = mandatoryFields)
        assert(policy.mandatoryFields.containsAll(mandatoryFields))
    }

    @Test
    fun emptyMandatoryFields(){
        val policy = PassportPolicy()
        assert(policy.mandatoryFields.isEmpty())
    }

    @Test
    fun emptyRules(){
        val policy = PassportPolicy()
        assert(policy.rules.isEmpty())
    }

    @Test
    fun test(){
        val rules: Map<String, String> = mapOf()
        val policy = PassportPolicy()
        assert(policy.mandatoryFields.isEmpty())
    }
}