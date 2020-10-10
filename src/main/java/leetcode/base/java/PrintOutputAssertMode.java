package leetcode.base.java;

import java.lang.reflect.Method;

import org.junit.jupiter.api.function.Executable;

/**
 * 只将输出打印出来
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
class PrintOutputAssertMode implements AssertMode {

    @Override
    public Executable createExecutable(Method method, Object[] args, DiffMode diffMode,
                                       ExecutionOption option) {
        return () -> new PrintExecution(method, args, diffMode, false, option).executeTestCase();
    }
}
