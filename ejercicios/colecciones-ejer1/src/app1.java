import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class app1 
{
    public static void main(String[] args) throws Exception 
    {
        int flag = 0;

        List<Integer> arr = new ArrayList<>();
        Set<Integer> hs = new HashSet<>();
        Integer n;

        Scanner sc = new Scanner(System.in);

        Integer sum = 0;
        do 
        {
            System.out.print("> Introduce n -> ");
            try 
            {
                n = sc.nextInt();            
            } catch (InputMismatchException e) 
            {
                n = -1;
                sc.nextLine();            
                continue ;
            }
            sc.nextLine();            

            sum += n;
            arr.add(n);
            hs.add(n);            
        } while (n != 0);

        // SUMA
        System.out.println("Suma: "+ sum);

        // MEDIA
        System.out.println("Media: "+ sum / arr.size());


        // N QUE SUPERAN LA MEDIA
        for (Integer i : arr)
        {
            if (i > sum / arr.size())
            {
                if (flag == 0)
                {
                    System.out.print(i);
                    flag = 1;
                }
                else
                    System.out.print(" - "+ i);
            }
        }

        System.out.println();

        // LISTA ORDENADA
        arr.sort(null);

        for (Integer i : arr)
            System.out.print(i + " > ");


        
        sc.close();
    }
}
