package leetcode.base.java;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.platform.commons.util.ClassUtils;
import org.junit.platform.commons.util.StringUtils;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @see org.junit.jupiter.api.DisplayNameGenerator
 * @since 2020-09-13
 */
interface DisplayNameGenerator {

    /**
     * 生成未填充参数值，只有参数类型的测试组名
     * @return {@code twoSum(int[], int) = int[]}
     */
    String nameForMethod(Method method);

    /**
     * 生成填充了参数值的测试名
     * @param args 一组Arguments 可能包含出参
     * @return {@code twoSum([3,2,4], 6) = [1, 2]}
     */
    String nameForCase(Method method, Object[] args);

    class Default implements DisplayNameGenerator {

        @Override
        public String nameForMethod(Method method) {
            String methodArgs = ClassUtils.nullSafeToString(Class::getSimpleName, method.getParameterTypes());
            if (method.getReturnType() == void.class) {
                return parameterizedMethodName(method, methodArgs);
            } else {
                String methodReturn = method.getReturnType().getSimpleName();
                return parameterizedMethodName(method, methodArgs, methodReturn);
            }
        }

        @Override
        public String nameForCase(Method method, Object[] args) {
            String methodArgs = Stream.concat(Arrays.stream(args), Stream.generate(() -> "#MISS"))
                .limit(method.getParameterCount())
                .map(StringUtils::nullSafeToString)
                .collect(Collectors.joining(", "));
            if (method.getReturnType() == void.class) {
                return parameterizedMethodName(method, methodArgs);
            } else if (args.length > method.getParameterCount()) {
                String methodReturn = StringUtils.nullSafeToString(args[method.getParameterCount()]);
                return parameterizedMethodName(method, methodArgs, methodReturn);
            } else {
                return parameterizedMethodName(method, methodArgs, "#MISS");
            }
        }

        private static String parameterizedMethodName(Method method, String methodArgs) {
            return String.format("%s(%s)", method.getName(), methodArgs);
        }

        private static String parameterizedMethodName(Method method, String methodArgs, String methodReturn) {
            return String.format("%s(%s) = %s", method.getName(), methodArgs, methodReturn);
        }
    }
}
