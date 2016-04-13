import com.appconus.encryption.AESCipher;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by haven on 4/13/16.
 */
public class AESEncryptionTest {

    @Test
    public void testAES() {
        byte[] aesKey = AESCipher.generateAESKey();
        byte[] input = "I love you".getBytes();
        Assert.assertArrayEquals(input, AESCipher.decrypt(AESCipher.encrypt(input, aesKey), aesKey));
    }
}
