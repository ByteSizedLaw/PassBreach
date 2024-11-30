using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PassBreach
{
    public class CalculateHash
    {
        public static string GetHash(string input, IHashAlgorithm alg)
        {
            return alg.ComputeHash(input);
        }
    }
}
