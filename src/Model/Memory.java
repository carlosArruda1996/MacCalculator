package Model;

import Vision.Display;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private enum ComandType{
        ZERO, NUMBER, PERCENT, POS_NEG, DIVISION, MULTIPLICATION, SUM, SUBTRATION, COMMA, EQUAL
    }
    private static final Memory instance = new Memory();

    private ComandType lastOperation = null;
    private boolean substitute = false;
    private String currentText = "";
    private String bufferText = "";

    private final List<OberverMemory> observers = new ArrayList<>();

    private Memory(){

    }
    public static Memory getInstance(){
        return instance;
    }

    public void addObserver(Display observer){
        observers.add(observer);
    }

    public String getCurrentText(){
        return currentText.isEmpty() ? "0" : currentText;
    }
    public void comand(String text){

        ComandType comandType = detectComandType(text);

        if(comandType == null){
            return;
        } else if (comandType == ComandType.ZERO) {
            currentText = "";
            bufferText = "";
            lastOperation = null;
            substitute = false;
        } else if (comandType == ComandType.NUMBER || comandType == ComandType.COMMA) {
            currentText = substitute ? text : currentText + text;
            substitute = false;
        } else if (comandType == ComandType.POS_NEG && currentText.contains("-")) {
            currentText = currentText.substring(1);
        } else if (comandType == ComandType.POS_NEG && !currentText.contains("-")) {
            currentText = "-" + currentText;
        }else{
            substitute = true;
            currentText = getResultOperation();
            bufferText = currentText;
            lastOperation = comandType;
        }

        observers.forEach(o -> o.newValue(getCurrentText()));
    }

    private String getResultOperation() {
        if(lastOperation == null || lastOperation == ComandType.EQUAL){
            return currentText;
        }

        double bufferNumber = Double.parseDouble(bufferText.replace(",","."));
        double currentNumber = Double.parseDouble(currentText.replace(",","."));

        double result = 0;

        if (lastOperation == ComandType.SUM){
            result = bufferNumber + currentNumber;
        } else if (lastOperation == ComandType.DIVISION) {
            result = bufferNumber / currentNumber;
        } else if (lastOperation == ComandType.MULTIPLICATION) {
            result = bufferNumber * currentNumber;
        } else if (lastOperation == ComandType.SUBTRATION) {
            result = bufferNumber - currentNumber;
        } else if (lastOperation == ComandType.DIVISION) {
            result = bufferNumber / currentNumber;
        }else if(lastOperation == ComandType.PERCENT) {
            result = (currentNumber /100) * bufferNumber;
        }

        String resultString = Double.toString(result).replace(".",",");
        boolean intValue = resultString.endsWith(",0");
        return intValue ? resultString.replace(",0","") : resultString;
    }

    private ComandType detectComandType(String text){
        if(currentText.isEmpty() && currentText == "0"){
            return null;
        }
        try {
            Integer.parseInt(text);
            return ComandType.NUMBER;
        }catch (NumberFormatException e){
            if("AC".equalsIgnoreCase(text)){
                return ComandType.ZERO;
            } else if ("/".equals(text)) {
                return ComandType.DIVISION;
            } else if ("X".equalsIgnoreCase(text)) {
                return ComandType.MULTIPLICATION;
            } else if ("+".equals(text)) {
                return ComandType.SUM;
            } else if ("-".equals(text)) {
                return ComandType.SUBTRATION;
            } else if ("=".equals(text)) {
                return ComandType.EQUAL;
            } else if ("+/-".equalsIgnoreCase(text)) {
                return ComandType.POS_NEG;
            } else if ("%".equalsIgnoreCase(text)) {
                return ComandType.PERCENT;
            } else if (",".equals(text) && !currentText.contains(",")) {
                return ComandType.COMMA;
            }
        }
    return null;
    }
}
