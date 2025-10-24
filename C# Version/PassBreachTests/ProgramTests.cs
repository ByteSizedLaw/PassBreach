using System.Diagnostics;

namespace PassBreach.Tests
{
    /// <summary>
    /// Tests within this class will test <b>User Input</b> (TC-1.1 and TC-1.2)
    /// </summary>
    [TestClass]
    public class UserInputTests
    {
        private readonly InputValidator validator;

        public UserInputTests()
        {
            validator = new InputValidator();
        }

        [TestMethod]
        public void PasswordField_ShouldAcceptValidPassword()
        {
            // Arrange
            string validPassword = "password123";

            // Act
            bool isValid = validator.ValidatePassword(validPassword);

            // Assert
            Xunit.Assert.True(isValid, "Valid password should be accepted");
        }

        [TestMethod]
        public void PasswordField_ShouldRejectInvalidPassword()
        {
            // Arrange
            string invalidPassword = "";

            // Act
            bool isValid = validator.ValidatePassword(invalidPassword);

            // Assert
            Xunit.Assert.False(isValid, "Invalid password is correctly rejected");
        }

        [TestMethod]
        public void HashField_ShouldAcceptValidHash()
        {
            // Arrange
            string validHash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd786c91cfad736ab0a"; // Example SHA-256 hash

            // Act
            bool isValid = validator.ValidateHash(validHash);

            // Assert
            Xunit.Assert.True(isValid, "Valid hash should be accepted");
        }

        [TestMethod]
        public void HashField_ShouldRejectInvalidHash()
        {
            // Arrange
            string invalidHash = "12345"; // Invalid hash

            // Act
            bool isValid = validator.ValidateHash(invalidHash);

            // Assert
            Xunit.Assert.False(isValid, "Invalid hash should be rejected");
        }
    }

    /// <summary>
    /// Tests within this class will test <b>Password Cracking</b> (TC-2.1, TC-2.2)
    /// </summary>
    [TestClass]
    public class BruteForceTests
    {
        private readonly char[] characters = "pasword".ToCharArray();

        [TestMethod]
        public void BruteForceAlgorithm_ShouldFindPassword()
        {
            // Arrange
            string targetHash = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"; // SHA-256 of "password"
            var queueManager = new ConcurrentQueueManager();
            var hashAlgorithm = new SHA256HashAlgorithm();
            int leng = "password".Length;

            foreach (char c in characters)
            {
                queueManager.Enqueue(c.ToString());
            }
            var cracker = new HashCracker(queueManager, hashAlgorithm, targetHash, characters, leng);

            // Act
            var originalPassword = cracker.CrackPassword();

            // Assert
            Xunit.Assert.Equal("password", originalPassword);
        }
    }

    /// <summary>
    /// Tests within this class are not covered in the Test Plan or business requirements, but are necessary for validation.
    /// <br></br>
    /// Each Hash algorithm should return exactly the same hash given the same input, and the hash should exactly match what we expect.
    /// </summary>
    [TestClass]
    public class HashAlgorithmTests
    {
        [TestMethod]
        public void SHA256HashAlgorithm_ShouldReturnExpectedHash()
        {
            // Arrange
            SHA256HashAlgorithm hashAlgorithm = new SHA256HashAlgorithm();
            // Act
            string hash = CalculateHash.GetHash("password", hashAlgorithm);
            //Assert
            Xunit.Assert.Equal("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", hash);
        }
        [TestMethod]
        public void MD5HashAlgorithm_ShouldReturnExpectedHash()
        {
            // Arrange
            Md5Algorithm hashAlgorithm = new Md5Algorithm();
            // Act
            string hash = CalculateHash.GetHash("password", hashAlgorithm);
            //Assert
            Xunit.Assert.Equal("5F4DCC3B5AA765D61D8327DEB882CF99", hash);
        }
    }

    /// <summary>
    /// Tests within this class will test <b>Outputs</b> (TC-3.1, TC-3.2, TC-3.3)
    /// </summary>
    [TestClass]
    public class OutputTests
    {
        private readonly char[] characters = "pasword".ToCharArray();

        [TestMethod]
        public void BruteForceAlgorithm_ShouldDisplayPasswordAndTimeTaken()
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
            var cracker = new HashCracker(queueManager, hashAlgorithm, targetHash, characters, 8); // Adjusting maxPasswordLength if needed

            // Act
            stopwatch.Start();
            var originalPassword = cracker.CrackPassword();
            stopwatch.Stop();

            // Assert
            Xunit.Assert.Equal("password", originalPassword);
            Console.WriteLine($"Password: {originalPassword}");
            Console.WriteLine($"Time Taken: {stopwatch.ElapsedMilliseconds} ms");
        }

        [TestMethod]
        public void BruteForceAlgorithm_ShouldDisplayMessageIfPasswordNotFound()
        {
            // Arrange
            string targetHash = "0000000000000000000000000000000000000000000000000000000000000000"; // Unlikely SHA-256 hash to force failure
            var queueManager = new ConcurrentQueueManager();
            var hashAlgorithm = new SHA256HashAlgorithm();
            var stopwatch = new Stopwatch();

            foreach (char c in characters)
            {
                queueManager.Enqueue(c.ToString());
            }
            var cracker = new HashCracker(queueManager, hashAlgorithm, targetHash, characters, 3); //keeping the length small to speed up the test

            // Act
            stopwatch.Start();
            var result = cracker.CrackPassword();
            stopwatch.Stop();

            // Assert
            Xunit.Assert.Equal(string.Empty, result);
            Console.WriteLine("Password not found.");
            Console.WriteLine($"Time Taken: {stopwatch.ElapsedMilliseconds} ms");
        }
    }
}