 //  Try to convert the following from Infix to Postfix 
 //  a*b/(c-a)+d*e
 //  ab*ca-/de*+
 //
import java.lang.Math;

public class Calculator {
    public static void main(String[] args) {
        //infix expression that needs to be converted to postfix
        String equation = "a*b/(c-a)+d*e";
        
        System.out.println("\nConverting the equation:\na*b/(c-a)+d*e\n-----------------------");
        System.out.println(convertToPostFix(equation));
        System.out.println("-----------------------\nEvaluating the equation:\na*b/(c-a)+d*e\n-----------------------");
        System.out.println(evaluatingPostFix(convertToPostFix(equation)));
    } //end main

    public static String convertToPostFix(String equation) {
        //new stack object
        ResizableArrayStack<Character> form = new ResizableArrayStack<>();
        //result string for the postfix form
        String result = "";
        //for loop to loop through each character in the equation
        for (int i = 0; i < equation.length(); i++) {
            //placeholder for the string at index i
            char acter = equation.charAt(i);
            //if statement to check if the character is a variable
            if (isAlphabet(acter))
                //add it to the result (queue)
                result += acter;
            //if the character is an opened parenthesis
            else if (acter == '(')
                //push it onto the stack
                form.push(acter);
            //if the character is a closed parenthesis
            else if (acter == ')') {
                //while loop to add all the operands in between the parentheses to the result
                while (form.peek() != '(')
                    //ends when the opened parentheses appears at the top of the stack
                    result += form.pop();
                //pops the opened parenthesis so it doesn't show up in the postfix expression
                form.pop();
            }
            //when the character is an operand
            else {
                //if the stack is empty, push the operand onto the stack
                if (form.isEmpty())
                    form.push(acter);
                //if there already is operands on the stack
                else {
                    // compare if the precedence of the operand is less than the operand on top of the stack,
                    //then pop the operand on top of the stack and then repeat until the operand being checked has the highest precedence
                    while (!form.isEmpty() && Priority(acter) <= Priority(form.peek()))
                        result += form.pop();
                    //push the new operand onto the stack
                    form.push(acter);
                }
            }
        } //end loop
        //when there is no more characters left to check, pop the remaining operands left on the stack into the expression
        while (!form.isEmpty())
            result += form.pop();
        //return the postfix expression
        return result;
    } //end convertToPostFix

    public static int evaluatingPostFix(String equation) {
        //new stack object
        ResizableArrayStack<Integer> form = new ResizableArrayStack<>();
        //for loop to loop through each character in the equation
        for (int i = 0; i < equation.length(); i++) {
            //placeholder for the string at index i
            char acter = equation.charAt(i);
            //if statement to check if the character is a variable
            if (isAlphabet(acter))
                form.push(valueOf(acter));
            //if the character shown is a operand, perform the operand with the two variables on top
            if (acter == '+') {
                int variable1 = form.pop();
                int variable2 = form.pop();
                int eger = variable2 + variable1;
                form.push(eger);
            }
            else if (acter == '-') {
                int variable1 = form.pop();
                int variable2 = form.pop();
                int eger = variable2 - variable1;
                form.push(eger);
            }
            else if (acter == '*') {
                int variable1 = form.pop();
                int variable2 = form.pop();
                int eger = variable2 * variable1;
                form.push(eger);
            }
            else if (acter == '/') {
                int variable1 = form.pop();
                int variable2 = form.pop();
                int eger = variable2 / variable1;
                form.push(eger);
            }
            if (acter == '^') {
                int variable1 = form.pop();
                int variable2 = form.pop();
                int eger = (int)Math.pow(variable2, variable1);
                form.push(eger);
            }
        } //end loop
        //return the top of the stack, which should be the fully evaluated expression
        return form.peek();
    } //end evaluatingPostFix

    private static int Priority(char operator) {
        switch(operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return -1;
    } //end Priority
    
    private static boolean isAlphabet(char input) {
        if (input == 'a')
            return true;
        else if (input == 'b')
            return true;
        else if (input == 'c')
            return true;    
        else if (input == 'd')
            return true;    
        else if (input == 'e')
            return true;
        else if (input == 'f')
            return true;
        else
            return false;
    } //end isAlphabet

    private static int valueOf(char input) {
        if (input == 'a')
            return 2;
        else if (input == 'b')
            return 3;
        else if (input == 'c')
            return 4;
        else if (input == 'd')
            return 5;
        else
            return 6;
    } //end valueOf

} //end Calculator
