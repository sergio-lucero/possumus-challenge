package com.possumus.challenge.service.impl;

import com.possumus.challenge.exception.BadRequestException;
import com.possumus.challenge.exception.enums.ConverterErrorEnum;
import com.possumus.challenge.service.ConverterService;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService {

  private static final String[][] romanos = new String[][]{
      {"", "M", "MM", "MMM"},
      {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
      {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
      {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}
  };


  @Override
  public int converterFromRomanNumber(String romanNumber) {

    int arabic = 0;
    int multiple = 1000;

    int cursor = 0;
    while (!romanNumber.isEmpty()) {

      if(cursor > 3) {
        throw new BadRequestException(ConverterErrorEnum.ROMAN_NUMBER_INVALID);
      }
      for (int j = romanos[cursor].length-1; j>=0;  j--) {
        int size = romanos[cursor][j].length();
        if (size <= romanNumber.length() && romanos[cursor][j].equalsIgnoreCase(romanNumber.substring(0, size))) {
          arabic += j * multiple;
          romanNumber = romanNumber.substring(size);
          break;
        }
      }
      multiple /= 10;
      cursor += 1;
    }

    return arabic;
  }

  @Override
  public String converterFromArabicNumber(int arabicNumber) {
    if(arabicNumber > 3999) {
      throw new BadRequestException(ConverterErrorEnum.ARABIC_MAX);
    }

    if(arabicNumber < 1) {
      throw new BadRequestException(ConverterErrorEnum.ARABIC_MIN);
    }

    int resto = arabicNumber;
    int m = resto / 1000;
    resto = resto % 1000;

    int c = resto / 100;
    resto = resto % 100;

    int d = resto / 10;
    resto = resto % 10;

    int u = resto;
    StringBuilder miles = new StringBuilder();
    miles.append("M".repeat(Math.max(0, m)));

    String result;
    if (arabicNumber >= 1000) {
      result = miles + romanos[1][c] + romanos[2][d] + romanos[3][u];
    } else if (arabicNumber >= 100) {
      result =romanos[1][c] + romanos[2][d] + romanos[3][u];
    } else {
      if (arabicNumber >= 10) {
        result = romanos[2][d] + romanos[3][u];
      } else {
        result = romanos[3][arabicNumber];
      }
    }
    return result;
  }
}
