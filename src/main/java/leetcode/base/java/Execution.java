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
@SuppressWarnings("WeakerAccess")
abstract class Execution {

    protected final Method   method;
    protected final Object[] args;
    protected final DiffMode diffMode;

    protected final ExecutionOption option;

    protected Execution(Method method, Object[] args, DiffMode diffMode, ExecutionOption option) {
        this.method   = method;
        this.args     = args;
        this.diffMode = diffMode;
        this.option   = option;
    }

    /** 调用AssertJ执行断言测试 */
    public void executeTestCase() throws Throwable {
        preconditions();
        Object methodOutput = invokeMethod();
        assertions(methodOutput);
    }

    private Object invokeMethod() throws Throwable {
        Object[] methodInput = Arrays.copyOf(args, method.getParameterCount());
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
        miscArgsPreconditions();
        inputArgsPreconditions();
        outputArgsPreconditions();
    }

    protected void miscArgsPreconditions() {
        Preconditions.checkState(args.length > 0, "尚未提供入参出参");

        boolean  enoughParameters = args.length == method.getParameterCount();
        boolean  assertParameters = args.length == method.getParameterCount() + 1;
        Class<?> returnsType      = method.getReturnType();
        if (returnsType == void.class) {
            Preconditions.checkState(enoughParameters || assertParameters,
                                     "函数需要%d个参数和最多一个返回值，但提供了%d个，" +
                                     "返回void的函数推荐使用AssertMode.exceptArgumentMode",
                                     method.getParameterCount(), args.length);
        } else {
            Preconditions.checkState(enoughParameters || assertParameters,
                                     "函数需要%d个参数和最多一个返回值，但提供了%d个",
                                     method.getParameterCount(), args.length);
        }
    }

    protected void inputArgsPreconditions() {
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            Preconditions.checkState(args[i] != null, "第%d个参数不应该为null", i + 1);
            Preconditions.checkState(isAssignableTo(args[i].getClass(), parameterType),
                                     "第%d个参数的类型应该为%s而不是%s", i + 1,
                                     args[i].getClass().getSimpleName(),
                                     parameterType.getSimpleName());
        }
    }

    protected void outputArgsPreconditions() {
        if (args.length == method.getParameterCount()) {
            // 可能是用例不好assert，只需打印结果
            return;
        }

        Class<?> returnsType = method.getReturnType();
        Preconditions.checkState(returnsType != void.class,
                                 "函数返回void时推荐使用AssertMode.exceptArgumentMode");
        Object returnsObject = args[method.getParameterCount()];
        Preconditions.checkState(option.allowNullOutput() || returnsObject != null, "函数返回不应该为null");
        if (returnsObject != null) {
            Preconditions.checkState(isAssignableTo(returnsObject.getClass(), returnsType),
                                     "函数返回的类型应该为%s而不是%s",
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
