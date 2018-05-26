using System;
using System.ServiceModel;

namespace WcfServiceContract
{
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class Calculator : ICalculator
    {
        private double sum = 0;

        public double Add(double first, double second)
        {
            return first + second;
        }

        public double Subtract(double first, double second)
        {
            return first - second;
        }

        public double Multiply(double first, double second)
        {
            return first * second;
        }

        public double Sum(double number)
        {
            sum += number;
            return sum;
        }
    }
}