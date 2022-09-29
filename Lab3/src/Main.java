import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String path = "C:\\Users\\Yuriy Kozlov\\IdeaProjects\\Lab3\\src\\input.txt";
        File file = new File(path);
        try
        {
            printSolution(findSolution(fillMatrix(file)));
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e.getMessage());
            return;
        }
        catch (NoSuchElementException e)
        {
            System.err.println("There is a wrong amount of arguments");
            return;
        }
    }

    public static void printSolution(double[] solution)
    {
        System.out.println("The solution is:");
        System.out.print("( ");
        for (int j = 0; j < solution.length; j++)
        {
            System.out.print(solution[j]);
            System.out.print("  ");
        }
        System.out.print(")");
    }
    public static void pasteZeros(double[][] matrix)
    {
        for(int i = 1; i < matrix.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                matrix[i][j] = 0;
            }
        }
    }
    public static double[][] fillMatrix(File file) throws FileNotFoundException, IllegalArgumentException, NoSuchElementException
    {
        Scanner input = new Scanner(file);
        int amountOfEquations = input.nextInt();
        if (amountOfEquations <= 0) throw new IllegalArgumentException("Amount of equations cannot be less than of equal to 0");
        double[][] mainMatrix = new double[amountOfEquations][amountOfEquations + 1];
        pasteZeros(mainMatrix);
        for (int i = 0; i < amountOfEquations; i++)
        {
            for (int j = i; j < amountOfEquations + 1; j++)
            {
                mainMatrix[i][j] = input.nextInt();
            }
        }
        if (input.hasNext()) throw new NoSuchElementException();
        input.close();
        int product = 1;
        for (int i = 0; i < amountOfEquations; i++)
        {
            product *= mainMatrix[i][i];
        }
        if (product == 0) throw new IllegalArgumentException("The system of the equations has an infinite number of solutions");
        return mainMatrix;
    }

    public static void subtractLines(double[] line1, double[] line2)
    {
        for (int i = 0; i < line1.length; i++)
        {
            line1[i] -= line2[i];
        }
    }

    public static void multipleLineOnNumber(double[] line, double number)
    {
        for (int i = 0; i < line.length; i++)
        {
            line[i] *= number;
        }
    }

    public static void divideLineOnNumber(double[] line, double number)
    {
        for (int i = 0; i < line.length; i++)
        {
            line[i] /= number;
        }
    }

    public static double[] findSolution(double[][] matrix)
    {
        double[] solution = new double[matrix.length];

        for (int i = matrix.length - 1; i > 0; i--)
        {
            divideLineOnNumber(matrix[i], matrix[i][i]);
            for (int j = i - 1; j >= 0; j--)
            {
                double current = matrix[j][i];
                multipleLineOnNumber(matrix[i], current);
                subtractLines(matrix[j], matrix[i]);
                divideLineOnNumber(matrix[i], current);
            }
        }

        for (int j = 0; j < matrix.length; j++)
        {
            solution[j] = matrix[j][matrix.length];
        }
        return solution;
    }
}