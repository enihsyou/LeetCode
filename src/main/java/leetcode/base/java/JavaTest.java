package leetcode.base.java;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import leetcode.base.struct.TreeNode;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.platform.commons.support.ModifierSupport;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.discovery.MethodSelector;

/**
 * 适用与Java语言题解的通用测试父类
 * 子类继承该父类并注意一定要设置{@link S}为需要测试的题解类的类型
 * @author Ryoka Kujo s1131234@gmail.com
 * @since 2020-09-08
 */
public abstract class JavaTest<S> {

    /** 子类中定义的public实例方法，作为需要测试的解题方法 */
    private final List<Method> solutionMethods;

    protected JavaTest() {
        Class<S> solutionClass = getSolutionClassObject();
        solutionMethods = ReflectionUtils.findMethods(
            solutionClass, ModifierSupport::isPublic);
    }

    /** 提供输入到解题方法中到测试数据，每组的最后一个参数为期望输出 */
    protected abstract Stream<Arguments> provider();

    /** 定义结果比较模式 */
    protected DiffMode diffMode() {
        return DiffMode.EXACTLY;
    }

    protected AssertMode assertMode() {
        return AssertMode.exceptOutputMode();
    }

    @SuppressWarnings("WeakerAccess")
    protected DisplayNameGenerator displayNameGenerator() {
        return new DisplayNameGenerator.Default();
    }

    /** 动态创建测试用例 */
    @TestFactory
    Stream<? extends DynamicNode> dynamicTestsGenerator() {
        return solutionMethods.stream()
            .map(method -> buildDynamicTest(method, provider()));
    }

    /** 创建一个解题方法组，如果有多个public方法会有多个测试组 */
    private DynamicContainer buildDynamicTest(Method method, Stream<Arguments> provider) {
        return DynamicContainer.dynamicContainer(
            displayNameGenerator().nameForMethod(method),
            provider.map(args -> buildDynamicTest(method, args)));
    }

    /** 创建属于一个解题方法组的一个测试用例 */
    private DynamicTest buildDynamicTest(Method method, Arguments provider) {
        Object[] args = provider.get();

        MethodSelector methodSelector = DiscoverySelectors.selectMethod(method.getDeclaringClass(), method);

        Function<String, String> encode = s -> URLEncoder.encode(s, StandardCharsets.US_ASCII);
        String uriString = "method:" +
                           encode.apply(methodSelector.getClassName()) + "#" +
                           encode.apply(methodSelector.getMethodName());

        return DynamicTest.dynamicTest(
            displayNameGenerator().nameForCase(method, args),
            URI.create(uriString),
            assertMode().createExecutable(method, args, diffMode()));
    }

    /** 获得子类继承实现该抽象类时，设置在JavaTest.{@link S}上的类型 */
    @SuppressWarnings("unchecked")
    private Class<S> getSolutionClassObject() {
        Type _superType = this.getClass().getGenericSuperclass();
        if (!(_superType instanceof ParameterizedType)) {
            throw new IllegalCallerException(
                this.getClass().getName() +
                " should use parameterized type, not raw type");
        }
        ParameterizedType superType = (ParameterizedType) _superType;

        return (Class<S>) superType.getActualTypeArguments()[0];
    }

    /* 常用构造器 */

    protected static int[] ints(int... ints) {
        return ints;
    }

    protected static char[] chars(char... chars) {
        return chars;
    }

    @SuppressWarnings("ZeroLengthArrayAllocation")
    protected static int[] emptyInts() {
        return new int[]{ };
    }

    protected static int[][] arrayInts(int[] ints) {
        return new int[][]{ ints };
    }

    protected static int[][] ints(int[]... ints) {
        return ints;
    }

    protected static char[][] chars(char[]... chars) {
        return chars;
    }

    protected static String[] strings(String... strings) {
        return strings;
    }

    @SafeVarargs
    protected static <E> List<E> lists(E... elements) {
        return Arrays.asList(elements);
    }

    protected static TreeNode node(int value) {
        return new TreeNode(value);
    }

    protected static TreeNode node(int value, Integer left, Integer right) {
        TreeNode node = new TreeNode(value);
        if (left != null) node.left = new TreeNode(left);
        if (right != null) node.right = new TreeNode(right);
        return node;
    }

    protected static TreeNode node(int value, TreeNode left, TreeNode right) {
        TreeNode node = new TreeNode(value);
        node.left  = left;
        node.right = right;
        return node;
    }

}
