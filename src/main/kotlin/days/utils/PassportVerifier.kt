package days.utils

class PassportVerifier(private val policy: PassportPolicy) {

    fun verify(passport: Map<String, String>)= policy.verifyPassport(passport)

}