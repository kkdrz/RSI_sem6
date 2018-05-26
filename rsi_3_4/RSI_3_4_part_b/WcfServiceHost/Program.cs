using System;
using System.ServiceModel;
using WcfServiceContractWithDataContract;

namespace WcfServiceHost
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            var serviceHost = new ServiceHost(typeof(ComplexCalculator));
            try
            {
                serviceHost.Open();

                Console.WriteLine("ComplexCalculator is running...");
                Console.WriteLine("Press ENTER to close.");

                Console.ReadLine();
                serviceHost.Close();
            }
            catch (CommunicationException ce)
            {
                Console.WriteLine("Exception catched: {0}", ce.Message);
                Console.WriteLine("Aborting..");
                serviceHost.Abort();
            }

        }
    }
}
