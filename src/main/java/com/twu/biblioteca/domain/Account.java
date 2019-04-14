package com.twu.biblioteca.domain;

import com.twu.biblioteca.utils.StringUtils;
import lombok.Data;

import static com.twu.biblioteca.consts.ApplicationConstant.INFO_SLICER;
import static com.twu.biblioteca.consts.ApplicationConstant.LANDSCAPE_LACE;

@Data
public class Account implements Describable {
    private String name;
    private String email;
    private String phoneNumber;

    private String password;
    private String libraryNumber;

    public Account(String name, String email, String phoneNumber, String libraryNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    @Override
    public String briefSelfIntroduce() {
        return LANDSCAPE_LACE +
                INFO_SLICER + StringUtils.smooth(this.name, 16) +
                INFO_SLICER + StringUtils.smooth(this.email, 24) +
                INFO_SLICER + StringUtils.smooth(this.phoneNumber, 16) +
                INFO_SLICER + LANDSCAPE_LACE;
    }
}
