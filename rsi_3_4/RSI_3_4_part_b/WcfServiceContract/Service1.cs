using System;
using System.Diagnostics;

namespace WcfServiceContractWithDataContract
{
    public class ComplexCalculator : IComplexCalculator
    {
        public ComplexNumber AddComplex(ComplexNumber first, ComplexNumber second)
        {
            return new ComplexNumber(first.Real + second.Real, first.Imaginary + second.Imaginary);
        }

        public RandomResult Random(RandomSpecification specification)
        {
            var watch = Stopwatch.StartNew();
            var randomIndex = new Random().Next(0, specification.IntNumbersArray.Length);
            watch.Stop();

            var duration = watch.ElapsedMilliseconds;

            return new RandomResult { Time = duration, Result = specification.IntNumbersArray[randomIndex] };
        }
    }
}