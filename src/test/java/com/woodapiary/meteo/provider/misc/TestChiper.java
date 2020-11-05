/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.misc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestChiper {

    @Test
    public void test01() throws Exception {
        final String data = "qwerty";
        final Chiper chiper = new Chiper("password", "salt");
        final String value = chiper.encrypt(data);
        final String res = chiper.decrypt(value);
        assertEquals(data, res);
    }

}
