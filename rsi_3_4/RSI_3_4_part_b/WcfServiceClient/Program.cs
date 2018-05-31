using System;
using System.ServiceModel;
using WcfServiceClient.ServiceReference1;
using WcfServiceClient.ServiceReference2;

namespace WcfServiceClient
{
    internal static class Program
    {
        private static void Main(string[] args)
        {
            //SYNC PART
            /*
            var syncClient = new ComplexCalculatorClient();

            int[] inputArray = { 24, 43, 22, 33, 11, 3, 4, 53, 221 };

            Console.WriteLine("Input: {24, 43, 22, 33, 11, 3, 4, 53, 221}");

            var spec = new ServiceReference1.RandomSpecification { IntNumbersArray = inputArray };

            var result = syncClient.Random(spec);
            Console.WriteLine("Sync Result: " + result.Result);
            Console.WriteLine("Sync Time: " + result.Time);
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            */

            //ASYNC CALLBACK
            int[] inputArray = { 24, 43, 22, 33, 11, 3, 4, 53, 221 };
            var handler = new CallbackHandler();
            var context = new InstanceContext(handler);
            var callbackClient = new CallbackCalculatorClient(context);
            /*
            DateTime date = new DateTime(2018, 2, 28);
            Console.WriteLine("First date: " + date);
            callbackClient.DateDiffAsync(date);
            Console.WriteLine("test");
            Console.WriteLine("test");
            Console.WriteLine("test");
            Console.WriteLine("test");
            Console.WriteLine("test");
            Console.Read();

            */
            Console.WriteLine("Input: {24, 43, 22, 33, 11, 3, 4, 53, 221}");

            var specification = new ServiceReference2.RandomSpecification { IntNumbersArray = inputArray };

            callbackClient.AsyncRandom(specification);
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.WriteLine("SPAM");
            Console.Read();
            
        }
        
    }

    public class CallbackHandler : ICallbackCalculatorCallback
    {
        public void ReturnDateDiff(double days)
        {
            Console.WriteLine("DateDiff result: " + days);
            Console.ReadLine();
        }

        public void ReturnRandom(ServiceReference2.RandomResult result)
        {
            Console.WriteLine("Callback Result: " + result.Result);
            Console.WriteLine("Callback Time: " + result.Time);
            Console.ReadLine();
        }

    }
}