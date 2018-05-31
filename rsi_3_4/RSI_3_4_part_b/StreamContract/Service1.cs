using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace StreamContract
{
    public class Stream : IStream
    {
        
        public System.IO.Stream GetStream(RandomSpecification specification)
        {
            System.IO.Stream memoryStream = new MemoryStream();
            System.Random rnd = new System.Random();
            var numbers = specification.IntNumbersArray.OrderBy(r => rnd.Next()).ToArray();

            BinaryWriter writer = new BinaryWriter(memoryStream);
            for(int i = 0; i < numbers.Length; i++)
            {
               writer.Write(numbers[i]);
            }
            return writer.BaseStream;
        }
    }
}
