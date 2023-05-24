import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private final String[] numbers = {"ноль", "один", "два", "три",
            "четыре", "пять", "шесть", "семь",
            "восемь", "девять", "десять", "одиннадцать",
            "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private final String[] dozens = {"двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private final String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот",
            "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private final String[] largeNumbersSingle = {"", "тысяча", "миллион", "миллиард", "триллион",
            "квадриллион", "квинтиллион", "секстиллион", "септиллион"};
    private final String[] largeNumbers24 = {"", "тысячи", "миллиона", "миллиарда", "триллиона",
            "квадриллиона", "квинтиллиона", "секстиллиона", "септиллиона"};
    private final String[] largeNumbersOver5 = {"", "тысяч", "миллионов", "миллиардов", "триллионов",
            "квадриллионов", "квинтиллионов", "секстиллионов", "септиллионов"};

    public String translate(String inputNumber) {
        char[] str = (inputNumber.length() % 3 == 0 ? inputNumber : inputNumber.length() % 3 == 1 ? "00" + inputNumber : "0" + inputNumber).toCharArray();
        StringBuilder result = new StringBuilder();
        int rank = str.length / 3 - 1;
        if (inputNumber.length() > 1)
            for (int i = 0; i < str.length; i += 3) {
                int subNumber = Integer.parseInt("" + str[i] + str[i + 1] + str[i + 2]);
                for (int j = 0; j < 3; j++) {
                    int currentCharacter = Integer.parseInt(String.valueOf(str[i + j]));
                    if (currentCharacter != 0) {
                        if (j == 0) {
                            result.append(hundreds[currentCharacter]).append(" ");
                        }
                        if (j == 1 && currentCharacter == 1) {
                            result.append(numbers[Integer.parseInt("" + str[i + j] + str[i + j + 1])]).append(" ");
                            break;
                        } else if (j == 1) {
                            result.append(dozens[currentCharacter - 2]).append(" ");
                        }
                        if (j == 2 && /*subNumber == 1*/(subNumber % 10 == 1 && subNumber % 100 != 11) && rank == 1)
                            result.append("одна ");
                        else if (j == 2 && (subNumber % 10 == 2 && subNumber % 100 != 12) && rank == 1) {
                            result.append("две ");
                        } else if (j == 2) {
                            result.append(numbers[currentCharacter]).append(" ");
                        }
                    }
                }
                if (rank == 0)
                    result.replace(result.length() - 1, result.length(), "");
                else {
                    if (subNumber > 1 && subNumber < 5 || (subNumber > 5 && (subNumber % 10 > 1 && subNumber % 10 < 5) && (subNumber % 100 != 12 && subNumber % 100 != 13 && subNumber % 100 != 14))) {
                        result.append(largeNumbers24[rank]).append(" ");
                        rank--;
                        continue;
                    }
                    if (subNumber == 1 || (subNumber > 5 && subNumber % 10 == 1 && subNumber % 100 != 11)) {
                        result.append(largeNumbersSingle[rank]).append(" ");
                        rank--;
                        continue;
                    }
                    if (subNumber > 4)
                        result.append(largeNumbersOver5[rank]).append(" ");
                }
                rank--;
            }
        else
            result.append(numbers[Integer.parseInt(inputNumber)]);
        return result.toString();
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println(main.translate("112000"));

    }
}
