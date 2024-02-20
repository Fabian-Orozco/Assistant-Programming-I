
/**
 * SOLUCION EN JAVA
 *  import java.io.*;
 *  import java.util.*;
 *
 *  public class Solution7_4 {
 *
 *     public static void main(String[] args) {
 *        // Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution.
 *     }
 *  }
 */

// do it.


// =================================================================================================

/**
 * SOLUCION EN C++
 
 
// ----------------------------------------------------------------- Fraction.h
#ifndef Fraction_h
#define Fraction_h

#include <iostream>

class Fraction
{
   private:
   long long numerator = 0;
   long long denominator = 1;
   
   public:
   /// Default constructor, conversion constructor
   Fraction(long long numerator = 0, long long denominator = 1)
   : numerator(numerator)
   , denominator(denominator)
   {
      simplify();
   }
   
   bool read();
   
   void print() const
   {
      std::cout << numerator << '/' << denominator;
   }
   
   bool simplify();

   long long gcd()
   {
      return gcd(numerator, denominator);
   }
   
   // greatest common divisor
   static long long gcd(long long a, long long b);
   
   Fraction       add(const Fraction& other) const;
   inline Fraction operator+(const Fraction& other) const { return add(other); }
   
   Fraction operator*(const Fraction& other) const;
   Fraction operator/(const Fraction& other) const;
   Fraction operator-(const Fraction& other) const;
   friend std::ostream& operator<<(std::ostream& out, const Fraction& fraction)
   {
      if (fraction.denominator != 1)
      out << fraction.numerator << '/' << fraction.denominator;
      else if (fraction.denominator == 1)
      out << fraction.numerator;
      
      return out;
   }
   friend std::istream& operator>>(std::istream& in, Fraction& fraction)
   {
      // Read fraction in form a/b
      in >> fraction.numerator;
      
      char slash = 0;
      if ( ! (in >> slash))
      return in;
      if (slash != '/')
      {
         in.setstate(std::ios::failbit);
         return in;
      }
      
      in >> fraction.denominator;
      
        fraction.simplify();
        return in;
      }
   };
   
   #endif // Fraction_h
   
   // --------------------------------------------------------------- Calculator.h
   #ifndef Calculator_h
   #define Calculator_h
   
   // #include "Fraction.h"
   
   class Calculator
   {
      private:
      Fraction result;
      Fraction other;
      char operador = ' ';
      
      public:
      int run();
      bool readFraction();
      bool readOperation();
   };

#endif // Calculator_h

// ------------------------------------------------------------------- main.cpp
// #include "Fraction.h"
// #include "Calculator.h"

int main()
{
   Calculator calculator;
   return calculator.run();
}

// --------------------------------------------------------------- Fraction.cpp
// #include "Fraction.h"

bool Fraction::simplify()
{
   long long common = gcd();
   numerator /= common;
   denominator /= common;
   
   if ( denominator < 0 )
   {
      numerator *= -1;
      denominator = -denominator;
   }
   
   return common > 1;
}

long long Fraction::gcd(long long a, long long b)
{
   a = a >= 0 ? a : -a;
   b = b >= 0 ? b : -b;
   
   while ( b > 0 )
   {
      long long t = b;
      b = a % b;
      a = t;
   }
   return a;
}

Fraction Fraction::add(const Fraction& other) const
{
   return Fraction(
      this->numerator * other.denominator + denominator * other.numerator
      , denominator * other.denominator);
   }
   
   Fraction Fraction::operator*(const Fraction& other) const
   {
      return Fraction( numerator * other.numerator, denominator * other.denominator);
   }
   
   Fraction Fraction::operator/(const Fraction& other) const
   {
      return Fraction( numerator * other.denominator, denominator * other.numerator);
   }
   
   Fraction Fraction::operator-(const Fraction& other) const
   {
      return Fraction( numerator * other.denominator - denominator * other.numerator , denominator * other.denominator);
   }
   
   // ------------------------------------------------------------- Calculator.cpp
   // #include "Calculator.h"
   
   int Calculator::run()
   {
      while (true)
      {
         if ( ! readFraction() ) return 0;
         if ( ! readOperation() ) return 0;
      }
   }
   
   bool Calculator::readFraction()
   {
      if ( ! (std::cin >> other) )
      return false;
      
      switch ( operador )
      {
         case '+': result = result + other; break;
         case '*': result = result * other; break;
         case '/': result = result / other; break;
         case '-': result = result - other; break;
         
         case ' ':
         result = other;
         break;
         
         default:
         std::cout << "Err" << std::endl;
         return false;
      }
      
      std::cout << other;
      return true;
   }
   
   bool Calculator::readOperation()
   {
      std::cin >> operador;
      
      std::cout << " " << operador << " ";
      
      if ( operador == '=' )
      {
         std::cout << result << std::endl;
         //limpia el operador
         operador = ' ';
         result = 0;
      }
      
      return std::cin.good();
   }
*/