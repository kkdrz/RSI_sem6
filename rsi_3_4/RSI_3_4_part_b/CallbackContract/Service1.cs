using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace CallbackContract
{
    
        [ServiceBehavior(InstanceContextMode = InstanceContextMode.PerSession)]
        public class CallbackCalculator : ICallbackCalculator { 

            ICallbackHandler callback = null;

            public CallbackCalculator()
            {
                callback = OperationContext.Current.GetCallbackChannel<ICallbackHandler>();
            }

            public void AsyncRandom(RandomSpecification specification)
            {
                var watch = Stopwatch.StartNew();
                var randomIndex = new Random().Next(0, specification.IntNumbersArray.Length);
                watch.Stop();

                var duration = watch.ElapsedMilliseconds;

                callback.ReturnRandom(new RandomResult { Time = duration, Result = specification.IntNumbersArray[randomIndex] });
            }

            public void DateDiff(DateTime date)
            {
                DateTime current = DateTime.Today;
                Console.WriteLine("Current date: " + current);
                callback.ReturnDateDiff((current - date).TotalDays);
            }

    }
}
