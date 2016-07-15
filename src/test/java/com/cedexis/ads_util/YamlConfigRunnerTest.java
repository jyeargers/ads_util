package com.cedexis.ads_util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

/**
 * Created on 7/13/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({YamlConfigRunner.class})
public class YamlConfigRunnerTest {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(YamlConfigRunnerTest.class);

    YamlConfigRunner yamlConfigRunner;

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void flatten_test0() throws Exception {
        yamlConfigRunner = PowerMockito.spy(new YamlConfigRunner());

        assertEquals("java.lang.Stringxxxx", yamlConfigRunner.getValueType("rootkey"));
        assertEquals("rootvalue", yamlConfigRunner.getString("rootkey"));

        assertNull(yamlConfigRunner.getString("rootint"));
        assertEquals("java.lang.Integer", yamlConfigRunner.getValueType("rootint"));
        assertEquals((Integer)1234, yamlConfigRunner.getInt("rootint"));

        assertEquals("java.util.ArrayList", yamlConfigRunner.getValueType("somearray"));

        List<String> list0 = yamlConfigRunner.getList("somearray");
        assertEquals(2, list0.size());
        assertEquals("java.util.ArrayList", yamlConfigRunner.getValueType("nested0/level0"));

        List<String> list1 = yamlConfigRunner.getList("nested0/level0");
        assertEquals(2, list1.size());

        assertEquals("thing0thing", yamlConfigRunner.getString("nested1/level1/string0"));
        assertEquals("java.lang.String", yamlConfigRunner.getValueType("nested1/level1/string0"));

        // these have to be cast otherwise the assert gets confused.
        assertEquals((Integer)123, yamlConfigRunner.getInt("nested1/level1/int0"));
        assertEquals("java.lang.Integer", yamlConfigRunner.getValueType("nested1/level1/int0"));

        assertEquals("10.20.30.40", yamlConfigRunner.getString("nested1/level1/string2"));

        assertEquals((Long)1234567890123L, yamlConfigRunner.getLong("nested1/level1/long0"));
        assertEquals("java.lang.Long", yamlConfigRunner.getValueType("nested1/level1/long0"));

        assertEquals(new BigInteger("12345678901234567890"), yamlConfigRunner.getBigInt("nested1/level1/bigint0"));
        assertEquals("java.math.BigInteger", yamlConfigRunner.getValueType("nested1/level1/bigint0"));

        assertEquals((Double)123.456, yamlConfigRunner.getDouble("nested1/level1/double0"));
        assertEquals("java.lang.Double", yamlConfigRunner.getValueType("nested1/level1/double0"));

        List<Integer> list2 = yamlConfigRunner.getList("nested1/level1/array0");
        assertEquals(3, list2.size());
        assertEquals((Integer)123, list2.get(0));
        assertNotEquals("456", list2.get(1));
        assertNotEquals((Long)789L, list2.get(2));

        assertEquals("java.util.ArrayList", yamlConfigRunner.getValueType("nested1/level1/array0"));
    }

    /*
    non existent file
     */
    @Test(expected = RuntimeException.class)
    public void flatten_test1() throws Exception {
        yamlConfigRunner = PowerMockito.spy(new YamlConfigRunner("notafile"));

    }

}
