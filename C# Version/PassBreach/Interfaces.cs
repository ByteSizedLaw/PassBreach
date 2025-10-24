namespace PassBreach
{
    public interface IHashCracker
    {
        public string CrackPassword();
    }
    public interface IConcurrentQueueManager
    {
        public void Enqueue(string s);
        public string Dequeue();
    }
    public interface IHashAlgorithm
    {
        public string ComputeHash(string s);
    }
}
