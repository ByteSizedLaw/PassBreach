namespace PassBreach
{
    public class SHA256HashAlgorithm : IHashAlgorithm
    {
        public string ComputeHash(string input)
        {
            using (var sha256 = System.Security.Cryptography.SHA256.Create())
            {
                byte[] bytes = sha256.ComputeHash(System.Text.Encoding.UTF8.GetBytes(input));
                return BitConverter.ToString(bytes).Replace("-", "").ToLowerInvariant();
            }
        }
    }
}
