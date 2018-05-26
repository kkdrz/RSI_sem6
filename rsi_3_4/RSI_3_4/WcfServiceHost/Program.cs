using System;
using WcfServiceContract;
using System.ServiceModel;
using System.ServiceModel.Description;

namespace WcfServiceHost
{
    public class Program
    {
        private static void Main()
        {
            var baseAddress = new Uri("http://localhost:9000/Calculator/");
            var serviceHost = new ServiceHost(typeof(WcfServiceContract.Calculator), baseAddress);

            try
            {
                var serviceBinding = new WSHttpBinding();
                serviceHost.AddServiceEndpoint(typeof(ICalculator), serviceBinding, "endpointName");

                var metadataBehaviour = new ServiceMetadataBehavior { HttpGetEnabled = true };
                serviceHost.Description.Behaviors.Add(metadataBehaviour);

                serviceHost.Open();
                Console.WriteLine("Service running...");
                Console.WriteLine("Press ENTER to close.");
                Console.WriteLine();

                Console.ReadLine();
                serviceHost.Close();
            }
            catch (CommunicationException ce)
            {
                Console.WriteLine("Exception: {0}", ce.Message);
                serviceHost.Abort();
            }
        }
    }
}