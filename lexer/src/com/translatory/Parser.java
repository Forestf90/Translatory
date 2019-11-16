package com.translatory;

import java.util.*;

public class Parser {

    private Lexer lexer;

    private Stack<String> resultList;
    private Stack<String> operandsList;

    private Map<String, Integer> dictionary;



    public Parser(Lexer l){
        this.lexer =l;
        this.resultList = new Stack<>();
        this.operandsList = new Stack<>();
        dictionary = new HashMap<>();
        dictionary.put("(", 0);
        dictionary.put("+", 1);
        dictionary.put("-", 1);
        dictionary.put("*", 2);
        dictionary.put("/", 2);

    }

    public void analise() throws Exception {
        for(Token t: lexer.getTokens()){

            switch(t.getType()){

                case TokenType.number:
                    resultList.push(t.getValue());
                    break;
                case TokenType.operator:
                    String current = t.getValue();

                    while(operandsList.size() >0 && !operandsList.peek().equals("(")){

                        String last = operandsList.peek();

                        if(dictionary.get(current)<=dictionary.get(last)){
                            operandsList.pop();
                            //operandsList.
                            resultList.push(String.valueOf(calculate(last)));
                        }
                        else break;
                    }
                    operandsList.push(current);
                    break;
                case TokenType.bracket:
                    if(t.getValue().equals("(")) operandsList.push(t.getValue());
                    else if (t.getValue().equals(")")) operation(true);
                    break;
                case TokenType.whiteSpace:


            }
        }
        //resultList.push(String.valueOf(calculate(operandsList.firstElement())));
        operation(false);
        if(!operandsList.isEmpty())  throw new Exception("Invalid number of brackets");
        for(String s: resultList){
            System.out.println(s);
        }

    }

    private void operation(boolean rightBracket) throws Exception {

        while(operandsList.size() >0 && !operandsList.peek().equals("(")){

            String last = operandsList.pop();
            resultList.push(String.valueOf(calculate(last)));
        }
        if(rightBracket){
            if(operandsList.isEmpty()) throw new Exception("Invalid number of brackets");
            String last = operandsList.peek();
            if(last.equals("(")){
                operandsList.pop();
            }else{
                throw new Exception("Invalid number of brackets");
            }
        }
    }

    private int calculate(String type) throws Exception {

        try{
            switch (type){
                case "+":
                    int arg2p = Integer.parseInt(resultList.pop());
                    int arg1p = Integer.parseInt(resultList.pop());
                    return arg2p+arg1p;
                case "-":
                    int arg2m = Integer.parseInt(resultList.pop());
                    int arg1m = Integer.parseInt(resultList.pop());
                    return arg1m-arg2m;

                case "*":
                    int arg2r = Integer.parseInt(resultList.pop());
                    int arg1r = Integer.parseInt(resultList.pop());
                    return arg2r*arg1r;

                case "/":
                    int arg2d = Integer.parseInt(resultList.pop());
                    int arg1d = Integer.parseInt(resultList.pop());
                    return arg1d/arg2d;

            }
        }catch (Exception e){
            throw new Exception("Cannot perform operation "+type+" not enough operands");
        }
        return 0;
    }


}




