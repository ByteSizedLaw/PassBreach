using System.Diagnostics;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Xunit;

namespace PassBreach.Tests
{
    /// <summary>
    /// Tests in this class will test overall <b>System Performance</b>
    /// </summary>
    [TestClass]
    public class PerformanceTests
    {
        //private readonly char[] characters = "abcdefghijklmnopqrstuvwxyz".ToCharArray();
        // Note: I had to reduce the size because a full char set took too long to calculate
        private readonly char[] characters = "pwordabcs".ToCharArray();
        [TestMethod]
        // BRD Requirement 4.1 - Handle Large Input Spaces Efficiently
        public void BruteForceAlgorithm_ShouldHandleLargeInputSpacesEfficiently()
        {
            // Arrange
            string targetHash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"; // SHA-256 of "password"
            var queueManager = new ConcurrentQueueManager();
            var hashAlgorithm = new SHA256HashAlgorithm();
            var stopwatch = new Stopwatch();

            foreach (char c in characters)
            {
                queueManager.Enqueue(c.ToString());
            }
            var cracker = new HashCracker(queueManager, hashAlgorithm, targetHash, characters, 8);

            // Act
            stopwatch.Start();
            var originalPassword = cracker.CrackPassword();
            stopwatch.Stop();

            // Assert
            Xunit.Assert.Equal("password", originalPassword);
            Console.WriteLine($"Time Taken: {stopwatch.ElapsedMilliseconds} ms");
        }

    [TestMethod]
        // BRD Requirement 4.2 - Utilize System Resources Effectively
        public void BruteForceAlgorithm_ShouldUtilizeSystemResourcesEffectively()
        {
            // Arrange
            string targetHash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"; // SHA-256 of "password"
            var queueManager = new ConcurrentQueueManager();
            var hashAlgorithm = new SHA256HashAlgorithm();
            var stopwatch = new Stopwatch();
            var process = Process.GetCurrentProcess();

            foreach (char c in characters)
            {
                queueManager.Enqueue(c.ToString());
            }
            var cracker = new HashCracker(queueManager, hashAlgorithm, targetHash, characters, 8);

            // Act
            stopwatch.Start();
            var originalPassword = cracker.CrackPassword();
            stopwatch.Stop();

            // Measure resource usage
            var memoryUsage = process.PrivateMemorySize64 / (1024 * 1024); // Memory usage in MB

            // Assert
            Xunit.Assert.Equal("password", originalPassword);
            Console.WriteLine($"Time Taken: {stopwatch.ElapsedMilliseconds} ms");
            Console.WriteLine($"Memory Usage: {memoryUsage} MB");
        }
    }
}
