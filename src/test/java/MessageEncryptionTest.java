import com.appconus.encryption.MessageSecureProcessor;
import com.appconus.encryption.RSACipher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;

public class MessageEncryptionTest {

    private KeyPair keyPair;
    @Before
    public void initRSAKeyPair() {
        keyPair = RSACipher.generateKeyPair();
        Assert.assertNotNull(keyPair);
    }

    @Test
    public void testEncryption() {
        String AnneMessage = "I love you";
        String encrypted = MessageSecureProcessor.encrypt(AnneMessage, keyPair.getPublic().getEncoded());
        String decrypted = MessageSecureProcessor.decrypt(encrypted, keyPair.getPrivate().getEncoded());
        Assert.assertEquals(AnneMessage, decrypted);
    }
}
