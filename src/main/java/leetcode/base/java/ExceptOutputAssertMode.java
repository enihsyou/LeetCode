package leetcode.base.java;

import java.lang.reflect.Method;

import org.junit.jupiter.api.function.Executable;

/**
 * 将函数输出与args的入参位置的后一个元素做对比
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
class ExceptOutputAssertMode implements AssertMode {

    @Override
    public Executable createExecutable(Method method, Object[] args, DiffMode diffMode, ExecutionOption option) {
        if (args.length <= method.getParameterCount()) {
            return () -> new PrintExecution(method, args, diffMode, true, option).executeTestCase();
        } else {
            return () -> new AssertExecution(method, args, diffMode, option).executeTestCase();
        }
    }
}
