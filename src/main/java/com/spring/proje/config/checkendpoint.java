package com.spring.proje.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class checkendpoint implements CommandLineRunner {

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final RequestMappingHandlerMapping handlerMapping;

    @Autowired
    public checkendpoint(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }

    @Override
    public void run(String... args) {
        System.out.println(ANSI_GREEN + "---- Aktif Endpointler ----" + ANSI_RESET);        // bu sayfayı gptye yazdırdım

        handlerMapping.getHandlerMethods().forEach((info, method) -> {
            if (info != null) {
                if (info.getPathPatternsCondition() != null) {
                    info.getPathPatternsCondition().getPatterns().forEach(p -> {
                        String path = p.getPatternString();
                        if (!path.equals("/error")) {
                            System.out.println(ANSI_GREEN + path + ANSI_RESET);
                        }
                    });
                }
                else if (info.getPatternsCondition() != null) {
                    info.getPatternsCondition().getPatterns().forEach(p -> {
                        if (!"/error".equals(p)) {
                            System.out.println(ANSI_GREEN + p + ANSI_RESET);
                        }
                    });
                }
            }
        });
    }
}
