package Calculator;

import java.util.Scanner;

class Stdout {
    int returnNumber;
    String returnRomansOne;
    String returnRomansTwo;

    void performsCalculations(Stdin input) {
        if (input.operation == '+')
            this.returnNumber = input.firstNumber + input.secondNumber;
        if (input.operation == '-')
            this.returnNumber = input.firstNumber - input.secondNumber;
        if (input.operation == '*')
            this.returnNumber = input.firstNumber * input.secondNumber;
        if (input.operation == '/')
            this.returnNumber = input.firstNumber / input.secondNumber;
    }

    String getRomanResultOne(Stdin input) {
        int i = this.returnNumber / 10;

        if (input.flagRomanFirstNumber == 1 && input.flagRomanSecondNumber == 1 && this.returnNumber <= 0)
            Calculator.errorExit("Ошибка: отрицательный или нулевой результат при работе с римскими цифрами невозможен");
        if (input.flagRomanFirstNumber == 1 && input.flagRomanSecondNumber == 1 && this.returnNumber >=10) {
            switch (i) {
                case 1: return ("X");
                case 2: return ("XX");
                case 3: return ("XXX");
                case 4: return ("XL");
                case 5: return ("L");
                case 6: return ("LX");
                case 7: return ("LXX");
                case 8: return ("LXXX");
                case 9: return ("XC");
                case 10: return ("C");
            }
        }
        return (null);
    }

    String getRomanResultTwo(Stdin input) {
        int i = this.returnNumber;
        if (this.returnNumber >= 10)
            i = this.returnNumber % 10;
        if (input.flagRomanFirstNumber == 1 && input.flagRomanSecondNumber == 1) {
            switch (i) {
                case 1: return ("I");
                case 2: return ("II");
                case 3: return ("III");
                case 4: return ("IV");
                case 5: return ("V");
                case 6: return ("VI");
                case 7: return ("VII");
                case 8: return ("VIII");
                case 9: return ("IX");
            }
        }
        return (null);
    }

    void printResult(Stdin input) {
        if (input.flagRomanFirstNumber == 1 && input.flagRomanSecondNumber == 1) {
            if (this.returnRomansOne != null && this.returnRomansTwo == null)
                System.out.println(this.returnRomansOne);
            if (this.returnRomansOne != null && this.returnRomansTwo != null) {
                System.out.print(this.returnRomansOne);
                System.out.println(this.returnRomansTwo);
            }
            if (this.returnRomansOne == null && this.returnRomansTwo != null)
                System.out.println(this.returnRomansTwo);
        }
        else
            System.out.println(this.returnNumber);
    }
}

class Stdin {
    String[] enterData;
    char operation = '0';
    int firstNumber = 0;
    int secondNumber = 0;
    int flagRomanFirstNumber = 0;
    int flagRomanSecondNumber = 0;

    int checkRoman(String str) {
        switch (str) {
            case "I": case "II": case "III": case "IV": case "V":
            case "VI": case "VII": case "VIII": case "IX": case "X": return (1);
            default: return (0);
        }
    }

    char convertsOperation(String str) {
        switch (str) {
            case "+": return ('+');
            case "-": return ('-');
            case "*": return ('*');
            case "/": return ('/');
            default: Calculator.errorExit("Ошибка: некорректный знак операции");
        }
        return (0);
    }

    int convertStrToInt(String str) {
        switch(str) {
            case "10": case "X": return (10);
            case "1": case "I": return (1);
            case "2": case "II": return (2);
            case "3": case "III": return (3);
            case "4": case "IV": return (4);
            case "5": case "V": return (5);
            case "6": case "VI": return (6);
            case "7": case "VII": return (7);
            case "8": case "VIII": return (8);
            case "9": case "IX": return (9);
            default: return (0);
        }
    }

    void checkInput() {
        if (this.operation == '0' || this.firstNumber == 0 || this.secondNumber == 0)
            Calculator.errorExit("Ошибка: некорректно введена строка");
        if ((this.flagRomanFirstNumber == 0 && this.flagRomanSecondNumber == 1) ||
                (this.flagRomanFirstNumber == 1 && this.flagRomanSecondNumber == 0))
            Calculator.errorExit("Ошибка: одновременно используются разные системы исчисления");
    }
}

public class Calculator {
    public static void errorExit(String str) {
        System.out.println(str);
        System.exit(0);
    }

    static void info() {
        System.out.println("Калькулятор");
        System.out.println("Для вычисления результата математической операции введите данные, соблюдая следующие правила:");
        System.out.println("- калькулятор работает с числами от 0 до 10 включительно");
        System.out.println("- между числами и знаком должен быть один пробел");
        System.out.println("- допустимые операции: сложение (\"+\"), вычитание (\"-\"), умножение (\"*\") и деление (\"\\\")");
        System.out.println("Пример: \"3 + 5\"");
    }

    public static void main(String[] args) {
        Calculator.info();
        Scanner console = new Scanner(System.in);
        String stdinStr = console.nextLine();
        Stdin input = new Stdin();
        input.enterData = stdinStr.split(" ");
        if (input.enterData.length != 3)
            Calculator.errorExit("Ошибка: некорректно введена строка");
        input.firstNumber = input.convertStrToInt(input.enterData[0]);
        input.secondNumber = input.convertStrToInt(input.enterData[2]);
        input.operation = input.convertsOperation(input.enterData[1]);
        input.flagRomanFirstNumber = input.checkRoman(input.enterData[0]);
        input.flagRomanSecondNumber = input.checkRoman(input.enterData[2]);
        input.checkInput();
        Stdout output = new Stdout();
        output.performsCalculations(input);
        output.returnRomansOne = output.getRomanResultOne(input);
        output.returnRomansTwo = output.getRomanResultTwo(input);
        output.printResult(input);
    }
}
