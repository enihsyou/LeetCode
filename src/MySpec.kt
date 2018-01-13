import io.kotlintest.KTestJUnitRunner
import io.kotlintest.Spec
import io.kotlintest.TestCase
import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class) // required to let IntelliJ discover tests
abstract class MySpec(body: MySpec.() -> Unit = {}) : Spec() {

    init {
        body()
    }

//    operator fun String.invoke(test: () -> Unit): TestCase {
//        val tc = TestCase(suite = rootTestSuite, name = this, test = test, config = defaultTestCaseConfig)
//        rootTestSuite.addTestCase(tc)
//        return tc
//    }
    /**让测试名称作为参数*/
    operator fun String.invoke(test: (params: String) -> Unit): TestCase {
        val curried = test.curried(this)
        val tc = TestCase(suite = rootTestSuite, name = this, test = curried, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    private fun <P1, R> Function1<P1, R>.curried(p1: P1): () -> R =
        { this(p1) }

    private fun <P1, P2, R> Function2<P1, P2, R>.curried(): (P1) -> (P2) -> R =
        { p1 -> { p2 -> this(p1, p2) } }
}
