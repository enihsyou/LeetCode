import io.kotlintest.KTestJUnitRunner
import io.kotlintest.Spec
import io.kotlintest.TestCase
import org.junit.runner.RunWith

/**使用字符串做测试名，并把它当做参数传给测试用例*/
@RunWith(KTestJUnitRunner::class) // required to let IntelliJ discover tests
abstract class MySpec(body: MySpec.() -> Unit = {}) : Spec() {

    init {
        body()
    }

    /**让测试名称作为参数*/
    operator fun String.invoke(test: (params: String) -> Unit): TestCase {
        val curried = test.curried(this)
        val tc = TestCase(suite = rootTestSuite, name = this, test = curried, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    operator fun <T> String.invoke(param: T, test: (params: T) -> Unit): TestCase {
        val curried = test.curried(param)
        val tc = TestCase(suite = rootTestSuite, name = this, test = curried, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    operator fun <T1, T2> String.invoke(param1: T1, param2: T2, test: (param1: T1, param2: T2) -> Unit): TestCase {
        val curried = test.curried(param1, param2)
        val tc = TestCase(suite = rootTestSuite, name = this, test = curried, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    operator fun <T1, T2, T3> String.invoke(
        param1: T1, param2: T2, param3: T3, test: (param1: T1, param2: T2, param3: T3) -> Unit): TestCase {
        val curried = test.curried(param1, param2, param3)
        val tc = TestCase(suite = rootTestSuite, name = this, test = curried, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    private fun <P1, R> Function1<P1, R>.curried(p1: P1): () -> R = { this(p1) }
    private fun <P1, P2, R> Function2<P1, P2, R>.curried(p1: P1, p2: P2): () -> R = { this(p1, p2) }
    private fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried(p1: P1, p2: P2, p3: P3): () -> R = { this(p1, p2, p3) }
}
