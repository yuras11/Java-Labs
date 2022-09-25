import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) throw new InputMismatchException("There are too many arguments");
        System.out.println(CalculateTheExpression(args[0], Integer.parseInt(args[1])));
    }

    public static int CalculateTheExpression(String expression, int x) throws IllegalArgumentException
    {
        StringTokenizer stringTokenizer = new StringTokenizer(expression.replaceAll("\\s+", ""), "-|+", true);
        String firstToken = stringTokenizer.nextToken();
        if (firstToken.equals("+") || firstToken.equals("-")) throw new IllegalArgumentException("Expression cannot start with the operation");
        int result = Character.isDigit(firstToken.charAt(0)) ? Integer.parseInt(firstToken) : x;
        while (stringTokenizer.hasMoreTokens())
        {
            String currentToken = stringTokenizer.nextToken();
            if (currentToken.equals("+"))
            {
                String nextToken = stringTokenizer.nextToken();
                result += Character.isDigit(nextToken.charAt(0)) ? Integer.parseInt(nextToken) : x;
            }
            if (currentToken.equals("-"))
            {
                String nextToken = stringTokenizer.nextToken();
                result -= Character.isDigit(nextToken.charAt(0)) ? Integer.parseInt(nextToken) : x;
            }
        }
        return result;
    }
}
