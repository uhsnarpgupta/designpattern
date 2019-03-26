package com.uhsnarp.designpattern;

import com.uhsnarp.designpattern.prototype.ProtoFalse;
import com.uhsnarp.designpattern.prototype.ProtoTrue;
import com.uhsnarp.designpattern.singleton.SingA;
import com.uhsnarp.designpattern.singleton.SingB;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesignpatternApplicationTests {
    @Autowired
    SingB singB1;

    @Autowired
    SingB singB2;

    @Autowired
    ProtoTrue true1;

    @Autowired
    ProtoTrue true2;

    @Autowired
    ProtoFalse false1;

    @Autowired
    ProtoFalse false2;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSingletons() {
        SingA singA1 = SingA.getInstance();
        SingA singA2 = SingA.getInstance();
        Assert.assertNotNull(singA1);
        Assert.assertSame(singA1, singA2);

        Assert.assertNotNull(singB1);
        Assert.assertSame(singB1, singB2);
    }

    @Test
    public void testPrototypes() {
        Assert.assertSame(false1, false2);
        Assert.assertNotSame(true1, true2);
    }

}
