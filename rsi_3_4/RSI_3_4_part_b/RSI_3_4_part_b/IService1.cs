using System.Runtime.Serialization;
using System.ServiceModel;

namespace WcfServiceContractWithDataContract
{
    [ServiceContract]
    public interface IComplexCalculator
    {
        [OperationContract]
        ComplexNumber AddComplex(ComplexNumber first, ComplexNumber second);

        [OperationContract]
        RandomResult Random(RandomSpecification specification);
    }

    [DataContract]
    public class ComplexNumber
    {
        [DataMember]
        public double Real;
        [DataMember]
        public double Imaginary;

        public ComplexNumber(double real, double imaginary)
        {
            Real = real;
            Imaginary = imaginary;
        }

        public override string ToString()
        {
            return $"[real: {Real}, img: {Imaginary}]";
        }
    }

    [DataContract]
    public class RandomSpecification
    {
        [DataMember]
        public int[] IntNumbersArray;
    }

    [DataContract]
    public class RandomResult
    {
        [DataMember]
        public int Result;
        [DataMember]
        public long Time;
    }
}