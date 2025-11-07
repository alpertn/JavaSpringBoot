# KODU OKUMADAN ONCE
## AKTIF ENDPOİNT(POST) https://185.34.101.218/
### Örnek
https://185.34.101.218/api/admin/finduserwithid
```
{

    "id" : "1",
    "adminname" : "root",
    "adminpassword" : "root"


}
```
---
## Genel Tanıtım

Bu proje, AES/GCM encrypt / decrypt destekli, SQL injection korumasi olan, adminlerin endpoint kullandiklari ip bilgileri en son kullandiklari endpointi ve endpoint count'u loglayan, Admin Endpointlerini ve user endpointlerini kullanmak için doğrulama gereken, Kullanıcıların arasında para transverı yapabileceği bir JDK 17 Java Spring Boot Projesidir.

---

## Temel Özellikler ve Faydalar
*   **Log**: Adminlerin endpoıntlerı kullanırkenki ip hangi endpoint kullanıldıgı ve kac defa kullanıldıgı gıbı verılerı loglar.
*   **Encrypt**: Bilgiler **256 bitlik** secret key, **128 bitlik** tag (16 byte) ve **12 byte'lik** IV ile şifrelenir. Aynı secret key ve IV kullanılarak **Decrypt** işlemi yapılır.
*   **Database** : bilgiler mysql uzerinde depolanırr..
*   **Kullanıcı Yönetimi:** Hesap oluşturma, görüntüleme, güncelleme ve silme gibi kapsamlı kullanıcı yönetimi işlevleri sunar. Her kullanıcıya benzersiz bir TCKN ve IBAN atanır.
*   **Yönetici Yetkileri:** Yöneticiler, kullanıcı bakiyelerini güncelleme, kullanıcı bilgilerini sorgulama ve kullanıcı hesaplarını silme gibi tam kontrol sağlayan özel yetkilere sahiptir. Yönetici erişimi, veritabanı tabanlı kimlik doğrulama ile güvence altına alınmıştır.
*   **İşlevsellik:** Kullanıcılar, TCKN ve şifreleri ile güvenli bir şekilde oturum açarak para yatırma ve bakiye sorgulama gibi temel işlemleri gerçekleştirebilir.
*   **SQL Enjeksiyon Koruması:** Proje sql injection denemelerını tespit eder ve durdurure
---
## Kodun Kullanım alanları
*   **Banka Uygulamaları**
*   **Dijital Cüzdan Uygulamalarır**
*   **Bakiye yüklenebilen / harcanabılen tum programlar**
---
## Ön Koşullar ve Bağımlılıklar
Projeyi başarıyla kurmak ve çalıştırmak için aşağıdaki yazılımların sisteminizde yüklü olması gerekmektedir:

*   **JDK 17**
*   **Apache Maven (İntellij idea kullanıyorsanız otomatık yukluyor)** 
*   **MySQL Veritabanı Sunucusu:** Veri tabanı portu 3306 olmalıdır.
*   **IDE (İsteğe Bağlı):** IntelliJ IDEA, Eclipse veya Visual Studio Code gibi bir entegre geliştirme ortamı (IDE) kullanmanız önerilir.
*   **API Test Aracı (Önerilen):** Postman, Insomnia veya benzeri bir HTTP istemcisi, API uç noktalarını test etmek için faydalı olacaktır.


## Veritabanı Kurulumu ve Yapılandırması

Projenin çalışması için gerekli tüm veritabanı kurulum adımları ve SQL komutları aşağıdadır. Bu komutları MySQL istemcinizden sırasıyla çalıştırmanız gerekmektedir.

Not : Tek bir adımda tum sql kodlarını kurmak için
```sql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';  FLUSH PRIVILEGES;CREATE DATABASE ATM; USE ATM; CREATE TABLE admins (id BIGINT PRIMARY KEY AUTO_INCREMENT, adminname VARCHAR(100) NOT NULL, adminpassword VARCHAR(100) NOT NULL, ip_address VARCHAR(45) NOT NULL DEFAULT '0.0.0.0', lastendpoint VARCHAR(255) NOT NULL DEFAULT '', endpointusage INT DEFAULT 1, UNIQUE KEY unique_adminname (adminname)); INSERT INTO admins (adminname, adminpassword) VALUES ('root','root'); CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, ad VARCHAR(100) NOT NULL, soyad VARCHAR(100) NOT NULL, tckimlikno VARCHAR(11) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL, dogumtarihi DATE NOT NULL, iban VARCHAR(34) UNIQUE NOT NULL, balance DECIMAL(40,2) DEFAULT 0.00);
```

