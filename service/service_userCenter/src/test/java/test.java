import org.junit.Test;

public class test {
    @Test
    public void test(){
        float calc = calc(("((3/2)*3)"));
        System.out.println(calc);
    }
    public  static float calc(String newFormula) {
        boolean stillHaveCalcSymbol = false;
        do{
            //System.out.println("before:" + newFormula);
            /** 寻找最后一个左括号里面到第一个右括号里面1的内容 **/
            char formulaArray[] = newFormula.toCharArray();
            for (int i = 0; i < formulaArray.length; i++) {
                if (formulaArray[i] == '+' || formulaArray[i] == '-'
                        || formulaArray[i] == '*' || formulaArray[i] == '/'
                        || formulaArray[i] == '(' || formulaArray[i] == ')') {
                    stillHaveCalcSymbol = true;
                } else {
                    stillHaveCalcSymbol = false;
                }
            }
            if (stillHaveCalcSymbol) {
                String resultFormula = "";
                //找最内层的括号里面的内容出来（含括号）
                for (int i = 0; i < formulaArray.length; i++) {
                    if (formulaArray[i] == ')') {
                        int begin = 0;
                        //回溯找到左括号(
                        for (int j = i; j >= 0; j--) {
                            if (formulaArray[j] == '(') {
                                begin = j;
                                break;
                            }
                        }
                        //subString获取括号里的字符串()
                        String calcString = newFormula.substring(begin, i + 1);
                        //resultFormula是括号里计算得到的一个数字
                        resultFormula = newFormula.replace(calcString, calcProc(calcString) + "");
                        //System.out.println(calcString);
                        break;
                    }
                }
                newFormula = resultFormula;
            }
        } while(stillHaveCalcSymbol);
        //最后得到普通的顺序无括号公式：
        System.out.println(newFormula+" 要进行最后一次");

        //最后一次计算:
        //float result = calcProc("(" + newFormula.split("=")[1] + ")");
        return Float.valueOf(newFormula);
    }
    /**详细计算过程**/
    private static float calcProc(String calcString) {
//		if(calcString.contains("=")){
//			calcString = calcString.split("=")[1];
//		}
        //calcString = calcString.replace("(", "");
        //calcString = calcString.replace(")", "");

        String calcSymbol[] = {"\\*", "\\/", "\\+", "\\-"};
        char calcSymbolChar[] = {'*', '/', '+', '-'};
        boolean haveSymbol = true;
        float result = 0f;
        while(haveSymbol){
            System.out.println("calcStr:" + calcString);
            char calcCharArr[] = calcString.toCharArray();
            result = 0f;
            for (int i = 0; i < calcSymbol.length; i++) {
                boolean alreadyFind = false;
                for(int j = 0; j < calcCharArr.length; j++){
                    if(calcCharArr[j] == calcSymbolChar[i]){
                        //System.out.println("找到了" + calcSymbolChar[i]);
                        //以符号为中心，以左右两边的其他符号为边界找到两边的数
                        float num1 = 0f;
                        float num2 = 0f;
                        int bottom = 0;
                        //找到左边的数
                        for(int k = j - 1; k >= 0 && (calcCharArr[k] >= '0' && calcCharArr[k] <= '9' || calcCharArr[k] == '.') ; k--){
                            //System.out.println(calcCharArr[k] + "");
                            bottom = k;
                        }
                        //System.out.println("[j, bottom]:" + String.format("[%d, %d]", j, bottom));
                        //用取子字串的方法获得该符号左边的数，
                        num1 = Float.valueOf(calcString.substring(bottom, j));
                        System.out.println("num1:" + num1);
                        int top = 0;
                        //找到右边的数
                        for(int k = j + 1; k < calcString.length() && (calcCharArr[k] >= '0' && calcCharArr[k] <= '9' || calcCharArr[k] == '.'); k++){
                            top = k;
                        }
                        num2 = Float.valueOf(calcString.substring(j + 1, top + 1));
                        System.out.println("num2:" + num2);
                        //计算result=该括号里的计算总值
                        switch(calcSymbolChar[i]){
                            case '*':
                                result = num1 * num2;
                                break;
                            case '/':
                                result = num1 / num2;
                                break;
                            case '+':
                                result = num1 + num2;
                                break;
                            case '-':
                                result = num1 - num2;
                                break;
                        }
                        //System.out.println("bottom to top:" + calcString.substring(bottom + 1, top + 1));
                        calcString = calcString.replace(calcString.substring(bottom, top + 1), String.format("%.5f", result));
                        //System.out.println("end_calcStr:" + calcString);
                        alreadyFind = true;
                        break;
                    }
                }
                if(alreadyFind) break;
            }
            haveSymbol = false;
            if(calcString.contains("*") || calcString.contains("/") || calcString.contains("+") || calcString.contains("-")){
                haveSymbol = true;
                //System.out.println("找到");
            } else {
                //System.out.println("找不到");
            }
        }
        //System.out.println("result:" + result);
        return result;
    }


}
