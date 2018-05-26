using CallbackContract;
using System;
using System.ServiceModel;
using System.ServiceModel.Description;
using WcfServiceContractWithDataContract;

namespace WcfServiceHost
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            
            ServiceHost callbackServiceHost = new ServiceHost(typeof(CallbackCalculator));
            WSDualHttpBinding dualHttpBinding = new WSDualHttpBinding();

            var serviceHost = new ServiceHost(typeof(ComplexCalculator));
            try
            {
                //ServiceEndpoint callbackEndpoint = serviceHost.AddServiceEndpoint(typeof(ICallbackCalculator), dualHttpBinding, "CallbackCalculator");
                //ServiceEndpoint complexEndpoint = serviceHost.AddServiceEndpoint(typeof(IComplexCalculator), new BasicHttpBinding(), "ComplexCalculator");

                //ServiceMetadataBehavior smb = new ServiceMetadataBehavior();
                //smb.HttpGetEnabled = true;
                //serviceHost.Description.Behaviors.Add(smb);

                serviceHost.Open();
                Console.WriteLine("Service is running...");
                callbackServiceHost.Open();
                Console.WriteLine("CallbackService is running...");
                Console.WriteLine("Press anything to close.");

                Console.ReadLine();
                serviceHost.Close();
            } catch (CommunicationException e)
            {
                serviceHost.Abort();
            }
            /*
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
            */

        }
    }

   
}
