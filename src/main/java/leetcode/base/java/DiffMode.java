package leetcode.base.java;

import org.assertj.core.api.AbstractDoubleArrayAssert;
import org.assertj.core.api.AbstractIntArrayAssert;
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.assertj.core.api.ListAssert;

/**
 * @author Ryoka Kujo chunxiang.huang@mail.hypers.com
 * @since 2020-09-10
 */
public enum DiffMode {

    /** 必须完全匹配 数组考虑顺序 */
    EXACTLY {
        @Override
        public void satisfies(AbstractIntArrayAssert<?> instance, Object methodExcept) {
            instance.containsExactly((int[]) methodExcept);
        }

        @Override
        public void satisfies(AbstractDoubleArrayAssert<?> instance, Object methodExcept) {
            instance.containsExactly((double[]) methodExcept);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <E> void satisfies(AbstractObjectArrayAssert<?, E> instance, Object methodExcept) {
            instance.containsExactly((E[]) methodExcept);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <E> void satisfies(ListAssert<E> instance, Object methodExcept) {
            if (methodExcept instanceof Iterable) {
                instance.containsExactlyElementsOf((Iterable<? extends E>) methodExcept);
            } else {
                instance.isEqualTo(methodExcept);
            }
        }
    },

    /** 只要元素都有 数组顺序随意 */
    CONTAIN {
        @Override
        public void satisfies(AbstractIntArrayAssert<?> instance, Object methodExcept) {
            instance.containsExactlyInAnyOrder((int[]) methodExcept);
        }

        @Override
        public void satisfies(AbstractDoubleArrayAssert<?> instance, Object methodExcept) {
            instance.containsExactlyInAnyOrder((double[]) methodExcept);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <E> void satisfies(AbstractObjectArrayAssert<?, E> instance, Object methodExcept) {
            instance.containsExactlyInAnyOrder((E[]) methodExcept);
        }

        @Override
        @SuppressWarnings("unchecked")
        public <E> void satisfies(ListAssert<E> instance, Object methodExcept) {
            if (methodExcept instanceof Iterable) {
                instance.containsExactlyInAnyOrderElementsOf((Iterable<? extends E>) methodExcept);
            } else {
                throw new UnsupportedOperationException();
            }
        }
    };

    public abstract void satisfies(AbstractIntArrayAssert<?> instance, Object methodExcept);

    public abstract void satisfies(AbstractDoubleArrayAssert<?> instance, Object methodExcept);

    public abstract <E> void satisfies(AbstractObjectArrayAssert<?, E> instance, Object methodExcept);

    public abstract <E> void satisfies(ListAssert<E> instance, Object methodExcept);
}
