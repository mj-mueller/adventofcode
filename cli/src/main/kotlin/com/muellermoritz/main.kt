import com.muellermoritz.days.*
import com.muellermoritz.utils.ResourcesRequester
import com.muellermoritz.utils.ResourcesRequester.Companion.getInputFileAsText
import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.Metrics
import io.micrometer.graphite.GraphiteConfig
import io.micrometer.graphite.GraphiteMeterRegistry
import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry


fun main() {


//val reg = comp.registries
//    for(r in reg){
//        val m =r.meters.first()
//        println(m.id)
//    }
//    println(comp.meters.forEach{it.id.name})
//    val simple: MeterRegistry= SimpleMeterRegistry()


//    comp.add(prometheusRegistry)
//    comp.add(simple)
//    val ccount = comp.counter("sdad")
//    ccount.increment()
//    comp.counter("sdefefad")
//    println(simple.summary("tsefsefsf"))
//    val ssd= Metrics.globalRegistry.
//    try {
//        val server = HttpServer.create(InetSocketAddress(8080), 0)
//        server.createContext("/prometheus") { httpExchange ->
//            val response = prometheusRegistry.scrape()
//            httpExchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
//            httpExchange.responseBody.use { os -> os.write(response.toByteArray()) }
//        }
//        Thread(Runnable { server.start() }).start()
//    } catch (e: IOException) {
//        throw RuntimeException(e)
//    }
//    val comp = CompositeMeterRegistry()
//    comp.add(prometheusRegistry)
//    comp.add(AoCCoreMetricRegistry.registry)


//    val requests = Counter.build()
//        .name("requests_total").help("Total requests.").register();
//val m= Metrics.timer("sd")
//    while (System.currentTimeMillis().toInt() /2 !=0) {
    val day1 = Day1(getInputFileAsText("day1.txt"))
    println(day1.getResults())
//        println(ccount.count())
//        println(comp.meters.forEach { it.id })
//        ccount.increment()
//        println(prometheusRegistry.scrape())
//        println(prometheusRegistry.scrape())
    Thread.sleep(2000)
//    }
    val day2 = Day2(ResourcesRequester.DAY_INPUT_PATH + "day2.txt")
    day2.printResults()
    val day3 = Day3(ResourcesRequester.DAY_INPUT_PATH + "day3.txt")
    day3.printResults()
    val day4 = Day4(ResourcesRequester.DAY_INPUT_PATH + "day4.txt")
    day4.printResults()
    val day5 = Day5(ResourcesRequester.DAY_INPUT_PATH + "day5.txt")
    day5.printResults()
    val day6 = Day6(ResourcesRequester.DAY_INPUT_PATH + "day6.txt")
    day6.printResults()
}

private fun addMeterRegistries() {
    Metrics.globalRegistry.add(PrometheusMeterRegistry(PrometheusConfig.DEFAULT))
    Metrics.globalRegistry.add(GraphiteMeterRegistry(GraphiteConfig.DEFAULT, Clock.SYSTEM))
}