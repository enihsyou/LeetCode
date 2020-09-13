package leetcode.base.java;

import java.lang.reflect.Method;

import org.junit.jupiter.api.function.Executable;

/**
 * 创建测试方法的工厂
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
public interface AssertMode {

    Executable createExecutable(Method method, Object[] args, DiffMode diffMode);

    static AssertMode exceptOutputMode() {
        return new ExceptOutputAssertMode();
    }

    static AssertMode exceptArgumentMode(int positionInArgs) {
        return new ExceptArgumentAssertMode(positionInArgs);
    }

    static AssertMode printOutputMode() {
        return new PrintOutputAssertMode();
    }
}

