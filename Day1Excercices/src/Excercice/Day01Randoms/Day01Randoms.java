public static void main(String[] args) {


    System.out.println("Enter a number:");
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    if (num <0) {
        System.err.println("Number must be positive");
        System.exit(1);
    }

    System.out.println("\nWhat is your name?");
    String name = sc.next();
    for(char c: name.toCharArray()){
        if (Character.isDigit(c)){
            System.err.print("There's a digit in your name");
            System.exit(1);
        }
    }

    System.out.println("Enter minimum integer");
    int min = sc.nextInt();
    System.out.println("Enter maximum integer");
    int max = sc.nextInt();
    if (min>max){
        System.err.println("The maximum must be greater than the minimum");
        System.exit(1);
    }



    for (int j=1; j<=num; j++){
        int number= (int) (min+(Math.random() * (max-min+1)));
        if (j == num){
            System.out.print(number);
            break;
        }
        System.out.print(number+", ");

        }
    System.out.println("\nDid I do well "+ name+"?");
    }
