using System.Text.RegularExpressions;

public class InputValidator
{
    public bool ValidatePassword(string password)
    {
        return !string.IsNullOrEmpty(password);
    }

    public bool ValidateHash(string hash)
    {
        // Validate hash by checking length and characters (SHA-256 hash example: 64 hex characters)
        return !string.IsNullOrEmpty(hash) && Regex.IsMatch(hash, "^[a-fA-F0-9]{64}$");
    }
}
