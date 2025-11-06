package com.spring.proje.repository;

import com.spring.proje.models.User;
import com.spring.proje.security.aesgcmencryption; // EncryptionService import edildi
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class userrepository {

    public static JdbcTemplate jdbctemplate;
    private final aesgcmencryption encryptionService;
    private static aesgcmencryption staticEncryptionService; // RowMapperUser için statik yapmam lazim aksi taktirde hata aliyorum

    public userrepository(JdbcTemplate jdbctemplate, aesgcmencryption encryptionService) {
        this.jdbctemplate = jdbctemplate;
        this.encryptionService = encryptionService;
        this.staticEncryptionService = encryptionService; // Statik alan için initialize
    }

    // RowMapper ile sqlden aldıgımız verileri User Class'ına donusturuyoruz.
    private static final RowMapper<User> RowMapperUser = (resultset, rownum) -> {

        User user = new User();

        user.setid(resultset.getLong("id")); // Sqlden ID'yi long olarak alıyor. User class'ında id'yi Long olarak tanımladım.
        user.setname(resultset.getString("ad"));
        user.setsurname(resultset.getString("soyad"));
        user.settckn(resultset.getString("tckimlikno"));
        user.setpassword(staticEncryptionService.decrypt(resultset.getString("password")));
        user.setbirthdate(resultset.getDate("dogumtarihi").toLocalDate());
        user.setiban(resultset.getString("iban"));
        user.setbalance(resultset.getFloat("balance"));

        return user; // hazırladıgımız user degiskenini donduruyoruz.
    };

    // User türünde bir modül olusturduk. Save modülü User tipinde veri alıyor. sql stringi hazırladık. jdbctemplate ile ? yazılan yerlere user değişkeninin icindekileri aktardık.
    public User save(User saveuser) {
        String encryptedPassword = encryptionService.encrypt(saveuser.getpassword());

        String tosql = "INSERT INTO Users (ad,soyad,password,iban,tckimlikno,dogumtarihi,balance) VALUES (?,?,?,?,?,?,?)"; // Insert ediyoruz.

        jdbctemplate.update(tosql, saveuser.getname(), saveuser.getsurname(), encryptedPassword, saveuser.getiban(), saveuser.gettckn(), saveuser.getbirthdate(), saveuser.getbalance());

        return saveuser;
    }

    public static Optional<User> idsorgu(Long gelenid) { // user olarak donduruoruz.
        String tosql = "SELECT * FROM Users WHERE id = ?";
        var sql = jdbctemplate.query(tosql, RowMapperUser, gelenid).stream().findFirst(); // .stream() kullanmayınca .findFirst() gibi methodları kullanamıyoruz. sql kodunu gönderiyor. veriyi rowmapper'e kaydediyor. ? diye bıraktıgımızı da id ile degistiriyor.

        return sql; // sqlden gelen veriyi donduruyoruz.
    }

    public Boolean DeleteUser(Long id) {
        String tosql = "DELETE FROM USERS WHERE id = ?";
        var sql = jdbctemplate.update(tosql, id);

        if (sql > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean Updatebakiye(float yenibakiye, String id) {
        String tosql = "UPDATE users SET balance = ? WHERE id = ?";
        var sql = jdbctemplate.update(tosql, yenibakiye, id);

        if (sql > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean updatebalancewithiban(float yenibakiye, String iban) {
        String tosql = "UPDATE users SET balance = ? WHERE iban = ?";
        var sql = jdbctemplate.update(tosql, yenibakiye, iban);

        if (sql > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Long findid() {
        String tosql = "SELECT MAX(id) AS max_id FROM users;";
        var id = jdbctemplate.queryForObject(tosql, Long.class);
        if (id == null) { // databasede verı olmayınca hata verıyor oyuzden +1 ypamak zorunlu
            return 1L;
        }
        return id;
    }

    public Optional<User> findbyiban(String iban) {
        String tosql = "SELECT * FROM Users WHERE iban = ?";
        Optional<User> user = jdbctemplate.query(tosql, RowMapperUser, iban).stream().findFirst();

        return user;
    }

    public static Optional<User> findaccountbyiban(String iban) {
        String tosql = "SELECT * FROM Users WHERE iban = ?";
        Optional<User> user = jdbctemplate.query(tosql, RowMapperUser, iban).stream().findFirst();

        return user;
    }

    public String findbyiban2(String iban) {
        var tosql = "SELECT id FROM users WHERE iban = ?";
        var ibann = jdbctemplate.queryForObject(tosql, String.class, iban).toString();
        return ibann;
    }
}