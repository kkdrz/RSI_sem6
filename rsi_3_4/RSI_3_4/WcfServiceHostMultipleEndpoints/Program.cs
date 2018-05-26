using System;
using System.ServiceModel;
using System.ServiceModel.Description;
using WcfServiceContract;

namespace WcfServiceHostMultipleEndpoints
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            var serviceHost = new ServiceHost(typeof(Calculator));

            try
            {
                var endpoint1 = serviceHost.Description.Endpoints.Find(
                    new Uri("http://localhost:9000/Calculator/endpoint1"));

                var endpoint2 =
                    serviceHost.Description.Endpoints.Find(
                        new Uri("http://localhost:9000/Calculator/endpoint2"));

                var endpoint3 =
                    serviceHost.Description.Endpoints.Find(
                        new Uri("http://localhost:9001/Calculator/endpoint3"));

                var endpoint4Address = new Uri("net.tcp://localhost:30000/CalculatorTCP");
                var endpoint4 =
                    serviceHost.AddServiceEndpoint(typeof(ICalculator), new NetTcpBinding(), endpoint4Address);

                Console.WriteLine("\nEndpoints:");

                if (endpoint1 != null)
                {
                    Console.WriteLine("{0}  :  {1}  :  {2}", endpoint1.Name, endpoint1.Binding, endpoint1.ListenUri);
                }

                if (endpoint2 != null)
                {
                    Console.WriteLine("{0}  :  {1}  :  {2}", endpoint2.Name, endpoint2.Binding, endpoint2.ListenUri);
                }

                if (endpoint3 != null)
                {
                    Console.WriteLine("{0}  :  {1}  :  {2}", endpoint3.Name, endpoint3.Binding, endpoint3.ListenUri);
                }

                if (endpoint4 != null)
                {
                    Console.WriteLine("{0}  :  {1}  :  {2}", endpoint4.Name, endpoint4.Binding, endpoint4.ListenUri);
                }


                serviceHost.Open();

                var contractDescription = ContractDescription.GetContract(typeof(ICalculator));
                Console.WriteLine("\n\nContract:");
                Console.WriteLine("Type: {0}", contractDescription.ContractType);
                Console.WriteLine("Name: {0}", contractDescription.Name);
                Console.WriteLine("\nProcedures:");

                foreach (var operation in contractDescription.Operations)
                {
                    Console.WriteLine(operation.Name);
                }

                Console.WriteLine("\nService running.");
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