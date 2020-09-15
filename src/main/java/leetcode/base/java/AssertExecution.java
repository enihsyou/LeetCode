package leetcode.base.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.List;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectArrayAssert;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
class AssertExecution extends Execution {

    AssertExecution(Method method, Object[] args, DiffMode diffMode) {
        super(method, args, diffMode);
    }

    @Override
    public void assertions(Object methodOutput) {
        Object methodExcept = args[method.getParameterCount()];

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
            } else if (methodExcept.getClass().getComponentType() == char[].class) {
                diffMode.satisfies(new ObjectArrayAssert<>((char[][]) methodOutput),
                                   methodExcept);
            } else {
                throw new UnsupportedOperationException(
                    "没有适配返回类型: " + methodExcept.getClass().getSimpleName());
            }
        } else {
            if (List.class.isAssignableFrom(methodExcept.getClass())) {
                diffMode.satisfies(InstanceOfAssertFactories.LIST.createAssert(methodOutput),
                                   methodExcept);
            } else {
                assertThat(methodOutput).isEqualTo(methodExcept);
            }
        }
    }

    @Override
    protected int argsLength() {
        return method.getParameterCount();
    }
}
