using System.Collections.Concurrent;

namespace PassBreach
{
    public class HashCracker : IHashCracker
    {
        private readonly IConcurrentQueueManager queueManager;
        private readonly IHashAlgorithm hashAlgorithm;
        private readonly string targetHash;
        private readonly char[] characters;
        private int maxPasswordLength = 3; //default value is 3

        public HashCracker(IConcurrentQueueManager queueManager, IHashAlgorithm hashAlgorithm, string targetHash, char[] characters, int maxPasswordLength)
        {
            this.queueManager = queueManager;
            this.hashAlgorithm = hashAlgorithm;
            this.targetHash = targetHash;
            this.characters = characters;
            this.maxPasswordLength = maxPasswordLength;
        }

        public string CrackPassword()
        {
            ConcurrentQueue<string> currentCombinations = new ConcurrentQueue<string>();
            currentCombinations.Enqueue("");

            while (!currentCombinations.IsEmpty)
            {
                ConcurrentQueue<string> newCombinations = new ConcurrentQueue<string>();

                while (currentCombinations.TryDequeue(out string currentString))
                {
                    if (hashAlgorithm.ComputeHash(currentString) == targetHash)
                    {
                        return currentString;
                    }

                    if (currentString.Length < maxPasswordLength)
                    {
                        foreach (var c in characters)
                        {
                            newCombinations.Enqueue(currentString + c);
                        }
                    }
                }
                currentCombinations = newCombinations;
            }
            return string.Empty;
        }
    }
}
