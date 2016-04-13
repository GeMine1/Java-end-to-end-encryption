import com.appconus.encryption.AESCipher;
import com.appconus.encryption.RSACipher;
import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;

/**
 * Created by haven on 4/13/16.
 */
public class RSAEncryptionTest {

    @Test
    public void testRSAEncryption() {
        KeyPair keyPair = RSACipher.generateKeyPair();
        byte[] input = "I love you".getBytes();
        Assert.assertArrayEquals(input, RSACipher.decrypt(RSACipher.encrypt(input, keyPair.getPublic().getEncoded()), keyPair.getPrivate().getEncoded()));
    }
}