Veya tek tek kurmak istersen

```sql

ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;

CREATE DATABASE ATM;

USE ATM;

CREATE TABLE admins (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    adminname VARCHAR(100) NOT NULL,
    adminpassword VARCHAR(100) NOT NULL,
    ip_address VARCHAR(45) NOT NULL DEFAULT '0.0.0.0',
    lastendpoint VARCHAR(255) NOT NULL DEFAULT '',
    endpointusage INT DEFAULT 1,
    UNIQUE KEY unique_adminname (adminname)
);

INSERT INTO admin (adminname, adminpassword) VALUES ('root','root');

CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ad VARCHAR(100) NOT NULL,
    soyad VARCHAR(100) NOT NULL,
    tckimlikno VARCHAR(11) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    dogumtarihi DATE NOT NULL,
    iban VARCHAR(34) UNIQUE NOT NULL,
    balance DECIMAL(40,2) DEFAULT 0.00
);
```

## Kurulum Talimatları

Projeyi sisteminize kurmak ve çalıştırmak için aşağıdaki adımları izleyin:

### 1. Depoyu Klonlayın:
Projenin GitHub deposunu aşağıdaki komutla yerel makinenize klonlayın:
```bash
git clone https://github.com/alpertn/JavaSpringBootAtm.git
cd JavaSpringBootAtm
```

### 2. Veritabanını Hazırlayın:
Yukarıdaki "Veritabanı Kurulumu ve Yapılandırması" bölümündeki SQL komutlarını kullanarak atm veritabanını ve tablolarını oluşturun.

### 3. Veritabanı Bağlantısını Yapılandırın:
Application.properties dosyasını kendi database ve portunuza gore duzenleyin.

#### Localhostta veya internete açık şekilde kolayca çalıştırabılmenız ıcın not ve key bıraktım. Eğer internete acık yapmak ıstıyorsanız not olmayan yerleri silin ve notların ustundekı # ısaretını silip projeyi calıstırın.
```java
#spring.datasource.url=jdbc:mysql://localhost:3306/atm?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Istanbul
#spring.datasource.username=root
#spring.datasource.password=root
#spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#
#
#spring.jpa.hibernate.ddl-auto=update
#spring.jpa.show-sql=true
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
#
### tum iplerden ulasmasi icin bu onemli
##server.address=0.0.0.0
##server.port=443
##server.ssl.enabled=true
##server.ssl.key-store=classpath:keystore.p12
##server.ssl.key-store-password=123456
##server.ssl.key-alias=tomcat
#
#security.aes-key=3f7vX9aT1zPqLkN2sYdBcRgUeWhZjXo1
#
##logging.level.org.springframework=INFO
##logging.level.com.spring.proje=DEBUG
#
# İnternete acmak ıcın cmd'ye bunu yazın ıstege baglı port degısebılır. key olusutrmak ıcın de bir alt satırı yazın.
## netsh http add urlacl url=https://+:443/ user=everyone
## keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650

spring.datasource.url=jdbc:mysql://localhost:3306/atm?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

security.aes-key=3f7vX9aT1zPqLkN2sYdBcRgUeWhZjXo1
```

### 4. Projeyi Maven ile Derleyin:
Projenin bağımlılıklarını indirip derlemek için aşağıdaki Maven komutunu kullanın:
```bash
./mvnw clean install
```

### 5. Uygulamayı Çalıştırın:
## NOT : Hata verirse İntellij idea ile calıstırmayı deneyiniz.
Derleme başarılı olduktan sonra, uygulamayı aşağıdaki komutla çalıştırabilirsiniz:
```bash
java -jar target/atm-0.0.1-SNAPSHOT.jar
```
(Not: JAR dosya adı Maven yapılandırmanıza göre değişebilir; target/ dizininde oluşan uygun JAR dosyasını kullanın.)

---

## Proje Yapısı

Proje, okunabilirliği ve bakımı kolaylaştırmak için modüler bir yapıya sahiptir. Ana dizin ve önemli dosyalar aşağıda açıklanmıştır:

