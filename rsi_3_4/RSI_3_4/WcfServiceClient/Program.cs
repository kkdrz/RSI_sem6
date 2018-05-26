using System;

namespace WcfServiceClient
{
    internal class Program
    {
        private static void Main()
        {
            var client1 = new ServiceReference1.CalculatorClient("endpoint1");
            var client2 = new ServiceReference1.CalculatorClient("endpoint2");
            var client3 = new ServiceReference1.CalculatorClient("endpoint3");
            var client4 = new ServiceReference1.CalculatorClient("NetTcpBinding_ICalculator");

            float value1 = float.Parse(Console.ReadLine());
            float value2 = float.Parse(Console.ReadLine());

            var addResult = client1.Add(value1, value2);
            Console.WriteLine("Client1: {0} + {1} = {2}", value1, value2, addResult);

            var subResult = client1.Subtract(value1, value2);
            Console.WriteLine("Client1: {0} - {1} = {2}", value1, value2, subResult);

            var multResult = client1.Multiply(value1, value2);
            Console.WriteLine("Client1: {0} * {1} = {2}", value1, value2, multResult);

            var sumResult = client1.Sum(value1);
            sumResult = client1.Sum(value2);
            Console.WriteLine("Client1: TotalSum + {0} + {1} = {2}", value1, value2, sumResult);

            client1.Close();

            addResult = client2.Add(value1, value2);
            Console.WriteLine("Client2: {0} + {1} = {2}", value1, value2, addResult);

            subResult = client2.Subtract(value1, value2);
            Console.WriteLine("Client2: {0} - {1} = {2}", value1, value2, subResult);

            multResult = client2.Multiply(value1, value2);
            Console.WriteLine("Client2: {0} * {1} = {2}", value1, value2, multResult);

            sumResult = client2.Sum(value1);
            sumResult = client2.Sum(value2);
            Console.WriteLine("Client2: TotalSum + {0} + {1} = {2}", value1, value2, sumResult);
            client2.Close();


            addResult = client3.Add(value1, value2);
            Console.WriteLine("Client3: {0} + {1} = {2}", value1, value2, addResult);

            subResult = client3.Subtract(value1, value2);
            Console.WriteLine("Client3: {0} - {1} = {2}", value1, value2, subResult);

            multResult = client3.Multiply(value1, value2);
            Console.WriteLine("Client3: {0} * {1} = {2}", value1, value2, multResult);

            sumResult = client3.Sum(value1);
            sumResult = client3.Sum(value2);
            Console.WriteLine("Client3: TotalSum + {0} + {1} = {2}", value1, value2, sumResult);

            client3.Close();

            addResult = client4.Add(value1, value2);
            Console.WriteLine("Client4: {0} + {1} = {2}", value1, value2, addResult);

            subResult = client4.Subtract(value1, value2);
            Console.WriteLine("Client4: {0} - {1} = {2}", value1, value2, subResult);

            multResult = client4.Multiply(value1, value2);
            Console.WriteLine("Client4: {0} * {1} = {2}", value1, value2, multResult);

            sumResult = client4.Sum(value1);
            sumResult = client4.Sum(value2);
            Console.WriteLine("Client4: TotalSum + {0} + {1} = {2}", value1, value2, sumResult);

            client4.Close();

            Console.ReadLine();
        }
    }
}