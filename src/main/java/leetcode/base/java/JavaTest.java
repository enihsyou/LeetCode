package leetcode.base.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectArrayAssert;
import org.assertj.core.util.Preconditions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.platform.commons.support.ModifierSupport;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.platform.commons.util.StringUtils;
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

    /** 动态创建测试用例 */
    @TestFactory
    Stream<? extends DynamicNode> dynamicTestsGenerator() {
        return solutionMethods.stream()
            .map(method -> buildDynamicTest(method, provider(), diffMode()));
    }

    /** 创建一个解题方法组，如果有多个public方法会有多个测试组 */
    private static DynamicContainer buildDynamicTest(Method method, Stream<Arguments> provider,
                                                     DiffMode diffMode) {
        return DynamicContainer.dynamicContainer(
            parameterizedMethodName(method),
            provider.map(args -> buildDynamicTest(method, args, diffMode)));
    }

    /** 创建属于一个解题方法组的一个测试用例 */
    private static DynamicTest buildDynamicTest(Method method, Arguments provider,
                                                DiffMode diffMode) {
        Object[] args = provider.get();

        MethodSelector methodSelector = DiscoverySelectors.selectMethod(method.getDeclaringClass(), method);

        Function<String, String> encode = s -> URLEncoder.encode(s, StandardCharsets.US_ASCII);
        String uriString = "method:" +
                           encode.apply(methodSelector.getClassName()) + "#" +
                           encode.apply(methodSelector.getMethodName());
        return DynamicTest.dynamicTest(
            parameterizedTestCaseName(method, args),
            URI.create(uriString),
            () -> executeTestCase(method, args, diffMode));
    }


    /**
     * 生成未填充参数值，只有参数类型的测试组名
     * @return {@code twoSum(int[], int) = int[]}
     */
    private static String parameterizedMethodName(Method method) {
        String methodArgs = Arrays.stream(method.getParameterTypes())
            .map(Class::getSimpleName)
            .collect(Collectors.joining(", "));
        String methodReturn = method.getReturnType().getSimpleName();

        return parameterizedMethodName(method, methodArgs, methodReturn);
    }

    /**
     * 生成填充了参数值的测试名
     * @return {@code twoSum([3,2,4], 6) = [1, 2]}
     */
    private static String parameterizedTestCaseName(Method method, Object[] args) {
        if (args.length < 1) {
            return parameterizedMethodName(method, "#ERR", "#ERR");
        }

        String methodArgs = Arrays.stream(args).limit(args.length - 1)
            .map(StringUtils::nullSafeToString)
            .collect(Collectors.joining(", "));
        String methodReturn = StringUtils.nullSafeToString(args[args.length - 1]);

        return parameterizedMethodName(method, methodArgs, methodReturn);
    }

    @SuppressWarnings("TypeMayBeWeakened")
    private static String parameterizedMethodName(Method method, String methodArgs, String methodReturn) {
        return String.format("%s(%s) = %s", method.getName(), methodArgs, methodReturn);
    }

    /** 调用AssertJ执行断言测试 */
    @SuppressWarnings("OverlyBroadCatchBlock")
    private static void executeTestCase(Method method, Object[] args, DiffMode diffMode) {
        preconditions(method, args);

        Object[] methodInput  = Arrays.copyOf(args, args.length - 1);
        Object   methodExcept = args[args.length - 1];
        Object   methodOutput;
        try {
            Constructor<?> solutionConstructor = method.getDeclaringClass().getDeclaredConstructor();
            solutionConstructor.setAccessible(true);
            method.setAccessible(true);
            Object solutionObject = solutionConstructor.newInstance();
            methodOutput = method.invoke(solutionObject, methodInput);
        } catch (Exception e) {
            throw new RuntimeException("reflection invocation failed.", e);
        }

        if (methodExcept.getClass().isArray()) {
            //noinspection ChainOfInstanceofChecks
            if (methodExcept.getClass().getComponentType() == int.class) {
                diffMode.satisfies(InstanceOfAssertFactories.INT_ARRAY.createAssert(methodOutput),
                                   methodExcept);
            } else if (methodExcept.getClass().getComponentType() == double.class) {
                diffMode.satisfies(InstanceOfAssertFactories.DOUBLE_ARRAY.createAssert(methodOutput),
                                   methodExcept);
            } else if (methodExcept.getClass().getComponentType() == int[].class) {
                diffMode.satisfies(new ObjectArrayAssert<>((int[][]) methodOutput),
                                   methodExcept);
            } else {
                throw new UnsupportedOperationException(
                    "没有适配返回类型: " + methodExcept.getClass().getSimpleName());
            }
        } else {
            assertThat(methodOutput).isEqualTo(methodExcept);
        }
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

    private static void preconditions(Method method, Object[] args) {
        Preconditions.checkState(args.length > 0, "尚未提供入参出参");
        Preconditions.checkState(method.getParameterCount() == args.length - 1,
                                 "需要%d个入参但(除去出参)只提供了%d个",
                                 method.getParameterCount(), args.length - 1);

        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            Preconditions.checkState(args[i] != null,
                                     "第%d个入参不应该为null", i + 1);
            Preconditions.checkState(isAssignableTo(args[i].getClass(), parameterType),
                                     "第%d个入参的类型应该为%s而不是%s", i + 1,
                                     args[i].getClass().getSimpleName(),
                                     parameterType.getSimpleName());
        }
    }

    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = Map.of(
        boolean.class, Boolean.class,
        byte.class, Byte.class,
        char.class, Character.class,
        double.class, Double.class,
        float.class, Float.class,
        int.class, Integer.class,
        long.class, Long.class,
        short.class, Short.class);

    private static boolean isAssignableTo(Class<?> from, Class<?> to) {
        return to.isAssignableFrom(from) ||
               from.isPrimitive() && primitiveWrapperMap.get(from) == to ||
               to.isPrimitive() && primitiveWrapperMap.get(to) == from;
    }

    /* 常用构造器 */

    protected static int[] ints(int... ints) {
        return ints;
    }

    protected static int[][] ints(int[]... ints) {
        return ints;
    }

}
