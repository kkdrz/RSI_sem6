using System.ServiceModel;

namespace WcfServiceContract
{
    [ServiceContract]
    public interface ICalculator
    {
        [OperationContract]
        double Add(double first, double second);

        [OperationContract]
        double Subtract(double first, double second);

        [OperationContract]
        double Multiply(double first, double second);

        [OperationContract]
        double Sum(double number);
    }
}