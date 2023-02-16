package polishNotation;

import java.util.Stack;

// (1 + 2) * 4 + 3 = 1 2 + 4 * 3 +
// 1 + 2 * 2 = 1 2 2 * +
public class Main {
    public static void main(String[] args) {
        String input = "(1 + 2) * 4 + 3".replace(" ", "");
        char[] a = input.toCharArray();
        char[] b = reversedPolishNotation(a);
        int res = calculation(b);
        System.out.println(res);
    }

    public static char[] reversedPolishNotation(char[] input) {
        Stack<Character> lexemes = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : input) {
            if (Character.isDigit(c)) {
                result.append(c);
            } else {
                switch (c) {
                    case '(':
                        lexemes.add(c);
                        break;
                    case ')':
                        try {
                            while (lexemes.peek() != '(') {
                                result.append(lexemes.pop());
                            }
                            lexemes.pop();
                            break;
                        } catch (Exception e) {
                            System.out.println("Error");
                            break;
                        }
                    case '*':
                    case '/':
                        if (!lexemes.isEmpty()) {
                            char lastLexeme = lexemes.peek();
                            if (lastLexeme == '*' || lastLexeme == '/') {
                                result.append(lexemes.pop());
                                lexemes.add(c);
                            } else {
                                lexemes.add(c);
                            }
                        } else {
                            lexemes.add(c);
                        }
                        break;
                    case '+':
                    case '-':
                        if (!lexemes.isEmpty()) {
                            char lastLexeme = lexemes.peek();
                            if (lastLexeme == '*' || lastLexeme == '/' || lastLexeme == '+' || lastLexeme == '-') {
                                result.append(lexemes.pop());
                                lexemes.add(c);
                            } else {
                                lexemes.add(c);
                            }
                        } else {
                            lexemes.add(c);
                        }
                        break;
                }
            }
        }
        while (!lexemes.isEmpty()) {
            result.append(lexemes.pop());
        }
        return result.toString().toCharArray();
    }

    public static int calculation(char[] reversedPolishNotation) {
        Stack<Integer> numbers = new Stack<>();
        int result = 0;
        for (Character item : reversedPolishNotation) {
            if (Character.isDigit(item)) {
                numbers.add(Integer.parseInt(String.valueOf(item)));
            } else {
                switch (item) {
                    case '+' -> {
                        result = numbers.pop() + numbers.pop();
                        numbers.add(result);
                    }
                    case '-' -> {
                        result = numbers.pop() - numbers.pop();
                        numbers.add(result);
                    }
                    case '*' -> {
                        result = numbers.pop() * numbers.pop();
                        numbers.add(result);
                    }
                    case '/' -> {
                        result = numbers.pop() / numbers.pop();
                        numbers.add(result);
                    }
                }
            }
        }
        return result;
    }


}