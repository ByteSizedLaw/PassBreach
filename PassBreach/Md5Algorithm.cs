using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PassBreach
{
    public class Md5Algorithm : IHashAlgorithm
    {
        public string ComputeHash(string input)
        {
            string st = "";
            using (System.Security.Cryptography.MD5 md5 = System.Security.Cryptography.MD5.Create())
            {
                //create a byte[] from the string in our list
                byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(input);
                //create a new byte[] that consists of the md5 hash of our input
                byte[] hashBytes = md5.ComputeHash(inputBytes);
                //convert the hash to string
                st = Convert.ToHexString(hashBytes); // .NET 5 and up. In .net 4 and lower, we need to do this manually (no method to do it for us)
            }
            return st;
        }
    }
}
