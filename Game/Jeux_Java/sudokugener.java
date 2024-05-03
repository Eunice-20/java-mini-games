package Jeux_Java;


public class sudokugener{

    public static void main(String[] args)
    {
        int [][] tabsudo = {
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0},};
        int [] valin = new int[9]; 
        
        for(int ip = 1; ip <  3; ip ++){
            valin = new int[] {12,12,12,12,12,12,12,12,12};
            int mp = 0;

            while(mp != 9){
                int [] Val = {0,1,2,3,4,5,6,7,8};
                int AR = 0;
                for(int gp = 0; gp != 3 ; gp ++){
                
                int ale = (int)(Math.random() * (9 - AR));
                
                for(int dp = 0 ; dp<valin.length ; dp ++){
                    if(Val[ale] == valin[dp]){
                    ale = (int)(Math.random() * (9 - AR));
                    dp = 0;
                    }
                }

                for(int g = 0; g < 7 ; g --){
                    if(tabsudo[mp][ale] != 0 ){
                        ale = (int)(Math.random() * (9 - AR));
                    }else
                    break;
                }
                if(ale == 0 || ale == 1 || ale == 2){
                    tabsudo[mp][Val[ale]] = ip;
                    valin[mp]=Val[ale];
                    Val = removeTheElement(Val, 2);
                    Val = removeTheElement(Val, 1);
                    Val = removeTheElement(Val, 0);
                    AR += 3;

                }
                else if(ale == 3 || ale == 4 || ale == 5){
                    tabsudo[mp][Val[ale]] = ip;
                    valin[mp]=Val[ale];
                    Val = removeTheElement(Val, 5);
                    Val = removeTheElement(Val, 4);
                    Val = removeTheElement(Val, 3);
                    AR += 3;
                }
                else if(ale == 6 || ale == 7 || ale == 8){
                    tabsudo[mp][Val[ale]] = ip;
                    valin[mp]=Val[ale];
                    Val = removeTheElement(Val, 8);
                    Val = removeTheElement(Val, 7);
                    Val = removeTheElement(Val, 6);
                    AR += 3;
                }
                mp += 1;
            }
            }
        }
        for(int op = 0; op <=  8; op ++){
            for(int ap = 0; ap <=  8; ap ++){
                System.out.print(tabsudo[op][ap]);
            }
            System.out.println();
        }
    }
    private static int[] removeTheElement(int[] val, int a) {
        if (val == null || a < 0
        || a >= val.length) { 

        return val; 
    } 
    int[] anotherArray = new int[val.length - 1]; 
    for (int i = 0, k = 0; i < val.length; i++) { 
        if (i == a) { 
            continue; 
        } 
        anotherArray[k++] = val[i]; 
    }  
    return anotherArray;
    }
}