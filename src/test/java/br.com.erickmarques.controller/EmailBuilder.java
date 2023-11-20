package br.com.erickmarques.controller;

import java.util.Collections;
import java.util.List;

public class EmailBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static EmailDto getDto() {
        EmailDto dto = new EmailDto();
        dto.setId("1");
        return dto;
    }
}
