package com.spring.proje.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;


@Service
public class aesgcmencryption {

    private static final String aesalgoritma = "AES/GCM/NoPadding";
    private static final int taglenght = 128;                     // şifrelenmiş veriye 128 bitlik bir byte ekler.
    private static final int ivlenght = 12;                       // IV bir şifrenin tekrar olusturulurken aynı olmamasını saglar. mesela 2 defa merhaba yazan bir şifre göndersem ikisinide farklı şekilde şifreler. bu şifremeyi aynı yapmamak ıcın değer bu.

    @Value("${security.aes-key}")                                  // applişcation propertiesteki key
    private String secretkey;                                      // key

    private SecretKeySpec secretkeyspec;                                 // secretkey string olarak geldigi icin kullanılamıyor . oyuzden keyspec olarak işleniyor. yani secretkeyin işlenmiş hali.

    @PostConstruct
    public void initsecretkey(){

        byte[] keybytes = secretkey.getBytes(StandardCharsets.UTF_8); // application propertiesteki keyi javanın anlayacagı sekile donusturuyor. Örnek olarak key merhaba ise [109, 101, 114, 104, 97, 98, 97] 'a donusturuyor getBytes ile.
        secretkeyspec = new SecretKeySpec(keybytes,"AES" ); //secrey keyi aes anahtarı olarak işleme hazır bir sekıdle cıkarıyor.

    }

    public String encrypt(String text){

        try{

            byte[] iv = new byte[ivlenght];
            SecureRandom.getInstanceStrong().nextBytes(iv); // IV icin rastgele değer uretıryor.

            Cipher cipher = Cipher.getInstance(aesalgoritma);            // aes olarak şifrelemesini istiyor. yani aes algoritmasini kullandırıyor.

            cipher.init(Cipher.ENCRYPT_MODE, secretkeyspec, new GCMParameterSpec(taglenght, iv)); // Cipher.ENCRYPT_MODE algoritmayla şifrelemeyik icin. keyspec keyimiz. tag lenght veri şifrelenmesinden sonra ona eklenilecek tag. mesela a ise a123213 ekliyor. iv ise şifrelemesi icin
            // ONEMLI NOT : VERİYİ AES ŞİFRELİYOR IV İSE SADECE O ŞİFRELEMEYİ RASTGELE YAPTIRIYOR MESELA 2 KERE A VERİSİNİ SİFRELEDİGİNİZDE AYNI VERI OLMASINA RAGMEN 2 FARFKLI SONUC CIKARIYOR.

            // NOT CİPHER ŞİFRELEMİYOR SADECE ŞİFRELENMEK İCİN ORTAMI HAZIRLIYOR.

            byte[] encrypteddata = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)); // Veri şifrelemenin son aşaması. method'a gelen plaintex Stringi burda BYTE' e cevirilir. cipher.noFinal de önceden ayarladıgımız cipher ayarlarını uygular ve veriyi şifrelenmiş olarak geri cıkarır.

            byte[] combined = new byte[iv.length + encrypteddata.length]; // IV ve şifrelenmiş veriyi aynı dizi içinde kaydediyor ornek [ U5opLPsLcV+AOLeH + merhaba1232131DQWHDQ ]
            System.arraycopy(iv, 0, combined, 0, iv.length);          // ivyi dizenin en başına eklıyor örnek [ U5opLPsLcV+AOLeH  ]
            System.arraycopy(encrypteddata, 0, combined, iv.length, encrypteddata.length); // ivnin arkasına da şifrelenmiş veriyi ekliyor örnek [ U5opLPsLcV+AOLeHmerhaba1232131DQWHDQ ]
            // not : şifrelenmenin çözulmesı ıcın verının ıcıne dahıl edılmesı lazım.

            // GONDERME ASAMASI
            return Base64.getEncoder().encodeToString(combined);   // herşeyi şifreledik simdi bu sifrelenmisi veri tabanına kaydetmek icin byteden cıkarmak lazım örnek byte decoded = [72, 101, 108, 108, 111] örnek cıkarılmıs veri SQ3F4
            //byte olarak dırekt gonderırsek verı tabanında sıkıntı cıkarabılır strıng olaran dondurmemız lazım.

        }catch (Exception e){

            System.out.println(e);

        }
        return null;
    }

    public String decrypt(String sifre){

        if (sifre == null) {
            return null;
        }else{

            try{

                byte[] tobyte = Base64.getDecoder().decode(sifre);
                byte[] iv = Arrays.copyOfRange(tobyte, 0, ivlenght);
                byte[] encryptedtext = Arrays.copyOfRange(tobyte, ivlenght, tobyte.length);

                Cipher cipher = Cipher.getInstance(aesalgoritma); // algorıtmayı ekledım
                cipher.init(Cipher.DECRYPT_MODE, secretkeyspec, new GCMParameterSpec(taglenght, iv)); // cipher confıgını hazırlıyor

                byte[] decrypted = cipher.doFinal(encryptedtext);

                return new String(decrypted, StandardCharsets.UTF_8); // strınge cevırmek ıcın kullandım. utf-8 turunde

            } catch (Exception e) {

                System.out.println(e);

                return null;

            }

        }

    }

}
