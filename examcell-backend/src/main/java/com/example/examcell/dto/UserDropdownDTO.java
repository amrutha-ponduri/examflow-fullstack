package com.example.examcell.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDropdownDTO {
    private int userId;
    private String username;
    private String name;
}
