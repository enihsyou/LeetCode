package leetcode.base.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import org.assertj.core.util.Preconditions;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
abstract class Execution {

    protected final Method   method;
    protected final Object[] args;
    protected final DiffMode diffMode;

    private final boolean assertion;

    protected Execution(Method method, Object[] args, DiffMode diffMode, boolean assertion) {
        this.method    = method;
        this.args      = args;
        this.diffMode  = diffMode;
        this.assertion = assertion;
    }

    /** 调用AssertJ执行断言测试 */
    public void executeTestCase() throws Throwable {
        preconditions();
        Object methodOutput = invokeMethod();
        assertions(methodOutput);
    }

    public static Execution getInstance(Method method, Object[] args, DiffMode diffMode) {
        if (method.getParameterCount() == args.length) {
            return new PrintExecution(method, args, diffMode);
        } else {
            return new AssertExecution(method, args, diffMode);
        }
    }

    private Object invokeMethod() throws Throwable {
        Object[] methodInput = Arrays.copyOf(args, argsLength());
        try {
            Constructor<?> solutionConstructor = method.getDeclaringClass().getDeclaredConstructor();
            solutionConstructor.setAccessible(true);
            method.setAccessible(true);
            Object solutionObject = solutionConstructor.newInstance();
            return method.invoke(solutionObject, methodInput);
        } catch (InvocationTargetException e) {
            //noinspection ThrowInsideCatchBlockWhichIgnoresCaughtException
            throw e.getCause();
        } catch (@SuppressWarnings("OverlyBroadCatchBlock") Exception e) {
            throw new RuntimeException("reflection invocation failed.", e);
        }
    }

    private void preconditions() {
        Preconditions.checkState(args.length > 0, "尚未提供入参出参");

        String argsLengthAssertMessage = assertion
            ? "需要%d个入参但(除去出参)提供了%d个"
            : "需要%d个入参但提供了%d个";
        Preconditions.checkState(method.getParameterCount() == argsLength(),
                                 argsLengthAssertMessage,
                                 method.getParameterCount(), argsLength());

        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            Preconditions.checkState(args[i] != null, "第%d个入参不应该为null", i + 1);
            Preconditions.checkState(isAssignableTo(args[i].getClass(), parameterType),
                                     "第%d个入参的类型应该为%s而不是%s", i + 1,
                                     args[i].getClass().getSimpleName(),
                                     parameterType.getSimpleName());
        }
        if (assertion) {
            Class<?> returnsType   = method.getReturnType();
            Object   returnsObject = args[method.getParameterCount()];
            Preconditions.checkState(returnsObject != null, "出参不应该为null");
            assert returnsObject != null; // IDEA bug
            Preconditions.checkState(isAssignableTo(returnsObject.getClass(), returnsType),
                                     "出参的类型应该为%s而不是%s",
                                     returnsObject.getClass().getSimpleName(),
                                     returnsType.getSimpleName());
        }
    }

    protected abstract void assertions(Object methodOutput);

    protected abstract int argsLength();

    /* Help methods */

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
}
