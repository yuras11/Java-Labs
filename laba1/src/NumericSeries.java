public class NumericSeries {
    double m_x;
    double m_accuracy;

    public NumericSeries(double x, double accuracy)
    {
        this.m_x = x;
        this.m_accuracy = accuracy;
    }

    public double SumOfSeries()
    {
        double sum = 0, summand = -m_x/4, k = 1;
        do
        {
            sum += summand;
            summand *= -m_x * (k + 1) * (k + 1) / ((k + 2) * (k + 2));
            k++;
        }
        while (Math.abs(summand) > m_accuracy);
        return sum;
    }

    public static void main(String[] args)
    {
        double x = 0, accuracy = 0;
        if (args.length != 2)
        {
            throw new IllegalArgumentException();
        }
        try
        {
            x = Double.parseDouble(args[0]);
            accuracy = Double.parseDouble(args[1]);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Incorrect input!");
            return;
        }
        NumericSeries series = new NumericSeries(x, accuracy);
        System.out.println(series.SumOfSeries());
    }
}