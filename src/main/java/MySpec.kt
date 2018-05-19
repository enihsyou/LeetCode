import io.kotlintest.KTestJUnitRunner
import io.kotlintest.Spec
import io.kotlintest.TestCase
import org.junit.runner.RunWith
import java.util.*

/**使用字符串做测试名，并把它当做参数传给测试用例*/
@RunWith(KTestJUnitRunner::class) // required to let IntelliJ discover tests
abstract class MySpec(body: MySpec.() -> Unit = {}) : Spec() {

    init {
        body()
    }

    /**让测试名称作为参数*/
    protected operator fun String.invoke(test: (params: String) -> Unit): TestCase {
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

    fun add(test: () -> Unit): TestCase {
        val tc = TestCase(
            suite = rootTestSuite, name = "${test::class.simpleName}", test = test, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    fun add(
        param1: IntArray, param2: IntArray, test: (param1: IntArray, param2: IntArray) -> Unit): TestCase {
        val curried = test.curried(param1, param2)
        val tc = TestCase(
            suite = rootTestSuite, name = "${Arrays.toString(param1)}, ${Arrays.toString(param2)}", test = curried,
            config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    fun <T> add(
        param1: Array<T>, param2: Array<T>, test: (param1: Array<T>, param2: Array<T>) -> Unit): TestCase {
        val curried = test.curried(param1, param2)
        val tc = TestCase(
            suite = rootTestSuite, name = "${Arrays.toString(param1)}, ${Arrays.toString(param2)}", test = curried,
            config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    fun <T> add(param: T, test: (params: T) -> Unit): TestCase {
        val curried = test.curried(param)
        val tc = TestCase(suite = rootTestSuite, name = "$param", test = curried, config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    fun <T> add(param1: T, param2: T, test: (param1: T, param2: T) -> Unit): TestCase {
        val curried = test.curried(param1, param2)
        val tc = TestCase(
            suite = rootTestSuite, name = "${param1.toString()}, ${param2.toString()}", test = curried,
            config = defaultTestCaseConfig)
        rootTestSuite.addTestCase(tc)
        return tc
    }

    private fun <P1, R> Function1<P1, R>.curried(p1: P1): () -> R = { this(p1) }
    private fun <P1, P2, R> Function2<P1, P2, R>.curried(p1: P1, p2: P2): () -> R = { this(p1, p2) }
    private fun <P1, P2, P3, R> Function3<P1, P2, P3, R>.curried(p1: P1, p2: P2, p3: P3): () -> R = { this(p1, p2, p3) }
}