```
src/main/java/com/spring/proje/
├── springexample.java              # Main sınıfı
├── requests/                       # Tüm requestler burada
│
├── admin/                          # admin
│   ├── deleteuserwithid.java       # ID ile kullanıcı silme
│   ├── findidwithiban.java         # IBAN ile kullanıcı ID'si bulma
│   ├── finduserwithiban.java       # IBAN ile kullanıcı bilgilerini bulma
│   ├── finduserwithid.java         # ID ile kullanıcı bilgilerini bulma
│   ├── updatebalancewithiban.java  # IBAN ile bakiye güncelleme
│   └── updatebalancewithid.java    # ID ile bakiye güncelleme
├── admindto/                       # Dto
│   ├── deleteuserwithiddto.java
│   ├── findidwithibandto.java
│   ├── finduserwithibandto.java
│   ├── finduserwithiddto.java
│   ├── updatebalancewithibandto.java
│   └── updatebalancewithiddto.java
├── adminrepository/                # repository
│   ├── checkadmin.java             # databaseye sorgu yapıp verılen bılgılere gore admın mı degılmı cıktısı
│   └── endpointlogger.java         # endpointleri loglama
├── api/                            # User apileri
│   ├── depositmoney.java
│   ├── test.java
│   ├── transaction.java
│   └── user.java
├── config/                         # Uygulama yapılandırmaları
│   ├── checkendpoint.java          # Uç nokta kontrol mekanizmaları
│   └── databaseconfig.java         # Database config Not  kullanılmıyor resourcesde hepsı burayı sadece not olara yaptım
├── controller/                     # REST API isteklerini karşılayan ve işleyen kontrolörler
│   └── AccountController.java      
├── dto/                            
│   ├── depositmoneydto.java
│   ├── ibandto.java
│   ├── iddto.java
│   ├── testdto.java
│   ├── transactiondto.java
│   └── userdto.java
├── httpservletrequest/             # HttpServletRequest 
│   ├── checkuseragent.java         # user agent alma
│   └── getip.java                  # ip alıyor
├── models/                         
│   ├── transaction.java            # transaction modeli
│   └── User.java                   # user modeli
├── repository/                     
│   ├── transactionrepository.java  
│   └── userrepository.java        
├── security/                       
│   ├── checkip.java                
│   ├── clown.java                  # SQL enjeksiyonunda gösterilen ASCII palyaço
│   ├── aesgcmencryption.java      # AES / GCM encrypt decrypt servisi
│   ├── ipdto.java
│   └── sqlinjectiontester.java     # SQL enjeksiyon koruma modülü
└── service/                        
    ├── AccountService.java         
    ├── Iban.java                   # iban olusturma
    └── TransactionService.java     
```

---

## API Kullanımı ve Testler (Postman Örnekleri)

Proje, RESTful API üzerinden yönetilmektedir. API uç noktalarını test etmek ve kullanmak için Postman veya benzeri bir HTTP istemcisi kullanmanız önerilir.

### Genel API Bilgisi:
- **Base URL:** `http://localhost:8080/api/`
- **Yanıt Formatı:** JSON
- **Varsayılan Port:** 8080

### Kullanıcı İşlemleri

| İşlem | Endpoint | HTTP Metodu | Açıklama | Örnek İstek Gövdesi (JSON) | Örnek Yanıt |
|-------|----------|-------------|----------|----------------------------|-------------|
| Yeni Kullanıcı Oluşturma | `/user/createuser` | POST | Yeni bir kullanıcı hesabı oluşturur. IBAN otomatik olarak üretilir. | ```json { "name": "alper", "surname": "karakus", "tckn": "51182934422", "password": "alperkarakus07", "birthdate": "2007-05-25" } ``` | `{"id": "...", "name": "...", ...}` veya hata mesajı |
| Para Yatırma | `/user/depositmoney` | POST | Belirtilen TCKN ve parola ile kullanıcının hesabına para yatırır. | ```json { "tckn": "51182934422", "password": "alperkarakus07", "balance": "1000" } ``` | `{"status": "1"}` (Başarılı) veya `{"status": "0"}` (Başarısız) |

### Yönetici İşlemleri

**Not:** Tüm yönetici işlemleri için istek gövdesinde `adminname` ve `adminpassword` bilgileri gönderilmelidir. Bu bilgiler, veritabanındaki `admin` tablosundan doğrulanır. Varsayılan yönetici bilgileri: `adminname: "root"`, `adminpassword: "root"`.

