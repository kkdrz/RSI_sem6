using System;
using WcfServiceClient.ServiceReference1;

namespace WcfServiceClient
{
    internal static class Program
    {
        private static void Main(string[] args)
        {
            var client = new ComplexCalculatorClient();

            int[] inputArray = { 24, 43, 22, 33, 11, 3, 4, 53, 221 };

            Console.WriteLine("Input: {24, 43, 22, 33, 11, 3, 4, 53, 221}");

            var spec = new RandomSpecification { IntNumbersArray = inputArray };

            var result = client.Random(spec);

            Console.WriteLine("Result: " + result.Result);
            Console.WriteLine("Time: " + result.Time);
            Console.ReadLine();

        }
    }
}