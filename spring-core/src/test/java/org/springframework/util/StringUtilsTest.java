package org.springframework.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianglei
 * @since 2018/4/4
 */
public class StringUtilsTest {


    @Test
    public void testCollectionToDelimitedString() {
        List<Object> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");

        String res = StringUtils.collectionToDelimitedString(strList, ",", "p", "s");
        Assert.assertEquals("pas,pbs,pcs", res);
    }
}