| İşlem | Endpoint | HTTP Metodu | Açıklama | Örnek İstek Gövdesi (JSON) | Örnek Yanıt |
|-------|----------|-------------|----------|----------------------------|-------------|
| IBAN ile Bakiye Güncelleme | `/admin/updatebalancewithiban` | PUT | Belirtilen IBAN'a sahip kullanıcının bakiyesini günceller. | ```json { "iban": "TR064556002433370544604425", "balance": "1000000", "adminname": "root", "adminpassword": "root" } ``` | Güncelleme durumu (JSON yanıt) |
| IBAN ile Kullanıcı ID Bulma | `/admin/findidwithiban` | POST | Belirtilen IBAN'a sahip kullanıcının ID'sini döndürür. | ```json { "iban": "TR064556002433370544604425", "adminname": "root", "adminpassword": "root" } ``` | `{"id": "1"}` |
| ID ile Bakiye Güncelleme | `/admin/updatebalancewithid` | PUT | Belirtilen ID'ye sahip kullanıcının bakiyesini günceller. | ```json { "id": "3", "balance": "12000000", "adminname": "root", "adminpassword": "root" } ``` | `{"status": "1"}` |
| ID ile Kullanıcı Bilgilerini Bulma | `/admin/finduserwithid` | POST | Belirtilen ID'ye sahip kullanıcının tüm bilgilerini döndürür. | ```json { "id": "1", "adminname": "root", "adminpassword": "root" } ``` | Kullanıcı detayları JSON objesi |
| ID ile Kullanıcı Silme | `/admin/deleteuserwithid` | DELETE | Belirtilen ID'ye sahip kullanıcıyı siler. | ```json { "id": "311", "adminname": "root", "adminpassword": "root" } ``` | `{"status": "0"}` veya `{"status": "1"}` |
| IBAN ile Kullanıcı Bilgilerini Bulma | `/admin/finduserwithiban` | POST | Belirtilen IBAN'a sahip kullanıcının tüm bilgilerini döndürür. | ```json { "iban": "TR197161791918570541333863", "adminname": "root", "adminpassword": "root" } ``` | Kullanıcı detayları JSON objesi |

---

## Güvenlik Özellikleri

### AES/GCM Encrypt / Decrypt
projede yer alan `com.bank.atm.security.aesgcmencrpytion` sınıfı alinan string veriyi 32 haneli secret key ve İV'yi kullanarak sifreler.
```java
    private static final String aesalgoritma = "AES/GCM/NoPadding";
    private static final int taglenght = 128;                     // şifrelenmiş veriye 128 bitlik bir byte ekler.
    private static final int ivlenght = 12;                       // IV bir şifrenin tekrar olusturulurken aynı olmamasını saglar. mesela 2 defa merhaba yazan bir şifre göndersem ikisinide farklı şekilde şifreler. bu şifremeyi aynı yapmamak ıcın değer bu.

    @Value("${security.aes-key}")                                  // applişcation propertiesteki key
    private String secretkey;                                      // key

    private SecretKeySpec secretkeyspec;                                 // secretkey string olarak geldigi icin kullanılamıyor . oyuzden keyspec olarak işleniyor. yani secretkeyin işlenmiş hali.

    @PostConstruct
    public void initsecretkey(){

        byte[] keybytes = secretkey.getBytes(StandardCharsets.UTF_8); // application propertiesteki keyi byte'a donusturuyor. Örnek olarak key merhaba ise [109, 101, 114, 104, 97, 98, 97] 'a donusturuyor getBytes ile.
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
```
ve tekrar string olarak geri dondurur. Aynı şekilde şifrelenmiş veriyi de çözer.
```
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
```

### SQL Enjeksiyon Koruması

Projede yer alan `com.bank.atm.security.sqlinjectiontester` sınıfı sql injection'u algılar.

- **Anahtar Kelimeler:** `select`, `union`, `insert`, `update`, `delete`, `drop`, `or`, `and` (büyük/küçük harf duyarsız).
- **Şüpheli Karakterler:** `'`, `;`, `--`, `/*`, `*/`, `#`.

**sqlinjectiontester.java sınıfının bir kısmı:**
```java
private static final String[] sql = new String[]{"select", "union", "insert", "update", "delete", "drop", "and", "'", ";", "--", "/*", "*/", "#", "or 1=1", "and 1=1"};

public static boolean check(String text) {

    if (text == null || text.isEmpty()) {
            return false;
        }

    String lowertext = text.toLowerCase();


    for (String sqll : sql) {

        if (lowertext.contains(sqll)) {
            return true;
        }
    }

    return false;
}
```

### SQL Enjeksiyon Tespit Edildiğinde 

Sql injection tespit edilirse api yanıtı bu olur ve bad request status code 400 döner.

```java
{
  "status": "sql injection denemesi tespit edildi."
}
```

---

## KODA EKLENECEKLER.

1. presend
```
preSend()
postSend()
preReceive()    
postReceive()
```
 2. Docker
 3. Api altyapısının Apache kafka ile değiştirilmesi 
 4. Flink
 5. Fraud bot koruması spam koruması
 6. Belirli user agent ile admin api'sine erişim
 7. İp blackliste
 8. Geoip
