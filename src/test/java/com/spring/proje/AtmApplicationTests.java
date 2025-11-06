package com.spring.proje;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Test profili kullan
class AtmApplicationTests {

	@Test
	void contextLoads() {
		// Bağlamın yüklenip yüklenmediğini test eder
	}
}