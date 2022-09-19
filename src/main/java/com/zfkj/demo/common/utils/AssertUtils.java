package com.zfkj.demo.common.utils;

import com.zfkj.demo.common.exception.BusinessRootRuntimeException;
import com.zfkj.demo.common.exception.Exceptions;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 断言工具类
 *
 * @author lijunlin
 */
public class AssertUtils {
    public AssertUtils() {
    }

    public static void isTrue(boolean expression, Exceptions type) throws BusinessRootRuntimeException {
        if (!expression) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void isNull(Object object, Exceptions type) throws BusinessRootRuntimeException {
        if (object != null) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void notNull(Object object, Exceptions type) throws BusinessRootRuntimeException {
        if (object == null) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void hasLength(String text, Exceptions type) throws BusinessRootRuntimeException {
        if (!StringUtils.hasLength(text)) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void hasText(String text, Exceptions type) throws BusinessRootRuntimeException {
        if (!StringUtils.hasText(text)) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void hasText(String text, Exceptions type, Object... args) throws BusinessRootRuntimeException {
        if (!StringUtils.hasText(text)) {
            throw new BusinessRootRuntimeException(type, args);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, Exceptions type) throws BusinessRootRuntimeException {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void notEmpty(Object[] array, Exceptions type) throws BusinessRootRuntimeException {
        if (ObjectUtils.isEmpty(array)) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void noNullElements(Object[] array, Exceptions type) throws BusinessRootRuntimeException {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw new BusinessRootRuntimeException(type);
                }
            }
        }

    }

    public static void notEmpty(Collection<?> collection, Exceptions type) throws BusinessRootRuntimeException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessRootRuntimeException(type);
        }
    }

    public static void isTrue(boolean expression, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (!expression) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void isTrue(boolean expression, Exceptions type, Object... args) throws BusinessRootRuntimeException {
        if (!expression) {
            throw new BusinessRootRuntimeException(type, args);
        }
    }

    public static void isNull(Object object, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (object != null) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void notNull(Object object, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (object == null) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void notNull(Object object, Exceptions type, Object... args) throws BusinessRootRuntimeException {
        if (object == null) {
            throw new BusinessRootRuntimeException(type, args);
        }
    }

    public static void hasLength(String text, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (!StringUtils.hasLength(text)) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void hasText(String text, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (!StringUtils.hasText(text)) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void notEmpty(Object[] array, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (ObjectUtils.isEmpty(array)) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }

    public static void noNullElements(Object[] array, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (array != null) {
            Object[] var3 = array;
            int var4 = array.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Object element = var3[var5];
                if (element == null) {
                    throw new BusinessRootRuntimeException(type, message);
                }
            }
        }

    }

    public static void notEmpty(Collection<?> collection, Exceptions type, String message) throws BusinessRootRuntimeException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessRootRuntimeException(type, message);
        }
    }
}
