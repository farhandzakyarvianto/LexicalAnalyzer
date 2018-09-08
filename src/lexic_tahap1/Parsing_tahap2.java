/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexic_tahap1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import static lexic_tahap1.Lexic_tahap1.fail_state;

/**
 *
 * @author ghost
 */
public class Parsing_tahap2 {
    public int statePDA;
    public Map<String, ArrayList<Character[]>> rules;
    public Stack<Character> stacks;
    public boolean expandToOpr;
    public boolean kurAfter;
    public boolean passed;

    public Parsing_tahap2() {
        statePDA = 0 ;
        rules = new HashMap<>();
        stacks = new Stack<>();
        expandToOpr = false;
        kurAfter = false;
        passed = false;
        
        ArrayList<Character[]> SValues = new ArrayList<>();
        ArrayList<Character[]> EValues = new ArrayList<>();
        
        SValues.add(new Character[]{'E'});
        
        EValues.add(new Character[]{'x'});
        EValues.add(new Character[]{'E', 'o', 'E'});
        EValues.add(new Character[]{'b', 'E', 't'});
        
        rules.put("S", SValues);
        rules.put("E", EValues);
    }
    
    public void parsing(ArrayList<String> result) {
        if (statePDA == 0) {
            stacks.push('#');
            statePDA++;
        }
        if (statePDA == 1) {
            stacks.push('S');
            statePDA++;
        }
        if (statePDA == 2) {
            stacks.pop();
            stacks.push('E');
            int i = 0;
            
            while (!passed) {
                kurAfter = check(stacks);
                if (((result.get(i)=="kurbuka" && stacks.peek()=='b') || 
                        (result.get(i)=="kurtutup" && stacks.peek()=='t') ||
                        (result.get(i)=="num" && stacks.peek()=='x') ||
                        (result.get(i)=="opr" && stacks.peek()=='o'))){
                    stacks.pop();
                    result.remove(i);
                }else {
                    if ((result.indexOf(result.get(i)) == result.size()-1) ||
                        (result.size()+1 == stacks.size())) {
                        expandToOpr = false;
                        expand(expandToOpr, kurAfter, i, result, stacks);
                    }else {
                        expandToOpr = true;
                        expand(expandToOpr, kurAfter, i, result, stacks);
                    }
                }
                if (stacks.peek() == '#') {
                    stacks.pop();
                    passed = true;
                }
                
//                i++;
            }
            System.out.println(stacks.size());
        }
        
    }
    
    public void expand(boolean b, boolean x, int i, ArrayList<String> result, Stack<Character> stacks){
        if (b) {
            if (result.get(i) == "num") {
                if (x) {
                    stacks.pop();
                    result.remove(i);
                    result.remove(i);
                    for (int j = rules.get("E").get(2).length-1; j >= 0; j--) {
                        stacks.push(rules.get("E").get(2)[j]);
                    } 
                }else if (result.get(i+1) == "opr") {
                    stacks.pop();
                    for (int j = rules.get("E").get(1).length-1; j >= 0; j--) {
                        stacks.push(rules.get("E").get(1)[j]);
                    }
                }
            } else if (result.get(i) == "kurbuka") {
                stacks.pop();
                for (int j = rules.get("E").get(2).length-1; j >= 0; j--) {
                    stacks.push(rules.get("E").get(2)[j]);
                }
            }
        }else {
            stacks.pop();
            for (int j = rules.get("E").get(0).length-1; j >= 0; j--) {
                stacks.push(rules.get("E").get(0)[j]);
            }
        }
    }
    
    
    public boolean check(Stack<Character> stacks){
        Character cr = stacks.elementAt(stacks.size()-2);
        if (cr == 'o') {
            return true;
        }else {
            return false;
        }
    }
    
//    public Stack<Character> firstNum(Stack<Character> stacks, String currChar, int counter){
//        if (counter==1) {
//            Stack<Character> temp = new Stack<>();
//            stacks.push(rules.get("S").get(0)[0]);
//            if (stacks.peek()=='E') {
//                stacks.pop();
//                stacks.push(rules.get("E").get(0)[0]);
//            }
//            if (stacks.peek()=='x') {
//                stacks.pop();
//            }
//            return stacks;
//        }
//        return null;
//    }
    
    
}
