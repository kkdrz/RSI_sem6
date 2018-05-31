using System;
using System.Runtime.Serialization;
using System.ServiceModel;

namespace CallbackContract
{
    [ServiceContract(SessionMode = SessionMode.Required, CallbackContract = typeof(ICallbackHandler))]
    public interface ICallbackCalculator
    {
        [OperationContract(IsOneWay = true)]
        void DateDiff(DateTime date);


        [OperationContract(IsOneWay = true)]
        void AsyncRandom(RandomSpecification specification);
    }

    [ServiceContract]
    public interface ICallbackHandler
    {
        [OperationContract(IsOneWay = true)]
        void ReturnRandom(RandomResult result);

        [OperationContract(IsOneWay = true)]
        void ReturnDateDiff(double days);
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