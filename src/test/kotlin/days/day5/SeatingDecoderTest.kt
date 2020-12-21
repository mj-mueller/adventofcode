package days.day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatingDecoderTest {

    @Test
    fun seatingDecoder_normal_success() {
        val decoder: SeatingDecoder = SeatingDecoderImpl()
        val s: SeatingDecoderImpl = SeatingDecoderImpl()

        val ticket = "FBFBBFFRLR"
        val seat: Seat = decoder.getSeat(ticket)

        assertEquals(44, seat.row)
        assertEquals(5, seat.column)
        assertEquals(44 * 8 + 5, seat.id)
    }

    @Test
    fun seatingDecoder_invalidInput_throwException() {
        val decoder: SeatingDecoder = SeatingDecoderImpl()
        val tickets = listOf(
            "FBFBBFFLR", "", "FBFBBFLRLR", "FBFBBFFFLR",
            "FBF1BFFRLR", "FBFBBFFRLR".toLowerCase()
        )

        tickets.forEach {
            assertThrows<IllegalArgumentException> {
                decoder.getSeat(it)
            }
        }
    }
}
