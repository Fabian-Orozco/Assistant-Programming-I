// <body> Uncomment String in run(), remove code but keep
// comments for readVector() and printOperation()
import java.util.Scanner;

/**
 * A coordinate space to represent physical vectors
 */
public class Solution7_3
{
    /**
     * Gets data from standard input
     */
    private Scanner input = null;

    /**
     * The coordinate system: cartesian or polar indicated
     * in the first line of the standard input
     */
    private String coordinateSystem = null;

    /**
     * Start the execution of the solution
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        Solution7_3 solution = new Solution7_3();
        solution.run();
    }

    /**
     * Run the solution. This method is called from main()
     */
    public void run()
    {
        // Create object to read data from standard input
        input = new Scanner(System.in);

        // Read the coordinate system: cartesian or polar
        /*String*/ coordinateSystem = input.next();

        // Read the two vectors from standard input and print them
        Vector vector1 = readVector(1);
        Vector vector2 = readVector(2);

        // Print the vector operations
        System.out.println();
        printOperation( "V1 + V2", vector1.add(vector2) );
        printOperation( "V1 - V2", vector1.subtract(vector2) );
        printOperation( "V2 - V1", vector2.subtract(vector1) );

        // Close the standard input
        input.close();
    }

    /**
     * Create a Vector object, read its coordinates from standard input,
     * print the vector, and returns the newly created vector
     *
     * @param number The number of the vector to print
     * @return A reference to the new created vector
     */
    public Vector readVector(int number)
    {
        // Create the vector
        Vector vector = new Vector();

        // Read the vector from standard input
        vector.read(input);

        // Print the vector according to the coordinate system
        System.out.println("V" + number + " = " + vector.format(coordinateSystem));

        // Return a reference to the vector
        return vector;
    }

    /**
     * Print an operation made with Vectors and its result.
     * The resulting vector is printed according to the coordinate system
     *
     * @param label  A string explaining the vector operation
     * @param result The resulting vector of the operation
     */
    public void printOperation(String label, Vector result)
    {
        // Print the "label = result" and a new line
        System.out.printf( "%s = %s%n", label, result.format(coordinateSystem) );
    }

} // class Solution

/**
 * Represents a physical vector in a 2D Euclidean space
 */
class Vector
{
    /**
     * x-axis coordinate
     */
    private double x = 0.0;

    /**
     * y-axis coordinate
     */
    private double y = 0.0;

    /**
     * Default constructor
     */
    public Vector()
    {
    }

    /**
     * Builds a Vector which endpoint in the plane is given
     * @param  x x-axis coordinate
     * @param  y y-axis coordinate
     */
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Read the coordinates for this Vector from the given input
     * @param  input The file to read from, usually standard input
     * @return true if this Vector was read and it is not the (0, 0) vector
     */
    public boolean read(Scanner input)
    {
        this.x = input.nextDouble();
        this.y = input.nextDouble();
        return this.x != 0.0 && this.y != 0.0;
    }

    /**
     * Returns a text representing this vector in format (a,b), where
     * a and b are values for a specific coordinate system
     *
     * @param  coordinateSystem The coordinate system to format to. Valid
     * values are "cartesian" and "polar". Otherwise an empty text is returned
     * @return A text representing this vector in form (a, b)
     */
    public String format(String coordinateSystem)
    {
        switch ( coordinateSystem )
        {
            case "cartesian": return formatCartesian();
            case "polar" : return formatPolar();
            default: return "";
        }
    }

    /**
     * Return a text in form (x, y) that represents this Vector in cartesian coordiantes
     * @return A text in the form (x, y)
     */
    public String formatCartesian()
    {
        return String.format("(%s, %s)", format(x), format(y));
    }

    /**
     * Return a text in form (r, θ) that represents this Vector in polar coordiantes
     * @return A text in the form (r, θ)
     */
    public String formatPolar()
    {
        return String.format("(%s, %s)", format(magnitude()), format(direction()));
    }

    /**
     * Converts the given value to String using 0 decimals if the value is integer, or
     * 2 decimals if the value is not integer. This format is more readable for humans
     *
     * @param  value The value to be converted to string
     * @return A formatted text for the value
     */
    public static String format(double value)
    {
        return (long)value == value
            ? String.format("%,d", (long)value)
            : String.format("%.2f", value);
    }

    /**
     * Calculates the magnitude of this Vector using Pythagoras theorem
     * @return The magnitude of the vector
     */
    public double magnitude()
    {
        return Math.sqrt(x*x + y*y);
    }

    /**
     * Calculates the direction of the Vector in the plane as an angle
     * @return The angle in radians
     */
    public double direction()
    {
        return Math.atan2(y, x);
    }

    /**
     * Returns the resulting vector of adding this vector with another
     * @param  other Other vector to add with this one
     * @return A new vector resulting of adding the two vectors
     */
    public Vector add(Vector other)
    {
        return new Vector(x + other.x, y + other.y);
    }

    /**
     * Returns the resulting vector of subtracting the other vector from this one
     * @param  other Other vector to substract from this one
     * @return A new vector resulting of subtracting the two vectors
     */
    public Vector subtract(Vector other)
    {
        return new Vector(x - other.x, y - other.y);
    }
}