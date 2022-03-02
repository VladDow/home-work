package com.sbrf.reboot.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String eMail;
}
