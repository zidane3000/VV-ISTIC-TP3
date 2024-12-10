package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    
        @Test
        void testIsBalanced() {
            assertTrue(isBalanced("()"));
            assertTrue(isBalanced("[]"));
            assertTrue(isBalanced("{}"));
            assertTrue(isBalanced("([])"));
            assertTrue(isBalanced("([{}])"));
            
        }

        @Test
        void testIsBalancedWithNull() {
            assertFalse(isBalanced(null));
        }

        @Test
        void testIsBalancedWithEmptyString() {
            assertTrue(isBalanced(""));
        }

        @Test
        void testIsBalancedWithUnbalancedString() {
            assertFalse(isBalanced("("));
            assertFalse(isBalanced(")"));
            assertFalse(isBalanced("([)"));
            assertFalse(isBalanced("([)]"));
            assertFalse(isBalanced("([{}])}"));
            assertFalse(isBalanced("{{[["));
            assertFalse(isBalanced("}}]]))"));
        }


}