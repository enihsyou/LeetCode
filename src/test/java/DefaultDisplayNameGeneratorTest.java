package leetcode.base.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;

import leetcode.base.java.DisplayNameGenerator.Default;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-13
 */
@SuppressWarnings("WeakerAccess")
class DefaultDisplayNameGeneratorTest {

    private static Method voidMethod;
    private static Method intMethod;

    DisplayNameGenerator fixture = new Default();

    @BeforeAll
    static void beforeAll() throws NoSuchMethodException {
        voidMethod = DefaultDisplayNameGeneratorTest.class.getMethod(
            "voidMethod", int.class, String.class, boolean[].class);
        intMethod  = DefaultDisplayNameGeneratorTest.class.getMethod(
            "intMethod", int.class, String.class, boolean[].class);
    }

    @Test
    void displayNameForMethod() {
        assertThat(fixture.nameForMethod(voidMethod))
            .isEqualTo("voidMethod(int, String, boolean[])");
        assertThat(fixture.nameForMethod(intMethod))
            .isEqualTo("intMethod(int, String, boolean[]) = int");
    }

    @Test
    void displayNameForCase() {
        Object[] arguments = { 1, "a", new boolean[]{ true }, 2 };
        assertThat(fixture.nameForCase(voidMethod, arguments))
            .isEqualTo("voidMethod(1, a, [true])");
        assertThat(fixture.nameForCase(intMethod, arguments))
            .isEqualTo("intMethod(1, a, [true]) = 2");
    }

    @Test
    void displayNameForCaseWithMissingArgument() {
        assertThat(fixture.nameForCase(voidMethod, new Object[]{ 1, "a" }))
            .isEqualTo("voidMethod(1, a, #MISS)");
        assertThat(fixture.nameForCase(intMethod, new Object[]{ 1, "a" }))
            .isEqualTo("intMethod(1, a, #MISS) = #MISS");
    }

    public void voidMethod(int a, String b, boolean[] c) { }

    public int intMethod(int a, String b, boolean[] c)   { return 0; }
}
