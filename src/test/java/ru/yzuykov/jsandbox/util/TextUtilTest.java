package ru.yzuykov.jsandbox.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TextUtilTest {

    @Test
    void test_replace_1() {
        String input = "asdasd     \"    Test 123 ‰   \"   asdasd    \" dfsdfsdf  \"";
        String expected = "asdasd \"Test 123 ‰\" asdasd \"dfsdfsdf\"";
        String result = TextUtil.replaceDeniedSymbolsInString(input);
        assertEquals(expected, result);
    }

    @Test
    void test_replace_2() {
        String input = "asdasd     \"    Test 123 ‰   \"   asdasd    \" dfsdfsdf  \"      ";
        String expected = "asdasd \"Test 123 ‰\" asdasd \"dfsdfsdf\"";
        String result = TextUtil.replaceDeniedSymbolsInString(input);
        assertEquals(expected, result);
    }

}