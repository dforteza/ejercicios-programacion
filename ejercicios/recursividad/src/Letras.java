public class Letras 
{
    public static void main(String[] args) 
    {
        // System.out.println(alphabetic_sorted("abcde"));
        // System.out.println(alphabetic_sorted("abCde"));
        // System.out.println(palindrome("anitalavalatina"));
        // System.out.println(palindrome("anitalavalatinA"));
        // System.out.println(creciente(123456789));
        // System.out.println(creciente(1234567890));
        // System.out.println(parentesisBalanceados("(()())"));
        // System.out.println(parentesisBalanceados("(()))"));
    }

    // === Orden alfabético ===
    public static Boolean alphabetic_sorted(String s)
    {
        if (s.length() == 1)
            return (true);

        if (s.charAt(0) > s.charAt(1))
            return (false);

        return (alphabetic_sorted(s.substring(1)));
    }

    // === Palíndromo ===
    public static Boolean palindrome(String s)
    {
        if (s.length() <= 1)
            return (true);
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false;

        return (palindrome(s.substring(1, s.length() - 1)));
    }

    // === Números crecientes ===
    public static Boolean creciente(Integer n)
    {
        if (n < 10)
            return (true);

        if (n % 10 < (n / 10) % 10)
            return (false);
        return (creciente(n / 10));
    }

    // === Paréntesis balanceados ===
	public static boolean parentesisBalanceados(String s)
	{
		if (balanceo(s,0) != 0)
			return (false);

		return (true);
	}

	public static int balanceo (String s,int c)
	{
		if (s.length() == 0)
			return c;
		else
		{
			if (s.charAt(0)=='(')
				c++;
			else if (s.charAt(0)==')')
                c--;

			if (c < 0)
				return -1;
			else
				return balanceo (s.substring(1),c);
		}
	}
}
