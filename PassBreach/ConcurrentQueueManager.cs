using System.Collections.Concurrent;

namespace PassBreach
{
    public class ConcurrentQueueManager : IConcurrentQueueManager
    {
        private readonly ConcurrentQueue<string> queue = new ConcurrentQueue<string>();

        public void Enqueue(string s)
        {
            queue.Enqueue(s);
        }

        public string Dequeue()
        {
            return queue.TryDequeue(out string result) ? result : null;
        }
    }
}
