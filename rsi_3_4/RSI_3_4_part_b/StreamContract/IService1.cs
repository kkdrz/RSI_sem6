using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace StreamContract
{
    
    [ServiceContract]
    public interface IStream
    {
        [OperationContract]
        System.IO.Stream GetStream(RandomSpecification spec);

    }

    [DataContract]
    public class RandomSpecification
    {
        [DataMember]
        public int[] IntNumbersArray;
    }
}
