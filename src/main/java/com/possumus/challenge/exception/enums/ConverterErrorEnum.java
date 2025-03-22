package com.possumus.challenge.exception.enums;

public enum ConverterErrorEnum implements ErrorEnum {

  ARABIC_MIN("error.converter.arabic.number.min"),
  ARABIC_MAX("error.converter.arabic.number.max"),
  ROMAN_NUMBER_INVALID("error.converter.number.roman.invalid");

  private final String code;

  ConverterErrorEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
