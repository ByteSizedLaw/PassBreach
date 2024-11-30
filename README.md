# PassBreach
This is an educational **Brute-Force Password-Hash Cracker** that will help you understand how password-crackers work.<br>
This tool implements the Power Set algorithm, making it the most accurate password-cracker in the world.<br>

## Notes
1. I do not endorse illegal activity of any kind. I also do not wish to enable anyone to perform illegal activity.
2. This tool was designed strictly for educational purposes.
3. You assume ALL liability when using this tool for illegal activity, or when converting this tool into a cyber-weapon of ANY kind.
4. It is ILLEGAL to attempt to crack passwords or systems that you do not own.
5. Computers have finite memory to work with, and this tool (because of the use-case and mathematical algorithm it implements) uses lots of RAM. Please monitor your PCs memory usage and keep the password length and character-set within a reasonable threshold for your PC

## Some notes about passwords and password-crackers
1. The entropy of a password increases with the length of the password and, in that vein, using more random characters and symbols, creates a stronger password because it requires more combinations to crack/guess.
    - This sounds like common-sense, because "1234" (4 characters) is obviously weaker that "12345678" (8 characters) - since it's shorter and requires less combinations to find, but this also applies to the contents of the password itself if passwords are the same length. 
    - Due to how some other password-crackers are written: "1'[$" (4 characters) is more secure than its equivalent "1234" (4 characters), etc. (Note: For **PassBreach**, both passwords are equally weak)
2. At some point, a password can become almost impossible to crack immediately ("immediately" is the keyword here) using a tool like this, because of current computing constraints and finite-memory,  <br>
    - Hackers would then require a "slower" approach, which sacrifices speed for memory/resources efficiency, <br>
    - Meaning that your password will STILL be vulnerable to reverse-engineering over a longer period of time, or when computers become more powerful (every 18 months - [Moore's Law](https://www.intel.com/content/www/us/en/newsroom/resources/moores-law.html)). <br>

### What you will come to understand from using this tool is:
Having a long password (12 chars, for example) isn't Secure enough - if you're only using the same 5 characters to create that password. <br>
For real-world passwords, you should use longer passwords with more random combinations of characters and symbols, to make it more difficult to reverse-engineer. <br>
You should also avoid using the same passwords for different sites/systems. <br>

## Overview of How the App Works:
When started, the app will specify some parameters for you to work within. <br>
Answer some of its questions and give it time to reverse-engineer the password hash.
- You can either input a hash you already have and let it crack that, or let the app generate one for you.
<br>
Note: For smaller passwords (1~3 characters), because of the speedy reply, it's easy to believe that the app is just repeating what you initially typed - without any calculation, but a quick read through the code should dispel any of those doubts.

## Currently supported Hashing algs (algorithms we can crack):
1. MD5 (Deprecated because of vulnerabilities, but still in use in legacy systems)
2. SHA-256 (Most common, since it's used by organizations and governments, worldwide, for its security)
3. Will be adding more algorithms onto this later.

## Some Additional Info on Hackers and Common Tactics:
1. Hackers/"Malicious Actors"/"Threat actors" may sometimes manage to steal/compromise a database of stored password hashes. 
    - Passwords are always stored as hashes (Which are impossible to reverse-engineer [without this tool] - because hashing is a one-way function).
    - The attacker would then use a Cyber Weapon (like a Rainbow-table, etc.) to guess the original password that created the (stolen) password hash. These tools are not 100% accurate, and the attacker may never guess the correct password.
2. Attackers could also use Cyber Weapons similar this, to send hundreds-of-thousands of login-attempts to a server, to "guess" your password and gain access to your information.
    - This is why "Lockout counts" (Although annoying to us as users) are so important! They prevent hackers from logging into your accounts, by locking your accounts after a few failed login attempts
